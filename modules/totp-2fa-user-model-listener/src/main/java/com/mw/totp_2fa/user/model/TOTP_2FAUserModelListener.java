package com.mw.totp_2fa.user.model;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;
import com.mw.totp_2fa.key.model.SecretKey;
import com.mw.totp_2fa.key.service.SecretKeyLocalService;
import com.mw.totp_2fa.qrcode.service.QRCodeService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * Generates and persists a SecretKey for the user and emails it to the users email address...
 * 
 * @author Michael Wall
 *
 */
@Component(
		immediate = true,
		service = ModelListener.class
	)
public class TOTP_2FAUserModelListener extends BaseModelListener<User> {


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.liferay.portal.kernel.model.BaseModelListener#onAfterCreate(com.
	 * liferay.portal.kernel.model.BaseModel)
	 */
	@Override
	public void onAfterCreate(User user) throws ModelListenerException {

		//Assumes user is active. Will send even if user status is not 0 (e.g. where workflow apprival required) ...
		SecretKey secretKeyObject = secretKeyLocalService.addSecretKey(user);
		
		if (_log.isInfoEnabled()) {
			_log.info("SecretKey created for: " + user.getScreenName());
		}

		qrCodeService.sendEmail(user, secretKeyObject.getSecretKey(), false);
	}
	
	@Reference(cardinality = ReferenceCardinality.MANDATORY, unbind = "-")
	private SecretKeyLocalService secretKeyLocalService;

	@Reference(cardinality = ReferenceCardinality.MANDATORY, unbind = "-")
	private QRCodeService qrCodeService;
	
	private static Log _log = LogFactoryUtil.getLog(TOTP_2FAUserModelListener.class);	
}
