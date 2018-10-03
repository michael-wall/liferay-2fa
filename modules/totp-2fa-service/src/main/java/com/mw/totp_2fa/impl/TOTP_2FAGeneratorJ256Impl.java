package com.mw.totp_2fa.impl;

import com.j256.twofactorauth.TimeBasedOneTimePasswordUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mw.totp_2fa.api.TOTP_2FAGenerator;

import java.security.GeneralSecurityException;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, property = { TOTP_2FAGenerator.TOTP_IMPL_PROPERTY + "=" + TOTP_2FAGenerator.TOTP_API_IMPLEMENTATIONS.J256 }, service = TOTP_2FAGenerator.class)
public class TOTP_2FAGeneratorJ256Impl implements TOTP_2FAGenerator {

	@Override
	public String getTOTPCode(String secretKey, int authenticatorCodeLength) {

        try {
			String totpCode = TimeBasedOneTimePasswordUtil.generateCurrentNumberString(secretKey);
			
			if (_log.isDebugEnabled()) {
				_log.debug("TOTP_2FAGeneratorJ256Impl.getTOTPCode: " + totpCode);
			}
			
			return totpCode;
		} catch (GeneralSecurityException e) {
			_log.error("GeneralSecurityException generating TOTP with secretKey, " + e.getClass().getCanonicalName() + ", " + e.getMessage(), e);
			
			return null;
		}
	}
	
	private static Log _log = LogFactoryUtil.getLog(TOTP_2FAGeneratorJ256Impl.class);	
}
