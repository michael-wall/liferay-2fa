package com.mw.totp_2fa.qrcode.servlet;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.mw.totp_2fa.config.TOTP_2FAConfiguration;
import com.mw.totp_2fa.key.model.SecretKey;
import com.mw.totp_2fa.key.service.SecretKeyLocalService;
import com.mw.totp_2fa.qrcode.constants.QRCodeConstants;
import com.mw.totp_2fa.qrcode.service.QRCodeService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * Servlet to resend a QR Code email based on the provided JWT token. See QRCodeService for JWT Token syntax.
 * 
 * @author Michael Wall
 *
 */
@Component(
immediate = true,
property = { 
"osgi.http.whiteboard.context.path=/",
"osgi.http.whiteboard.servlet.name=com.mw.totp_2fa.qrcode.servlet.QRCodeResendServlet",
"osgi.http.whiteboard.servlet.pattern=/qrcoderesend", 
},
configurationPid = TOTP_2FAConfiguration.PID,
service = Servlet.class)
public class QRCodeResendServlet extends HttpServlet {
	private static final long serialVersionUID = 7740021116243562871L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		ServletOutputStream outStream = null;

		try {	
			outStream = resp.getOutputStream();
			
			//Extract params.
			// Token is the JWT token
			//t is timestamp in case of caching
			String token = ParamUtil.getString(req, "token", null);
			long t = ParamUtil.getLong(req, "t", -1);

			if (Validator.isNull(token) || t == -1) {
				outStream.println(LanguageUtil.get(req.getLocale(), "qr-code-resend-url-missing-expected-parameters"));
				
				return;
			}
			
			long[] validateJWTResponse = qrCodeService.validateQRCodeJWT(token, QRCodeConstants.JWT_CLAIM_TYPE.QR_CODE_RESEND);
			User user = null;
			
			//Check response from validateQRCodeJWT
			if (validateJWTResponse[0] == QRCodeConstants.JWT_VERIFY_RESPONSE.SUCCESS) {
				user = userLocalService.fetchUser(validateJWTResponse[1]);
			} else {
				outStream.println(LanguageUtil.get(req.getLocale(), "qr-code-resend-url-invalid"));
				
				return;
			}
			
			if (user == null || !user.isActive()) {
				outStream.println(LanguageUtil.get(req.getLocale(), "matching-active-user-not-found"));
				
				return;
			}
			
			//Retrieve the users secretKEy so we can generate the QR Code specific to that user.
			SecretKey secretKey = secretKeyLocalService.fetchSecretKeyByUserId(user.getCompanyId(), user.getUserId());
			
			if (secretKey == null || Validator.isNull(secretKey.getSecretKey())) {
				outStream.println(LanguageUtil.get(req.getLocale(), "secret-key-not-found-for-this-user-please-contact-the-system-administrators"));
				
				return;
			}
			
			qrCodeService.sendEmail(user, secretKey.getSecretKey(), true);

			//Return to the user...
			outStream.println(LanguageUtil.get(req.getLocale(), "qr-code-url-has-been-resent-to-the-account-email-address"));
			
			//Prevent caching.
			resp.setHeader("Cache-Control","private, no-cache, no-store, must-revalidate");
		} catch (Exception e) {
			_log.error(e);
			
			outStream.println(LanguageUtil.get(req.getLocale(), "an-error-occurred-resending-the-qr-code-url"));
		} finally {
			if (outStream != null) outStream.close();
		}
	}
	
	private byte[] getQRCodeImage(String text, int width, int height) throws WriterException, IOException {
	    QRCodeWriter qrCodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
	    
	    ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
	    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
	    byte[] pngData = pngOutputStream.toByteArray(); 
	    return pngData;
	}
	
	@Activate
	@Modified
	protected void activate(BundleContext bundleContext, Map<String, Object> properties){		
		tfaConfiguration = ConfigurableUtil.createConfigurable(TOTP_2FAConfiguration.class, properties);

		if (_log.isInfoEnabled()) {
			_log.info("*********************************************");
			_log.info("tfaConfiguration.qrcodeIssuer: " + tfaConfiguration.qrcodeIssuer());
			_log.info("tfaConfiguration.qrcodeUrl: " + tfaConfiguration.qrcodeUrl());
			_log.info("*********************************************");
		}
	}	
	
	private volatile TOTP_2FAConfiguration tfaConfiguration;
	
	@Reference(cardinality=ReferenceCardinality.MANDATORY)
	private UserLocalService userLocalService;
	
	@Reference(cardinality = ReferenceCardinality.MANDATORY, unbind = "-")
	private SecretKeyLocalService secretKeyLocalService;
	
	@Reference(cardinality = ReferenceCardinality.MANDATORY, unbind = "-")
	private QRCodeService qrCodeService;
	
	private static Log _log = LogFactoryUtil.getLog(QRCodeResendServlet.class);		
}
