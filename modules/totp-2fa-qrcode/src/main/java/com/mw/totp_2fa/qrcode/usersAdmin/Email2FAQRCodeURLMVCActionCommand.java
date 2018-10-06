/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mw.totp_2fa.qrcode.usersAdmin;

import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.users.admin.constants.UsersAdminPortletKeys;
import com.mw.totp_2fa.key.model.SecretKey;
import com.mw.totp_2fa.key.service.SecretKeyLocalService;
import com.mw.totp_2fa.qrcode.service.QRCodeService;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * Custom MVC Action Command for UsersAdminPortlet. 
 * 
 * Used by the corresponding Portlet Filter to email a QR Code URL to the user.
 * 
 * @author Michael Wall
 *
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + UsersAdminPortletKeys.USERS_ADMIN,
		"mvc.command.name=/users_admin/email_2fa_qr_code_url"
    },
    service = MVCActionCommand.class
)
public class Email2FAQRCodeURLMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			User user = _portal.getSelectedUser(actionRequest); //Uses p_u_i_d
			
			//Don't proceed if user inactive.
			if (user == null || !user.isActive()) {
				return;
			}

			SecretKey secretKeyObject = secretKeyLocalService.fetchSecretKeyByUserId(user.getCompanyId(), user.getUserId());
			
			if (secretKeyObject != null) {
				qrCodeService.sendEmail(user, secretKeyObject.getSecretKey());
				
				if (_log.isInfoEnabled()) {
					_log.info("Email sent to " + user.getEmailAddress());
				}
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchUserException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter("mvcPath", "/error.jsp");
			}
			else {
				throw e;
			}
		}
	}

	@Reference
	private Portal _portal;
	
	@Reference(cardinality = ReferenceCardinality.MANDATORY, unbind = "-")
	private SecretKeyLocalService secretKeyLocalService;
	
	@Reference(cardinality = ReferenceCardinality.MANDATORY, unbind = "-")
	private QRCodeService qrCodeService;
	
	private static final Log _log = LogFactoryUtil.getLog(
		Email2FAQRCodeURLMVCActionCommand.class);
}