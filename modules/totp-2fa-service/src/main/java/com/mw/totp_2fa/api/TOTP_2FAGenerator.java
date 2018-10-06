package com.mw.totp_2fa.api;

public interface TOTP_2FAGenerator {
	
	public interface TOTP_API_IMPLEMENTATIONS {
		public static final String JAVA_OPT = "java-otp";
		public static final String J256 = "j256 two-factor-auth";
	}
	
	public interface TOTP_API_LABELS {
		public static final String JAVA_OPT = "java-otp";
		public static final String J256 = "j256 two-factor-auth";
	}
	
	public static final String TOTP_IMPL_PROPERTY = "totpGeneratorImpl";

	/**
	 * Get TOTP for provided timestamp.
	 * 
	 * @param secretKey
	 * @param authenticatorCodeLength
	 * @param authenticatorCodeDuration
	 * @param time
	 * @return
	 */
	String getTOTPCode(String secretKey, int authenticatorCodeLength, int authenticatorCodeDuration, long time);
	
	/**
	 * Get previous TOTP, current TOTP and next TOTP (in that order) based on provided timestamp
	 * 
	 * @param secretKey
	 * @param authenticatorCodeLength
	 * @param authenticatorCodeDuration
	 * @param time
	 * @return
	 */
	String[] getTOTPCodes(String secretKey, int authenticatorCodeLength, int authenticatorCodeDuration, long time);
	
	/**
	 * Check if the provided code matches the generated one(s).
	 * 
	 * @param identifier
	 * @param userProvidedCode
	 * @param allowForTimeSkew
	 * @param secretKey
	 * @param authenticatorCodeLength
	 * @param authenticatorCodeDuration
	 * @param time
	 * @return
	 */
	public boolean isMatch(String identifier, String userProvidedCode, boolean allowForTimeSkew, String secretKey, int authenticatorCodeLength, int authenticatorCodeDuration, long time);
}