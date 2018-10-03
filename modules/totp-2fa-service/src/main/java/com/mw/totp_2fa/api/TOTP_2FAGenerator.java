package com.mw.totp_2fa.api;

public interface TOTP_2FAGenerator {
	public static final long AUTHENTICATOR_CODE_DURATION = 30; // Intentionally not externalized.
	
	public interface TOTP_API_IMPLEMENTATIONS {
		public static final String JAVA_OPT = "java-otp";
		public static final String J256 = "j256 two-factor-auth";
	}
	
	public interface TOTP_API_LABELS {
		public static final String JAVA_OPT = "java-otp";
		public static final String J256 = "j256 two-factor-auth";
	}
	
	public static final String TOTP_IMPL_PROPERTY = "totpGeneratorImpl";

	String getTOTPCode(String secretKey, int authenticatorCodeLength);
}