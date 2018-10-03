package com.mw.totp_2fa.qrcode.service;

import com.liferay.portal.kernel.model.User;

public interface QRCodeService {

	/**
	 * Uses MailServiceUtil.sendEmail to send an email to the user. The email contains a link to view the QR Code
	 * 
	 * @param user
	 * @param type
	 * @param secretKeyString
	 */
	void sendEmail(User user, String secretKeyString, boolean includeFooter);

	/**
	 * Generate QR Code URL from configuration and user. Details passed in JWT to avoid tampering.
	 * 
	 * @param user
	 * @param type
	 * @return
	 */
	String getQRCodeURL(User user, String type);
	
	/**
	 * Generate QR Code Resend URL from configuration and user. Details passed in JWT to avoid tampering.
	 * 
	 * @param user
	 * @param type
	 * @return
	 */	
	String getQRCodeResendURL(User user);

	/**
	 * Generate JWT used in the QR Code URLs. Any changes to the claims must be matched by validateQRCodeJWT. Issuer will be added as a claim with key iss.
	 * 
	 * @param user
	 * @param type
	 * @return
	 */
	String generateQRCodeJWT(User user, String type, String claimType);

	/**
	 * Validate the JWT and returns the userId. Validates with the secret, checks it contains the 3 expected claims and checks for expired if it is of type EMAIL and claimType QRCodeResend.
	 * 
	 * @param token
	 * @return
	 */
	long[] validateQRCodeJWT(String token, String claimType);

}