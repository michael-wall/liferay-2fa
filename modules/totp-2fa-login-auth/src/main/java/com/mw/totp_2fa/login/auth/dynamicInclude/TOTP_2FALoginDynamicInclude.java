package com.mw.totp_2fa.login.auth.dynamicInclude;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.mw.totp_2fa.config.TOTP_2FAConfiguration;
import com.mw.totp_2fa.login.auth.constants.LoginConstants;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

@Component(
    immediate = true,
    service = DynamicInclude.class,
    configurationPid = TOTP_2FAConfiguration.PID
)
public class TOTP_2FALoginDynamicInclude implements DynamicInclude {

	@Override
	public void include(HttpServletRequest request, HttpServletResponse response, String key) throws IOException {
		
		if (tfaConfiguration.loginTotp2faEnabled()) {
			PrintWriter printWriter = response.getWriter();
			
			//Google Authenticator App displays with a space, so allow for xxx xxx or xx xx xx syntax.
			int authenticatorCodeMaxLength = tfaConfiguration.authenticatorCodeLength() + 2;

			printWriter.println("<div class=\"form-group input-text-wrapper\">");
			printWriter.println("<label class=\"control-label\" for=\"" + LoginConstants.AUTHENTICATOR_CODE_FIELD + "\">");
			printWriter.println(LanguageUtil.get(request.getLocale(), "authenticator-code"));
			printWriter.println("</label>");
			printWriter.println("<input class=\"field form-control\" id=\"" + LoginConstants.AUTHENTICATOR_CODE_FIELD
					+ "\" name=\"" + LoginConstants.AUTHENTICATOR_CODE_FIELD + "\" type=\"text\" maxlength=\""
					+ authenticatorCodeMaxLength + "\" autocomplete=\"off\" value=\"\" aria-required=\"true\">");
			printWriter.println("</div>");			
		}
	}

    @Override
    public void register(DynamicIncludeRegistry dynamicIncludeRegistry) {

    	// Note: loginFieldsPost is a custom dynamic include added to login.jsp added by totp-login-fragment module.
    	
    	if (tfaConfiguration.loginTotp2faEnabled()) {
    	       dynamicIncludeRegistry.register(
    	    	         "com.liferay.login.web#/login.jsp#loginFieldsPost"); 		
    	       
//    	       dynamicIncludeRegistry.register(
//  	    	         "com.liferay.login.web#/login.jsp#alertPost");
    	}
    }

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		tfaConfiguration = ConfigurableUtil.createConfigurable(TOTP_2FAConfiguration.class, properties);

		if (_log.isInfoEnabled()) {
			_log.info("*********************************************");
			_log.info("tfaConfiguration.loginTotp2faEnabled: " + tfaConfiguration.loginTotp2faEnabled());
			_log.info("tfaConfiguration.authenticatorCodeLength: " + tfaConfiguration.authenticatorCodeLength());
			_log.info("*********************************************");
		}
	}

	private volatile TOTP_2FAConfiguration tfaConfiguration;
	
	private static Log _log = LogFactoryUtil.getLog(TOTP_2FALoginDynamicInclude.class);	
}