package com.mw.totp_2fa.qrcode.constants;

public class QRCodeConstants {

	public static final int QR_CODE_HEIGHT = 300;
	public static final int QR_CODE_WIDTH = 300;

	public interface JWT_CLAIM_TYPE {
		public static final String QR_CODE_URL = "QRCodeURL";
		public static final String QR_CODE_RESEND = "QRCodeResend";
	}
	
	public interface QR_CODE_JWT_URL_TYPE {
		public static final String WEB = "web";
		public static final String EMAIL = "email";
	}
	
	public interface JWT_VERIFY_RESPONSE {
		public static final long SUCCESS = 1;
		public static final long VERIFY_FAILED = -1;
		public static final long EXPIRED = -2;
		public static final long INVALID_CLAIMS = -3;
		public static final long INVALID_CLAIM_TYPE = -4;
	}
}
