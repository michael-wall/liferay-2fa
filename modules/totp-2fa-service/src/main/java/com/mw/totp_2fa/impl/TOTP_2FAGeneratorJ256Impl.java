package com.mw.totp_2fa.impl;

import com.j256.twofactorauth.TimeBasedOneTimePasswordUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mw.totp_2fa.api.TOTP_2FAGenerator;

import java.security.GeneralSecurityException;
import java.util.Arrays;

import org.osgi.service.component.annotations.Component;

/**
 * J256 implementation of TOTP.
 * 
 * @author Michael Wall
 *
 */
@Component(immediate = true, property = { TOTP_2FAGenerator.TOTP_IMPL_PROPERTY + "=" + TOTP_2FAGenerator.TOTP_API_IMPLEMENTATIONS.J256 }, service = TOTP_2FAGenerator.class)
public class TOTP_2FAGeneratorJ256Impl implements TOTP_2FAGenerator {
	
	private final static String LOG_PREFIX = "TOTP_2FAGeneratorJ256Impl";
	
	public static final int EXPECTED_AUTHENTICATOR_CODE_LENGTH = 6;

	/* (non-Javadoc)
	 * @see com.mw.totp_2fa.api.TOTP_2FAGenerator#isMatch(java.lang.String, java.lang.String, boolean, java.lang.String, int, int, long)
	 */
	public boolean isMatch(String identifier, String userProvidedCode, boolean allowForTimeSkew, String secretKey, int authenticatorCodeLength, int authenticatorCodeDuration, long time) {
		
		if (_log.isErrorEnabled()) {
			_log.error(LOG_PREFIX + " authenticatorCodeLength greater than 6, comparison will be based on last 6 digits only for: " + identifier);
		}
		
		if (authenticatorCodeLength > EXPECTED_AUTHENTICATOR_CODE_LENGTH) {
			userProvidedCode = userProvidedCode.substring(authenticatorCodeLength - EXPECTED_AUTHENTICATOR_CODE_LENGTH);
		}
		
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
			String totpCode = TimeBasedOneTimePasswordUtil.generateNumberString(secretKey, time, authenticatorCodeDuration);

			return totpCode;
		} catch (GeneralSecurityException e) {
			_log.error("GeneralSecurityException generating TOTP with secretKey, " + e.getClass().getCanonicalName() + ", " + e.getMessage(), e);
			
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
        	totpCodes[0] = TimeBasedOneTimePasswordUtil.generateNumberString(secretKey, (time - (authenticatorCodeDuration * 1000)), authenticatorCodeDuration);
        	totpCodes[1] = TimeBasedOneTimePasswordUtil.generateNumberString(secretKey, time, authenticatorCodeDuration);
        	totpCodes[2] = TimeBasedOneTimePasswordUtil.generateNumberString(secretKey, (time + (authenticatorCodeDuration * 1000)), authenticatorCodeDuration);

			return totpCodes;
		} catch (GeneralSecurityException e) {
			_log.error("GeneralSecurityException generating TOTPs with secretKey, " + e.getClass().getCanonicalName() + ", " + e.getMessage(), e);
			
			return null;
		}
	}	
	
	private static Log _log = LogFactoryUtil.getLog(TOTP_2FAGeneratorJ256Impl.class);	
}
