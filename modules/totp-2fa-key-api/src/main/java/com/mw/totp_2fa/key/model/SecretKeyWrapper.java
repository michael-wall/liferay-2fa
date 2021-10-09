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

package com.mw.totp_2fa.key.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SecretKey}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SecretKey
 * @generated
 */
public class SecretKeyWrapper
	extends BaseModelWrapper<SecretKey>
	implements ModelWrapper<SecretKey>, SecretKey {

	public SecretKeyWrapper(SecretKey secretKey) {
		super(secretKey);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("secretKeyId", getSecretKeyId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("secretKey", getSecretKey());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long secretKeyId = (Long)attributes.get("secretKeyId");

		if (secretKeyId != null) {
			setSecretKeyId(secretKeyId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String secretKey = (String)attributes.get("secretKey");

		if (secretKey != null) {
			setSecretKey(secretKey);
		}
	}

	/**
	 * Returns the company ID of this secret key.
	 *
	 * @return the company ID of this secret key
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the primary key of this secret key.
	 *
	 * @return the primary key of this secret key
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the secret key of this secret key.
	 *
	 * @return the secret key of this secret key
	 */
	@Override
	public String getSecretKey() {
		return model.getSecretKey();
	}

	/**
	 * Returns the secret key ID of this secret key.
	 *
	 * @return the secret key ID of this secret key
	 */
	@Override
	public long getSecretKeyId() {
		return model.getSecretKeyId();
	}

	/**
	 * Returns the user ID of this secret key.
	 *
	 * @return the user ID of this secret key
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this secret key.
	 *
	 * @return the user uuid of this secret key
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this secret key.
	 *
	 * @return the uuid of this secret key
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this secret key.
	 *
	 * @param companyId the company ID of this secret key
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the primary key of this secret key.
	 *
	 * @param primaryKey the primary key of this secret key
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the secret key of this secret key.
	 *
	 * @param secretKey the secret key of this secret key
	 */
	@Override
	public void setSecretKey(String secretKey) {
		model.setSecretKey(secretKey);
	}

	/**
	 * Sets the secret key ID of this secret key.
	 *
	 * @param secretKeyId the secret key ID of this secret key
	 */
	@Override
	public void setSecretKeyId(long secretKeyId) {
		model.setSecretKeyId(secretKeyId);
	}

	/**
	 * Sets the user ID of this secret key.
	 *
	 * @param userId the user ID of this secret key
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this secret key.
	 *
	 * @param userUuid the user uuid of this secret key
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this secret key.
	 *
	 * @param uuid the uuid of this secret key
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected SecretKeyWrapper wrap(SecretKey secretKey) {
		return new SecretKeyWrapper(secretKey);
	}

}