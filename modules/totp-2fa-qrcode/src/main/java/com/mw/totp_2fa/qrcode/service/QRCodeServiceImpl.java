package com.mw.totp_2fa.qrcode.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.mw.totp_2fa.config.TOTP_2FAConfiguration;
import com.mw.totp_2fa.qrcode.constants.QRCodeConstants;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * Service for QR Code related utility methods.
 * 
 * @author Michael Wall
 *
 */
@Component(configurationPid = TOTP_2FAConfiguration.PID, immediate = true, service = QRCodeService.class)
public class QRCodeServiceImpl implements QRCodeService {

	
	/* (non-Javadoc)
	 * @see com.mw.totp_2fa.qrcode.util.QRCodeService#sendEmail(com.liferay.portal.kernel.model.User, java.lang.String)
	 */
	@Override
	public void sendEmail(User user, String secretKeyString) {
		try {
			// Ideally we want to use the Users selected Language / Locale but if not available then the 
			// mail Subject & Body are meaningless plus the URL won't be included, so we check if the keys we are using
			// resolve, and if not, we use en_US.
			String defaultValue = System.currentTimeMillis() + "";
			Locale locale = user.getLocale();

			String test1 = LanguageUtil.get(user.getLocale(), "qrCode.mail.subject", defaultValue);
			String test2 = LanguageUtil.get(user.getLocale(), "qrCode.mail.body", defaultValue);

			if (test1.equalsIgnoreCase(defaultValue) || test2.equalsIgnoreCase(defaultValue)) {
				if (_log.isInfoEnabled())
					_log.info("QR Code URL Email Resource Bundle messages not available for: " + locale.toString() + " so using en_US for mail to: " + user.getEmailAddress());
				
				locale = Locale.US;
			}
			
			InternetAddress fromInternetAddress = new InternetAddress(tfaConfiguration.qrcodeEmailFrom(), tfaConfiguration.qrcodeEmailFromLabel());
			InternetAddress toInternetAddress = new InternetAddress(user.getEmailAddress(), user.getFullName());
			String qrCodeUrl = getQRCodeURL(user, QRCodeConstants.QR_CODE_JWT_URL_TYPE.EMAIL);

			String subject = LanguageUtil.format(locale, "qrCode.mail.subject", tfaConfiguration.applicationName());
			String[] bodyArgs = { tfaConfiguration.applicationName(), qrCodeUrl };
			String body = LanguageUtil.format(locale, "qrCode.mail.body", bodyArgs);

			MailMessage mailMessage = new MailMessage(fromInternetAddress, toInternetAddress, subject, body, true);
			MailServiceUtil.sendEmail(mailMessage);
			
			if (_log.isInfoEnabled()) {
				_log.info("Sent email to: " + user.getEmailAddress());	
			}
		} catch (UnsupportedEncodingException e) {
			_log.error("Exception sending email. ", e);
		}
	}	
	
	/* (non-Javadoc)
	 * @see com.mw.totp_2fa.qrcode.util.QRCodeService#getQRCodeURL(com.liferay.portal.kernel.model.User, java.lang.String)
	 */
	@Override
	public String getQRCodeURL(User user, String type) {
		String qrCodeUrl = tfaConfiguration.qrcodeUrl();
		
		String token = generateQRCodeJWT(user, type, QRCodeConstants.JWT_CLAIM_TYPE.QR_CODE_URL);
		
		qrCodeUrl = qrCodeUrl.replace("{token}", token); //JWT Token
		qrCodeUrl = qrCodeUrl.replace("{t}", String.valueOf(System.currentTimeMillis())); //To prevent caching issues
		
		return qrCodeUrl;
	}
	
	/* (non-Javadoc)
	 * @see com.mw.totp_2fa.qrcode.util.QRCodeService#getQRCodeResendURL(com.liferay.portal.kernel.model.User)
	 */
	@Override
	public String getQRCodeResendURL(User user) {
		String qrCodeUrl = tfaConfiguration.qrcodeResendUrl();
		
		String token = generateQRCodeJWT(user, QRCodeConstants.QR_CODE_JWT_URL_TYPE.WEB, QRCodeConstants.JWT_CLAIM_TYPE.QR_CODE_RESEND);
		
		qrCodeUrl = qrCodeUrl.replace("{token}", token); //JWT Token
		qrCodeUrl = qrCodeUrl.replace("{t}", String.valueOf(System.currentTimeMillis())); //To prevent caching issues
		
		return qrCodeUrl;
	}	
	
