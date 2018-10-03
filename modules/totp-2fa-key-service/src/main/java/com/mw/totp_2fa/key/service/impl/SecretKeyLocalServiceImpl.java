/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.mw.totp_2fa.key.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.mw.totp_2fa.key.model.SecretKey;
import com.mw.totp_2fa.key.service.base.SecretKeyLocalServiceBaseImpl;

import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base32;

/**
 * The implementation of the secret key local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.mw.totp_2fa.key.service.SecretKeyLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SecretKeyLocalServiceBaseImpl
 * @see com.mw.totp_2fa.key.service.SecretKeyLocalServiceUtil
 */
public class SecretKeyLocalServiceImpl extends SecretKeyLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.mw.totp_2fa.key.service.SecretKeyLocalServiceUtil} to access the secret key local service.
	 */
	
	public SecretKey fetchSecretKeyByUserId(long companyId, long userId) {

		return secretKeyPersistence.fetchByC_U(companyId, userId);
	}
	
	public SecretKey fetchSecretKeyByUserId(ServiceContext serviceContext, long userId) {

		return secretKeyPersistence.fetchByC_U(serviceContext.getCompanyId(), userId);
	}
	
	/**
	 * Generate secret key and persist it.
	 * Note: A secretKey is generated for any user, regardless of whether they are an Administrator or in the 'Login TOTP 2FA Skip User Role' group.
	 * 
	 * @param user
	 * @return
	 */
	public SecretKey addSecretKey(User user) {
		String secretKeyString = generateSecretKey();
		
		long secretKeyId = counterLocalService.increment();
		SecretKey secretKey = createSecretKey(secretKeyId);
		secretKey.setCompanyId(user.getCompanyId());
		secretKey.setUserId(user.getUserId());
		secretKey.setSecretKey(secretKeyString);

		SecretKey secretKeyCreated = addSecretKey(secretKey);

		return secretKeyCreated;
	}
	
	/**
	 * Retrieves an existing SecretKey Entity and updates it with a new Secret Key. Creates and persists a new SecretKey Entity if not found. 
	 * 
	 * @param user
	 * @return
	 */
	public SecretKey updateSecretKey(User user) {
		SecretKey secretKey = fetchSecretKeyByUserId(user.getCompanyId(), user.getUserId());
		
		if (secretKey == null) {
			return addSecretKey(user);
		}
		
		String secretKeyString = generateSecretKey();
		secretKey.setSecretKey(secretKeyString);
		
		SecretKey secretKeyUpdated = updateSecretKey(secretKey);

		return secretKeyUpdated;
	}
	
	/**
	 * Generate a SecretKey String.
	 * 
	 * @return
	 */
	public String generateSecretKey() {
		SecureRandom random = new SecureRandom();
	    byte[] bytes = new byte[20];
	    random.nextBytes(bytes);
	    Base32 base32 = new Base32();
	    String secretKey = base32.encodeToString(bytes);
	   
	    return secretKey;
	}
	
	private static Log _log = LogFactoryUtil.getLog(SecretKeyLocalServiceImpl.class);	
}