package com.mw.totp_2fa.login.portlet.filter;

import com.liferay.login.web.constants.LoginPortletKeys;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.mw.totp_2fa.config.TOTP_2FAConfiguration;
import com.mw.totp_2fa.login.auth.constants.LoginConstants;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;
import javax.portlet.filter.RenderResponseWrapper;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * Adds the Authenticator Code field to the Login Portlet login form below the Password field if tfaConfiguration.loginTotp2faEnabled true.
 *
 * @author Michael Wall
 */
@Component(
	    immediate = true,
	    configurationPid = TOTP_2FAConfiguration.PID,
	    property = {
	         "javax.portlet.name=" + LoginPortletKeys.LOGIN,
	         "javax.portlet.name=" + LoginPortletKeys.FAST_LOGIN,
	    },
	    service = PortletFilter.class)
public class LoginPortletFilter implements RenderFilter{
	
	private static final String formOpenTag = "<form";
	private static final String formCloseTag = "</form>";
	private static final String formNames[] = { "name=\"_" + LoginPortletKeys.LOGIN + "_loginFormModal\"", "name=\"_" + LoginPortletKeys.LOGIN + "_loginForm\""};
	private static final String formIds[] = { "id=\"_" + LoginPortletKeys.LOGIN + "_loginFormModal\"", "id=\"_" + LoginPortletKeys.LOGIN + "_loginForm\""};
	private static final String formFields[] = { "id=\"_" + LoginPortletKeys.LOGIN + "_login\"", "id=\"_" + LoginPortletKeys.LOGIN + "_password\""};
	private static final String passwordCapsLockSpan = "id=\"_" + LoginPortletKeys.LOGIN + "_passwordCapsLockSpan\"";
	private static final String spanCloseTag = "</span>";
	
	@Override
	public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain)
			throws IOException, PortletException {

		RenderResponseWrapper renderResponseWrapper = new BufferedRenderResponseWrapper(response);
		
		chain.doFilter(request, renderResponseWrapper);
		
		String text = renderResponseWrapper.toString();
		
		int formOpenTagIndex = text.indexOf(formOpenTag);
		int formCloseTagIndex = text.indexOf(formCloseTag);
		
		//Enabled and has a form...
		if (!tfaConfiguration.loginTotp2faEnabled() || formOpenTagIndex < 0 || formCloseTagIndex < 0) {
			response.getWriter().write(text);
			
			return;
		}
		
		boolean formNameMatch = false;
		
		// Login form has one of 2 names depending on whether modal dialog or not, check if either form name is present
		for (int i = 0; i < formNames.length; i++) {
			if (text.indexOf(formNames[i]) >= 0) {
				formNameMatch = true;
				
				break;
			}
		}
		
		if (!formNameMatch) {
			response.getWriter().write(text);
			
			return;
		}
		
		boolean formIdMatch = false;
		String formIdAttribute = null;
		
		// Login form has one of 2 ids depending on whether modal dialog or not, check if either form name is present
		for (int i = 0; i < formIds.length; i++) {
			if (text.indexOf(formIds[i]) >= 0) {
				formIdMatch = true;
				formIdAttribute = formIds[i];
				
				break;
			}
		}
		
		if (!formIdMatch) {
			response.getWriter().write(text);
			
			return;
		}
		
		int formFieldsMatchCount = 0;
		
		//Check login and password field ids present
		for (int i = 0; i < formFields.length; i++) {
			if (text.indexOf(formFields[i]) >= 0) {
				formFieldsMatchCount++;
			}
		}
		
		if (formFieldsMatchCount != formFields.length) {
			response.getWriter().write(text);
			
			return;
		}
		
		int formIdIndex = text.lastIndexOf(formIdAttribute) + formIdAttribute.length();
		
		//Add the senna attribute after the form id attribute within the form tag.
		text = text.substring(0, formIdIndex) + " data-senna-off=\"true\" " + text.substring(formIdIndex);
		
		int passwordCapsLockIndex = text.indexOf(passwordCapsLockSpan);
		
		if (passwordCapsLockIndex < 0) {
			response.getWriter().write(text);
			
			return;
		}
		
		int passwordCapsLockSpanIndex = text.indexOf(spanCloseTag, passwordCapsLockIndex) + spanCloseTag.length();
		
		if (passwordCapsLockSpanIndex < 0) {
			response.getWriter().write(text);
			
			return;
		}
		
		//Insert the authenticatorCode field after the passwordCapsLock span
		text = text.substring(0, passwordCapsLockSpanIndex) + getAuthenticatorCodeHTML(request.getLocale()) + text.substring(passwordCapsLockSpanIndex);

		response.getWriter().write(text);
	}
	
	private String getAuthenticatorCodeHTML(Locale locale) {
		//Google Authenticator App displays with a space, others display as xx xx xx, so allow for xxx xxx or xx xx xx syntax in maxlength.
		int authenticatorCodeMaxLength = tfaConfiguration.authenticatorCodeLength() + 2;

		StringBuilder html = new StringBuilder();
		
		html.append("<div class=\"form-group input-text-wrapper\">");
		html.append("<label class=\"control-label\" for=\"" + LoginConstants.AUTHENTICATOR_CODE_FIELD + "\">");
		html.append(LanguageUtil.get(locale, "authenticator-code"));
		html.append("</label>");
		html.append("<input class=\"field form-control\" id=\"" + LoginConstants.AUTHENTICATOR_CODE_FIELD
				+ "\" name=\"" + LoginConstants.AUTHENTICATOR_CODE_FIELD + "\" type=\"text\" maxlength=\""
				+ authenticatorCodeMaxLength + "\" autocomplete=\"off\" value=\"\" aria-required=\"true\">");
		html.append("</div>");
		
		return html.toString();
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws PortletException {

	}

	@Override
	public void destroy() {

	}	

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		tfaConfiguration = ConfigurableUtil.createConfigurable(TOTP_2FAConfiguration.class, properties);

		if (_log.isInfoEnabled()) {
			_log.info("*********************************************");
			_log.info("tfaConfiguration.loginTotp2faEnabled: " + tfaConfiguration.loginTotp2faEnabled());
			_log.info("tfaConfiguration.authenticatorCodeLength: " + tfaConfiguration.authenticatorCodeLength());
			_log.info("*********************************************");
		}
	}	

	private volatile TOTP_2FAConfiguration tfaConfiguration;	

	private static Log _log = LogFactoryUtil.getLog(LoginPortletFilter.class);
}