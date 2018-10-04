package com.mw.totp_2fa.activator;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.mw.totp_2fa.key.model.SecretKey;
import com.mw.totp_2fa.key.service.SecretKeyLocalService;
import com.mw.totp_2fa.qrcode.service.QRCodeService;

import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

/**
 * Bundle Activator that iterates through all users and for Active ones that don't have a Secret Key it creates a SecretKey and sends a QR Code URL to the user.
 * 
 *  This runs for ALL users even if the user is inactive / deleted.
 * 
 * @author Michael Wall
 *
 */
public class UserSetupActivator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		ServiceReference userServiceReference = bundleContext.getServiceReference(UserLocalService.class.getName());
		ServiceReference secretKeyServiceReference = bundleContext.getServiceReference(SecretKeyLocalService.class.getName());
		ServiceReference qrCodeServiceReference = bundleContext.getServiceReference(QRCodeService.class.getName());
		
		userLocalService = (UserLocalService)bundleContext.getService(userServiceReference);
		secretKeyLocalService = (SecretKeyLocalService)bundleContext.getService(secretKeyServiceReference);
		qrCodeService = (QRCodeService)bundleContext.getService(qrCodeServiceReference);
		
		if (_log.isInfoEnabled()) {
			_log.info("UsersCount: " + userLocalService.getUsersCount());	
		}
		
		// Ideally we would use userLocalService.getUsers(companyId, defaultUser, status, start, end, obc) but we don't have companyId
		List<User> users = userLocalService.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		
		long secretKeysAdded = 0;

		for (User user : users) {
			if (!user.isDefaultUser() && user.isActive()) { //Only active non default users.
				SecretKey secretKeyObject = secretKeyLocalService.fetchSecretKeyByUserId(user.getCompanyId(), user.getUserId());
				
				if (secretKeyObject == null) {
					//Generate secret key
					secretKeyObject = secretKeyLocalService.addSecretKey(user);
					String secretKeyString = secretKeyObject.getSecretKey();
					
					qrCodeService.sendEmail(user, secretKeyString, true);
					
					secretKeysAdded++;
				}				
			}
		}
		
		if (_log.isInfoEnabled()) {
			_log.info("Added Secret Key to " + secretKeysAdded + " users.");
		}
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
	}
	
	@Reference(cardinality = ReferenceCardinality.MANDATORY, unbind = "-")
	private UserLocalService userLocalService;
	
	@Reference(cardinality = ReferenceCardinality.MANDATORY, unbind = "-")
	private SecretKeyLocalService secretKeyLocalService;

	@Reference(cardinality = ReferenceCardinality.MANDATORY, unbind = "-")
	private QRCodeService qrCodeService;
	
	private static Log _log = LogFactoryUtil.getLog(UserSetupActivator.class);
}