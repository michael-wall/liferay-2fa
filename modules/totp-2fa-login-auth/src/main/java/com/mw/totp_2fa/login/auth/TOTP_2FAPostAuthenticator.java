package com.mw.totp_2fa.login.auth;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.Authenticator;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Validator;
import com.mw.totp_2fa.api.TOTP_2FAGenerator;
import com.mw.totp_2fa.config.TOTP_2FAConfiguration;
import com.mw.totp_2fa.key.model.SecretKey;
import com.mw.totp_2fa.key.service.SecretKeyLocalService;
import com.mw.totp_2fa.login.auth.constants.LoginConstants;
import com.mw.totp_2fa.util.TOTP_2FAUtil;

import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

@Component(
	    immediate = true, property = {"key=auth.pipeline.post"},
	    service = Authenticator.class,
	    configurationPid = TOTP_2FAConfiguration.PID
)
public class TOTP_2FAPostAuthenticator implements Authenticator {

	@Override
	public int authenticateByScreenName(long companyId, String screenName, String password,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap) throws AuthException {
		
		if (!tfaConfiguration.loginTotp2faEnabled()) return Authenticator.SUCCESS;

		return verifyAuthenticatorCode(CompanyConstants.AUTH_TYPE_SN, companyId, screenName, parameterMap);
	}

	@Override
	public int authenticateByEmailAddress(long companyId, String emailAddress, String password,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap) throws AuthException {
		
		if (!tfaConfiguration.loginTotp2faEnabled()) return Authenticator.SUCCESS;

		return verifyAuthenticatorCode(CompanyConstants.AUTH_TYPE_EA, companyId, emailAddress, parameterMap);
	}

	@Override
	public int authenticateByUserId(long companyId, long userId, String password, Map<String, String[]> headerMap,
			Map<String, String[]> parameterMap) throws AuthException {
		
		if (!tfaConfiguration.loginTotp2faEnabled()) return Authenticator.SUCCESS;
		
		return verifyAuthenticatorCode(CompanyConstants.AUTH_TYPE_ID, companyId, userId, parameterMap);
	}

	private int verifyAuthenticatorCode(String authType, long companyId, Object identifier, Map<String, String[]> parameterMap)
			throws AuthException {
		long time = System.currentTimeMillis();
		
		User user = fetchUser(authType, companyId, identifier);
		
		if (user == null) {
			if (_log.isInfoEnabled()) {
				_log.info("TOTP_2FAPostAuthenticator, return failure as user null for: " + identifier);	
			}
			
			return Authenticator.FAILURE;
		}	
		
		boolean[] isAdministratorOrSkipUserRole = TOTP_2FAUtil.isAdministratorOrSkipUserRole(user);
			
		if (isAdministratorOrSkipUserRole[0]) {
			if (_log.isInfoEnabled()) {
				if (isAdministratorOrSkipUserRole[1]) {
					_log.info("TOTP_2FAPostAuthenticator, return success as user has Administrator User Role (skipping TOTP 2FA check) for: " + identifier);	
				} else {
					_log.info("TOTP_2FAPostAuthenticator, return success as user has " + tfaConfiguration.loginTotp2faSkipUserRole() + " User Role (skipping TOTP 2FA check) for: " + identifier);	
				}
			}			

			return Authenticator.SUCCESS;
		}
		
		if (getTotpGenerator() == null) {
			_log.error("TOTP_2FAPostAuthenticator, return failure as TOTP Generator is null for: " + identifier);
			
			return Authenticator.FAILURE;
		}

		String authenticatorCode = null;
		
		if (parameterMap.containsKey(LoginConstants.AUTHENTICATOR_CODE_FIELD) && parameterMap.get(LoginConstants.AUTHENTICATOR_CODE_FIELD) != null) {
			authenticatorCode = parameterMap.get(LoginConstants.AUTHENTICATOR_CODE_FIELD)[0];
			
			if (Validator.isNull(authenticatorCode)) {
				if (_log.isInfoEnabled()) {
					_log.info("TOTP_2FAPostAuthenticator, return failure as authenticatorCode null for: " + identifier);	
				}
				
				return Authenticator.FAILURE;
			}
			
			// Remove any whitespace within the code e.g. if user used xxx xxx syntax to match Google Authenticator or xx xx xx syntax used in other 2FA apps.
			authenticatorCode = authenticatorCode.replaceAll("\\s", "");

			if (authenticatorCode.trim().length() != tfaConfiguration.authenticatorCodeLength()) {
				if (_log.isInfoEnabled()) {
					_log.info("TOTP_2FAPostAuthenticator, return failure as authenticatorCode not " + tfaConfiguration.authenticatorCodeLength() + " chars for: " + identifier);
				}
				
				return Authenticator.FAILURE;
			}
		} else {
			if (_log.isInfoEnabled()) {
				_log.info("TOTP_2FAPostAuthenticator, return failure as authenticatorCode missing for: " + identifier);	
			}
			
			return Authenticator.FAILURE;
		}

		SecretKey secretKeyObject = secretKeyLocalService.fetchSecretKeyByUserId(companyId, user.getUserId());

		if (Validator.isNull(secretKeyObject)) {
			if (_log.isInfoEnabled()) {
				_log.info("TOTP_2FAPostAuthenticator, return failure as stored secretKey missing for: " + identifier);	
			}
			
			return Authenticator.FAILURE;				
		}

		if (getTotpGenerator().isMatch(String.valueOf(identifier), authenticatorCode, tfaConfiguration.allowForTimeSkew(), secretKeyObject.getSecretKey(), tfaConfiguration.authenticatorCodeLength(), tfaConfiguration.authenticatorCodeDuration(), time)) {
			if (_log.isDebugEnabled()) {
				_log.debug("TOTP_2FAPostAuthenticator, return success as authenticatorCode matches generatedAuthenticatorCode for: " + identifier);
			}

			return Authenticator.SUCCESS;
		} else {
			if (_log.isInfoEnabled()) {
				_log.info("TOTP_2FAPostAuthenticator, return failure as authenticatorCode mismatch for: " + identifier);
			}			
		}

		return Authenticator.FAILURE;
	}

