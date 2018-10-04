package com.mw.totp_2fa.qrcode.usersAdmin;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mw.totp_2fa.key.model.SecretKey;
import com.mw.totp_2fa.qrcode.constants.QRCodeConstants;
import com.mw.totp_2fa.qrcode.service.QRCodeService;
import com.mw.totp_2fa.util.TOTP_2FAUtil;

import java.io.IOException;

import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.RenderFilter;

/**
 * Abstract class to remove duplication for QR Code content on Password section.
 * 
 * @author Michael Wall
 *
 */
public abstract class AbstractProfilePortletFilter implements RenderFilter{

	@Override
	public void init(FilterConfig filterConfig) throws PortletException {

	}

	@Override
	public void destroy() {

	}

	@Override
	public abstract void doFilter(RenderRequest request, RenderResponse response, FilterChain chain)
			throws IOException, PortletException;
	
	public String getContent(boolean isUserAdminScreen, boolean showSecretKeysOnAccountScreens, QRCodeService qrCodeService, boolean hasSecretKey, String portletId, RenderRequest request, User user, SecretKey secretKeyObject) {
		StringBuffer customText = new StringBuffer();

		String generateSecretKeyLabel = null;

		//Custom label based on screen type and whether the user already has a secret key...
		if (isUserAdminScreen) {
			generateSecretKeyLabel = LanguageUtil.get(request.getLocale(), "generate-secret-key-and-email-qr-code");
			
			if (hasSecretKey) {
				generateSecretKeyLabel = LanguageUtil.get(request.getLocale(), "regenerate-secret-key-and-email-qr-code");
			}
		} else {
			generateSecretKeyLabel = LanguageUtil.get(request.getLocale(), "generate-secret-key");
			
			if (hasSecretKey) {
				generateSecretKeyLabel = LanguageUtil.get(request.getLocale(), "regenerate-secret-key");
			}
		}

		String tfaRequiredOnLoginLabel = LanguageUtil.get(request.getLocale(),
				"2fa-required-on-login-when-2fa-enabled");
		
		String anyUnsavedChangesLabel = LanguageUtil.get(request.getLocale(),
				"any-unsaved-changed-to-this-screen-will-be-lost");

		boolean[] tfaSkip = TOTP_2FAUtil.isAdministratorOrSkipUserRole(user);

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		//The Action URL to call the custom MVCActionCommand
		LiferayPortletURL liferayPortletURL = PortletURLFactoryUtil.create(request,
				portletId, PortletRequest.ACTION_PHASE);
		liferayPortletURL.setParameter("p_u_i_d", String.valueOf(user.getUserId()));
		if (hasSecretKey) {//Regenerating
			liferayPortletURL.setParameter("javax.portlet.action", "/users_admin/regenerate_2fa_secret_key");
		} else {//Generating for first time
			liferayPortletURL.setParameter("javax.portlet.action", "/users_admin/generate_2fa_secret_key");
		}
		
		liferayPortletURL.setParameter("redirect", themeDisplay.getURLCurrent());
		liferayPortletURL.setParameter("sendEmail", Boolean.toString(isUserAdminScreen)); //Send an email only if not myAccount.

		// TODO MW Cleanup / make a button etc.
		customText.append("<a href=\"" + liferayPortletURL.toString() + "\">" + generateSecretKeyLabel + "</a>&nbsp;" + anyUnsavedChangesLabel);
		customText.append("<br>");
		customText.append("<strong>" + tfaRequiredOnLoginLabel + "</strong>");
		customText.append("<br>");
		
		//Whether the user (currently) needs a code to login (where 2FA enabled)
		if (!tfaSkip[0]) {
			String yesLabel = LanguageUtil.get(request.getLocale(), "yes");
			customText.append(yesLabel);
		} else {
			String noLabel = LanguageUtil.get(request.getLocale(), "no");
			customText.append(noLabel);
		}

		if (hasSecretKey) { // Show the secret key and the QR Code image
			if (showSecretKeysOnAccountScreens) {
				String secretKeyString = secretKeyObject.getSecretKey();
				String secretKeyLabel = LanguageUtil.get(request.getLocale(), "2fa-secret-key");
				
				customText.append("<br><br>");
				customText.append("<strong>" + secretKeyLabel + "</strong>");
				customText.append("<br>");
				customText.append(secretKeyString);				
			}
			
			String qrCodeUrl = qrCodeService.getQRCodeURL(user, QRCodeConstants.QR_CODE_JWT_URL_TYPE.WEB);
			String qrCodeLabel = LanguageUtil.get(request.getLocale(), "2fa-qr-code");

			customText.append("<br><br>");
			customText.append("<strong>" + qrCodeLabel + "</strong>");
			customText.append("<br>");
			customText.append("<img src=\"" + qrCodeUrl + "\" alt=\"" + qrCodeLabel + "\" height=\""
					+ QRCodeConstants.QR_CODE_HEIGHT + "\" width=\"" + QRCodeConstants.QR_CODE_WIDTH + "\">");
		}
		
		return customText.toString();
	}
	
	private static Log _log = LogFactoryUtil.getLog(AbstractProfilePortletFilter.class);
}