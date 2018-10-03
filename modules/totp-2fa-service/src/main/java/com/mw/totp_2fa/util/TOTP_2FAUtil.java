package com.mw.totp_2fa.util;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.Validator;
import com.mw.totp_2fa.config.TOTP_2FAConfiguration;

import java.util.Arrays;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

@Component(configurationPid = TOTP_2FAConfiguration.PID, immediate = true)
public class TOTP_2FAUtil {
	
	/**
	 * Check if we need to verify the Authenticator Code for the current user based on being an Administrator or a member of the SkipUserRole defined in configuration.
	 * 
	 * Response is array of booleans, first is whether to ignore, second is whether user is an administrator or not.
	 * 
	 * @param user
	 * @return
	 */
	public static boolean[] isAdministratorOrSkipUserRole(User user) {
		String ignoreRoles[] = { RoleConstants.GUEST.toLowerCase(), RoleConstants.USER.toLowerCase(), RoleConstants.OWNER.toLowerCase() };

		for (Role role : user.getRoles()) {
			if (role.getName().equalsIgnoreCase(RoleConstants.ADMINISTRATOR)) {
				boolean response[]={true, true};
				return response;
			} else if (Validator.isNotNull(tfaConfiguration.loginTotp2faSkipUserRole()) && role.getName().equalsIgnoreCase(tfaConfiguration.loginTotp2faSkipUserRole()) && !Arrays.asList(ignoreRoles).contains(tfaConfiguration.loginTotp2faSkipUserRole().trim().toLowerCase())) {
				boolean response[]={true, false};
				return response;
			}
		}
		
		boolean response[]={false, false};
		
		return response;
	}
	
	
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		tfaConfiguration = ConfigurableUtil.createConfigurable(TOTP_2FAConfiguration.class, properties);

	}

	private static volatile TOTP_2FAConfiguration tfaConfiguration;		

	private static Log _log = LogFactoryUtil.getLog(TOTP_2FAUtil.class);		
}