package com.mw.totp_2fa.impl;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mw.totp_2fa.api.TOTP_2FAGenerator;
import com.mw.totp_2fa.config.TOTP_2FAConfiguration;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.osgi.service.component.annotations.Component;

/**
 * Java opt implementation of TOTP.
 * 
 * @author Michael Wall
 *
 */
@Component(immediate = true, property = { TOTP_2FAGenerator.TOTP_IMPL_PROPERTY + "=" + TOTP_2FAGenerator.TOTP_API_IMPLEMENTATIONS.JAVA_OPT }, service = TOTP_2FAGenerator.class)
public class TOTP_2FAGeneratorJavaOptImpl implements TOTP_2FAGenerator {
	
	private final static String LOG_PREFIX = "TOTP_2FAGeneratorJavaOptImpl";

	/* (non-Javadoc)
	 * @see com.mw.totp_2fa.api.TOTP_2FAGenerator#isMatch(java.lang.String, java.lang.String, boolean, java.lang.String, int, int, long)
	 */
	public boolean isMatch(String identifier, String userProvidedCode, boolean allowForTimeSkew, String secretKey, int authenticatorCodeLength, int authenticatorCodeDuration, long time) {
		if (!allowForTimeSkew) {
			String generatedAuthenticatorCode = getTOTPCode(secretKey,authenticatorCodeLength, authenticatorCodeDuration, time);
			
			if (_log.isDebugEnabled()) {
				_log.debug(LOG_PREFIX + " authenticatorCode: " + userProvidedCode + ", generatedAuthenticatorCode: " + generatedAuthenticatorCode + " for: " + identifier);
			}
			
			if (Validator.isNotNull(generatedAuthenticatorCode) && userProvidedCode.equalsIgnoreCase(generatedAuthenticatorCode)) {
				return true;
			}
		} else {
			String[] generatedAuthenticatorCode = getTOTPCodes(secretKey,authenticatorCodeLength, authenticatorCodeDuration, time);
			
			if (_log.isDebugEnabled()) {
				_log.debug(LOG_PREFIX + " authenticatorCode: " + userProvidedCode + ", generatedAuthenticatorCodes: " + Arrays.toString(generatedAuthenticatorCode) + " for: " + identifier);
			}

			for (int i = 0; i < generatedAuthenticatorCode.length; i++) {
				if (Validator.isNotNull(generatedAuthenticatorCode[i]) && userProvidedCode.equalsIgnoreCase(generatedAuthenticatorCode[i])) {
					return true;
				}
			}
		}

		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.mw.totp_2fa.api.TOTP_2FAGenerator#getTOTPCode(java.lang.String, int, int, long)
	 */
	@Override
	public String getTOTPCode(String secretKey, int authenticatorCodeLength, int authenticatorCodeDuration, long time) {

		try {
			TimeBasedOneTimePasswordGenerator totpGenerator = new TimeBasedOneTimePasswordGenerator(authenticatorCodeDuration, TimeUnit.SECONDS, authenticatorCodeLength, TimeBasedOneTimePasswordGenerator.TOTP_ALGORITHM_HMAC_SHA1);				

			Base32 base32 = new Base32();
			byte[] optBytes = base32.decode(secretKey);
			
			SecretKeySpec optSecretKey = new SecretKeySpec(optBytes, TimeBasedOneTimePasswordGenerator.TOTP_ALGORITHM_HMAC_SHA1);
			
			String totpCode = String.format("%0" + authenticatorCodeLength + "d", totpGenerator.generateOneTimePassword(optSecretKey, new Date(time)));
			
			return totpCode;
			
		} catch (NoSuchAlgorithmException e) {
			_log.error("NoSuchAlgorithmException generating TOTP with secretKey and authenticatorCodeLength: " + authenticatorCodeLength + ", " + e.getClass().getCanonicalName() + ", " + e.getMessage(), e);
			
			return null;
		} catch (InvalidKeyException e) {
			_log.error("InvalidKeyException generating TOTP with secretKey and authenticatorCodeLength: " + authenticatorCodeLength + ", " + e.getClass().getCanonicalName() + ", " + e.getMessage(), e);
			
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.mw.totp_2fa.api.TOTP_2FAGenerator#getTOTPCodes(java.lang.String, int, int, long)
	 */
	@Override
	public String[] getTOTPCodes(String secretKey, int authenticatorCodeLength, int authenticatorCodeDuration, long time) {

		String totpCodes[] = new String[3];
		
		try {
			TimeBasedOneTimePasswordGenerator totpGenerator = new TimeBasedOneTimePasswordGenerator(authenticatorCodeDuration, TimeUnit.SECONDS, authenticatorCodeLength, TimeBasedOneTimePasswordGenerator.TOTP_ALGORITHM_HMAC_SHA1);				

			Base32 base32 = new Base32();
			byte[] optBytes = base32.decode(secretKey);
			
			SecretKeySpec optSecretKey = new SecretKeySpec(optBytes, TimeBasedOneTimePasswordGenerator.TOTP_ALGORITHM_HMAC_SHA1);
			
			totpCodes[0] = String.format("%0" + authenticatorCodeLength + "d", totpGenerator.generateOneTimePassword(optSecretKey, new Date(time - (authenticatorCodeDuration * 1000))));
			totpCodes[1] = String.format("%0" + authenticatorCodeLength + "d", totpGenerator.generateOneTimePassword(optSecretKey, new Date(time)));
			totpCodes[2] = String.format("%0" + authenticatorCodeLength + "d", totpGenerator.generateOneTimePassword(optSecretKey, new Date(time + (authenticatorCodeDuration * 1000))));

			return totpCodes;
			
		} catch (NoSuchAlgorithmException e) {
			_log.error("NoSuchAlgorithmException generating TOTPs with secretKey and authenticatorCodeLength: " + authenticatorCodeLength + ", " + e.getClass().getCanonicalName() + ", " + e.getMessage(), e);
			
			return null;
		} catch (InvalidKeyException e) {
			_log.error("InvalidKeyException generating TOTPs with secretKey and authenticatorCodeLength: " + authenticatorCodeLength + ", " + e.getClass().getCanonicalName() + ", " + e.getMessage(), e);
			
			return null;
		}
	}	
	
	private static Log _log = LogFactoryUtil.getLog(TOTP_2FAGeneratorJavaOptImpl.class);	
}