	/* (non-Javadoc)
	 * @see com.mw.totp_2fa.qrcode.util.QRCodeService#generateQRCodeJWT(com.liferay.portal.kernel.model.User, java.lang.String, java.lang.String)
	 */
	@Override
	public String generateQRCodeJWT(User user, String type, String claimType) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(tfaConfiguration.qrcodeUrlJWTSecret());
		    String token = JWT.create()
		    	.withIssuer(tfaConfiguration.qrcodeIssuer())
		    	.withClaim("claimType", claimType)
		    	.withClaim("userId", user.getUserId())
		    	.withClaim("type", type)
		    	.withClaim("timestamp", System.currentTimeMillis())
		        .sign(algorithm);
		    
		    return token;
		    
		} catch (JWTCreationException e){
			_log.error(e.getMessage(), e);

			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.mw.totp_2fa.qrcode.util.QRCodeService#validateQRCodeJWT(java.lang.String, java.lang.String)
	 */
	@Override
	public long[] validateQRCodeJWT(String token, String claimType) {
		
		long response[] = {0, -1};
		
		try {
			Algorithm algorithm = Algorithm.HMAC256(tfaConfiguration.qrcodeUrlJWTSecret());
			JWTVerifier verifier = JWT.require(algorithm).withIssuer(tfaConfiguration.qrcodeIssuer()).build();
			DecodedJWT jwt = verifier.verify(token);
		   
		    //Issuer is included as a claim with key iss.
		    if (jwt.getClaims().size() !=5 || jwt.getClaim("claimType").isNull() || jwt.getClaim("userId").isNull() || jwt.getClaim("type").isNull() || jwt.getClaim("timestamp").isNull() || jwt.getClaim("iss").isNull()) {
	    		if (_log.isInfoEnabled()) {
	    			_log.info("QR Code URL invalid (claims not as expected)");
	    		}
		    	
	    		response[0] = QRCodeConstants.JWT_VERIFY_RESPONSE.INVALID_CLAIMS;
	    		
		    	return response;
		    }
		    
		    //Make sure it is a QR Code claim... 
		    if (!jwt.getClaim("claimType").asString().equalsIgnoreCase(claimType)) {
		    	
		    	response[0] = QRCodeConstants.JWT_VERIFY_RESPONSE.INVALID_CLAIM_TYPE;
		    	
		    	return response;
		    }
		    
		    //Resend URL doesn't expire.
		    if (claimType.equalsIgnoreCase(QRCodeConstants.JWT_CLAIM_TYPE.QR_CODE_URL)) {
			    //Check Type Email expiry if applicable
			    if (tfaConfiguration.qrcodeUrlDurationMinutes() > 0) {
				    String type = jwt.getClaim("type").asString();

				    //Email links are valid for specific duration.
				    if (type.equalsIgnoreCase(QRCodeConstants.QR_CODE_JWT_URL_TYPE.EMAIL)) {
				    	long timestamp = jwt.getClaim("timestamp").asLong();
				    	
				    	long duration = tfaConfiguration.qrcodeUrlDurationMinutes() * (long)60 * (long)1000;
				    	
				    	if ((timestamp + duration) < System.currentTimeMillis()) {
				    		if (_log.isInfoEnabled()) {
				    			_log.info("QR Code URL expired (based on JWT timestamp)");
				    		}

				    		response[0] = QRCodeConstants.JWT_VERIFY_RESPONSE.EXPIRED;
				    		response[1] = jwt.getClaim("userId").asLong();
				    		
				    		return response;
				    	}
				    }
			    }		    	
		    }

		    response[0] = QRCodeConstants.JWT_VERIFY_RESPONSE.SUCCESS;
		    response[1] = jwt.getClaim("userId").asLong();

		    return  response;
		} catch (JWTVerificationException e){
			_log.error(e.getMessage(), e);
			
			response[0] = QRCodeConstants.JWT_VERIFY_RESPONSE.VERIFY_FAILED;
		    
		    return response;
		}
	}
	
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		tfaConfiguration = ConfigurableUtil.createConfigurable(TOTP_2FAConfiguration.class, properties);
	}

	private volatile TOTP_2FAConfiguration tfaConfiguration;		

	private static Log _log = LogFactoryUtil.getLog(QRCodeServiceImpl.class);		
}