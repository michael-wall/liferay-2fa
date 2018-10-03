package com.mw.totp_2fa.impl;

import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mw.totp_2fa.api.TOTP_2FAGenerator;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { TOTP_2FAGenerator.TOTP_IMPL_PROPERTY + "=" + TOTP_2FAGenerator.TOTP_API_IMPLEMENTATIONS.JAVA_OPT }, service = TOTP_2FAGenerator.class)
public class TOTP_2FAGeneratorJavaOptImpl implements TOTP_2FAGenerator {

	@Override
	public String getTOTPCode(String secretKey, int authenticatorCodeLength) {
		
		TimeBasedOneTimePasswordGenerator totpGenerator = null;

		try {
			totpGenerator = new TimeBasedOneTimePasswordGenerator(AUTHENTICATOR_CODE_DURATION, TimeUnit.SECONDS, authenticatorCodeLength, TimeBasedOneTimePasswordGenerator.TOTP_ALGORITHM_HMAC_SHA1);

			Base32 base32 = new Base32();
			byte[] optBytes = base32.decode(secretKey);
			
			SecretKeySpec optSecretKey = new SecretKeySpec(optBytes, TimeBasedOneTimePasswordGenerator.TOTP_ALGORITHM_HMAC_SHA1);
			
			String toptCode = String.format("%0" + authenticatorCodeLength + "d", totpGenerator.generateOneTimePassword(optSecretKey, new Date()));
			
			if (_log.isDebugEnabled()) {
				_log.debug("TOTP_2FAGeneratorJavaOptImpl.getTOTPCode: " + toptCode);
			}
			
			return toptCode;
			
		} catch (NoSuchAlgorithmException e) {
			_log.error("NoSuchAlgorithmException generating TOTP with secretKey and authenticatorCodeLength: " + authenticatorCodeLength + ", " + e.getClass().getCanonicalName() + ", " + e.getMessage(), e);
			
			return null;
		} catch (InvalidKeyException e) {
			_log.error("InvalidKeyException generating TOTP with secretKey and authenticatorCodeLength: " + authenticatorCodeLength + ", " + e.getClass().getCanonicalName() + ", " + e.getMessage(), e);
			
			return null;
		}
	}
	
	private static Log _log = LogFactoryUtil.getLog(TOTP_2FAGeneratorJavaOptImpl.class);	
}