	private User fetchUser(String authType, long companyId, Object identifier) {
		User user = null;

		if (authType.equalsIgnoreCase(CompanyConstants.AUTH_TYPE_EA)) {
			user = userLocalService.fetchUserByEmailAddress(companyId, (String)identifier);	
		} else if (authType.equalsIgnoreCase(CompanyConstants.AUTH_TYPE_SN)) {
			user = userLocalService.fetchUserByScreenName(companyId, (String)identifier);
		} else if (authType.equalsIgnoreCase(CompanyConstants.AUTH_TYPE_ID)) {
			user = userLocalService.fetchUserById((long)identifier);
		}
		
		return user;
	}

	@Activate
	@Modified
	protected void activate(BundleContext bundleContext, Map<String, Object> properties){		
		tfaConfiguration = ConfigurableUtil.createConfigurable(TOTP_2FAConfiguration.class, properties);

		if (_log.isInfoEnabled()) {
			_log.info("*********************************************");
			_log.info("tfaConfiguration.loginTotp2faEnabled: " + tfaConfiguration.loginTotp2faEnabled());
			_log.info("tfaConfiguration.loginTotp2faSkipUserRole: " + tfaConfiguration.loginTotp2faSkipUserRole());
			_log.info("tfaConfiguration.authenticatorCodeLength: " + tfaConfiguration.authenticatorCodeLength());
			_log.info("tfaConfiguration.authenticatorCodeDuration: " + tfaConfiguration.authenticatorCodeDuration());
			_log.info("tfaConfiguration.allowForTimeSkew: " + tfaConfiguration.allowForTimeSkew());
			_log.info("tfaConfiguration.totp2faImplementation: " + tfaConfiguration.totp2faImplementation());
			_log.info("TOTP Generator Impl filter: " + getFilter());
			_log.info("*********************************************");
		}
		
		setBundleContext(bundleContext);
		
		setTOTPGenerator();
	}

	private void setTOTPGenerator() {
		ServiceReference[] serviceReferences = null;
		try {
			serviceReferences = bundleContext.getServiceReferences(TOTP_2FAGenerator.class.getName(), getFilter());
		} catch (InvalidSyntaxException e) {
			_log.error("InvalidSyntaxException for filter: " + getFilter() + ", " + e.getClass().getCanonicalName() + ", " + e.getMessage(), e);
		}
		
		if (serviceReferences != null && serviceReferences.length >= 1) {
			if (_log.isInfoEnabled()) {
				_log.info("TOTP Generator Impl: " + serviceReferences[0].getProperty(TOTP_2FAGenerator.TOTP_IMPL_PROPERTY));
			}
			setTotpGenerator((TOTP_2FAGenerator)bundleContext.getService(serviceReferences[0]));	
		} else {
			_log.error("No TOTP Generator Impl found for filter: " + getFilter());
			
			setTotpGenerator(null);
		}
	}
	
	private String getFilter() {
		return "(&(objectClass=" + TOTP_2FAGenerator.class.getName() + ")(" + TOTP_2FAGenerator.TOTP_IMPL_PROPERTY + "=" + tfaConfiguration.totp2faImplementation() + "))";
	}
	
	private volatile BundleContext bundleContext;
	
	public BundleContext getBundleContext() {
		return bundleContext;
	}

	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	private volatile TOTP_2FAConfiguration tfaConfiguration;	

	@Reference(cardinality=ReferenceCardinality.MANDATORY)
	private UserLocalService userLocalService;
	
	private volatile TOTP_2FAGenerator totpGenerator;
	
	public TOTP_2FAGenerator getTotpGenerator() {
		if (totpGenerator == null) {
			if (_log.isInfoEnabled()) {
				_log.info("TOTP Generator Null, trying to get Impl: " + getFilter());
			}
			
			setTOTPGenerator();
		} 
		return totpGenerator;
	}	

	public void setTotpGenerator(TOTP_2FAGenerator totpGenerator) {
		this.totpGenerator = totpGenerator;
	}
	
	//@Reference(cardinality=ReferenceCardinality.OPTIONAL, target = "(" + TOTP_2FAGenerator.TOTP_IMPL_PROPERTY + "=" + TOTP_2FAGenerator.TOTP_API_IMPLEMENTATIONS.JAVA_OPT + ")")
	//private TOTP_2FAGenerator totpGenerator;
	
	//@Reference(cardinality=ReferenceCardinality.MANDATORY)
	//private TOTP_2FAGenerator totpGenerator;
	
	@Reference(cardinality = ReferenceCardinality.MANDATORY, unbind = "-")
	protected SecretKeyLocalService secretKeyLocalService;

	private static Log _log = LogFactoryUtil.getLog(TOTP_2FAPostAuthenticator.class);	
}