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

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link SecretKey}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SecretKey
 * @generated
 */
@ProviderType
public class SecretKeyWrapper implements SecretKey, ModelWrapper<SecretKey> {
	public SecretKeyWrapper(SecretKey secretKey) {
		_secretKey = secretKey;
	}

	@Override
	public Class<?> getModelClass() {
		return SecretKey.class;
	}

	@Override
	public String getModelClassName() {
		return SecretKey.class.getName();
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

	@Override
	public java.lang.Object clone() {
		return new SecretKeyWrapper((SecretKey)_secretKey.clone());
	}

	@Override
	public int compareTo(SecretKey secretKey) {
		return _secretKey.compareTo(secretKey);
	}

	/**
	* Returns the company ID of this secret key.
	*
	* @return the company ID of this secret key
	*/
	@Override
	public long getCompanyId() {
		return _secretKey.getCompanyId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _secretKey.getExpandoBridge();
	}

	/**
	* Returns the primary key of this secret key.
	*
	* @return the primary key of this secret key
	*/
	@Override
	public long getPrimaryKey() {
		return _secretKey.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _secretKey.getPrimaryKeyObj();
	}

	/**
	* Returns the secret key of this secret key.
	*
	* @return the secret key of this secret key
	*/
	@Override
	public java.lang.String getSecretKey() {
		return _secretKey.getSecretKey();
	}

	/**
	* Returns the secret key ID of this secret key.
	*
	* @return the secret key ID of this secret key
	*/
	@Override
	public long getSecretKeyId() {
		return _secretKey.getSecretKeyId();
	}

	/**
	* Returns the user ID of this secret key.
	*
	* @return the user ID of this secret key
	*/
	@Override
	public long getUserId() {
		return _secretKey.getUserId();
	}

	/**
	* Returns the user uuid of this secret key.
	*
	* @return the user uuid of this secret key
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _secretKey.getUserUuid();
	}

	/**
	* Returns the uuid of this secret key.
	*
	* @return the uuid of this secret key
	*/
	@Override
	public java.lang.String getUuid() {
		return _secretKey.getUuid();
	}

	@Override
	public int hashCode() {
		return _secretKey.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _secretKey.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _secretKey.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _secretKey.isNew();
	}

	@Override
	public void persist() {
		_secretKey.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_secretKey.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this secret key.
	*
	* @param companyId the company ID of this secret key
	*/
	@Override
	public void setCompanyId(long companyId) {
		_secretKey.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_secretKey.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_secretKey.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_secretKey.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_secretKey.setNew(n);
	}

	/**
	* Sets the primary key of this secret key.
	*
	* @param primaryKey the primary key of this secret key
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_secretKey.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_secretKey.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the secret key of this secret key.
	*
	* @param secretKey the secret key of this secret key
	*/
	@Override
	public void setSecretKey(java.lang.String secretKey) {
		_secretKey.setSecretKey(secretKey);
	}

	/**
	* Sets the secret key ID of this secret key.
	*
	* @param secretKeyId the secret key ID of this secret key
	*/
	@Override
	public void setSecretKeyId(long secretKeyId) {
		_secretKey.setSecretKeyId(secretKeyId);
	}

	/**
	* Sets the user ID of this secret key.
	*
	* @param userId the user ID of this secret key
	*/
	@Override
	public void setUserId(long userId) {
		_secretKey.setUserId(userId);
	}

	/**
	* Sets the user uuid of this secret key.
	*
	* @param userUuid the user uuid of this secret key
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_secretKey.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this secret key.
	*
	* @param uuid the uuid of this secret key
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_secretKey.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SecretKey> toCacheModel() {
		return _secretKey.toCacheModel();
	}

	@Override
	public SecretKey toEscapedModel() {
		return new SecretKeyWrapper(_secretKey.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _secretKey.toString();
	}

	@Override
	public SecretKey toUnescapedModel() {
		return new SecretKeyWrapper(_secretKey.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _secretKey.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SecretKeyWrapper)) {
			return false;
		}

		SecretKeyWrapper secretKeyWrapper = (SecretKeyWrapper)obj;

		if (Objects.equals(_secretKey, secretKeyWrapper._secretKey)) {
			return true;
		}

		return false;
	}

	@Override
	public SecretKey getWrappedModel() {
		return _secretKey;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _secretKey.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _secretKey.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_secretKey.resetOriginalValues();
	}

	private final SecretKey _secretKey;
}