package com.mw.totp_2fa.qrcode.usersAdmin;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.users.admin.constants.UsersAdminPortletKeys;
import com.mw.totp_2fa.config.TOTP_2FAConfiguration;
import com.mw.totp_2fa.key.model.SecretKey;
import com.mw.totp_2fa.key.service.SecretKeyLocalService;
import com.mw.totp_2fa.qrcode.service.QRCodeService;

import java.io.IOException;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderResponseWrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * Include the users 2FA Secret Key and QR Code in the Password section of Users Admin portlet and ability to generate / regenerate.
 * 
 * SecretKey and QR Code are shown for any user that has a SecretKey, regardless of whether they are an Administrator or in the 'Login TOTP 2FA Skip User Role' group.
 * 
 * @author Michael Wall
 *
 */
@Component(
	    immediate = true,
	    configurationPid = TOTP_2FAConfiguration.PID,
	    property = {
	         "javax.portlet.name=" + UsersAdminPortletKeys.USERS_ADMIN	         
	    },
	    service = PortletFilter.class)
public class UsersAdminPortletFilter extends AbstractProfilePortletFilter {

	@Override
	public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain)
			throws IOException, PortletException {

		RenderResponseWrapper renderResponseWrapper = new BufferedRenderResponseWrapper(response);
		
		chain.doFilter(request, renderResponseWrapper);
		
		long userId = ParamUtil.getLong(request, "p_u_i_d", -1);
		String mvcRenderCommandName = ParamUtil.getString(request, "mvcRenderCommandName", "");
		String screenNavigationEntryKey = ParamUtil.getString(request, "screenNavigationEntryKey", "");
		
		String text = renderResponseWrapper.toString();

		// We are appending the Secret Key and QR Code to the password section only, before the fieldset tag is closed.
		if (userId <= -1 || !mvcRenderCommandName.equalsIgnoreCase("/users_admin/edit_user") || !screenNavigationEntryKey.equalsIgnoreCase("password")) {
			response.getWriter().write(text);
			
			return;
		}
		
		User user = userLocalService.fetchUser(userId);
		
		//Don't do anything if user not found or user is not active.
		if (user == null || !user.isActive()) {
			response.getWriter().write(text);
			
			return;
		}
		
		SecretKey secretKeyObject = secretKeyLocalService.fetchSecretKeyByUserId(user.getCompanyId(), user.getUserId());
		
		boolean hasSecretKey = false;
		
		if (secretKeyObject != null && !Validator.isNull(secretKeyObject.getSecretKey())) {
			hasSecretKey = true;
		}
		
		Document doc = Jsoup.parse(text);
		Element form = doc.select("form").first();
		form.append(getContent(true, tfaConfiguration.showSecretKeysOnAccountScreens(), qrCodeService, hasSecretKey, UsersAdminPortletKeys.USERS_ADMIN, request, user, secretKeyObject));
		
		response.getWriter().write(doc.html());
	}
	
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_log.info("activate!");
		
		tfaConfiguration = ConfigurableUtil.createConfigurable(TOTP_2FAConfiguration.class, properties);
	}

	private volatile TOTP_2FAConfiguration tfaConfiguration;	

	@Reference(cardinality = ReferenceCardinality.MANDATORY, unbind = "-")
	protected UserLocalService userLocalService;
	
	@Reference(cardinality = ReferenceCardinality.MANDATORY, unbind = "-")
	protected SecretKeyLocalService secretKeyLocalService;
	
	@Reference(cardinality = ReferenceCardinality.MANDATORY, unbind = "-")
	protected QRCodeService qrCodeService;	

	private static Log _log = LogFactoryUtil.getLog(UsersAdminPortletFilter.class);
}
