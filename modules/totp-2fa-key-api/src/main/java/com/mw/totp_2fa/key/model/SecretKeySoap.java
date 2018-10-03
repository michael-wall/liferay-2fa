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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class SecretKeySoap implements Serializable {
	public static SecretKeySoap toSoapModel(SecretKey model) {
		SecretKeySoap soapModel = new SecretKeySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setSecretKeyId(model.getSecretKeyId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setSecretKey(model.getSecretKey());

		return soapModel;
	}

	public static SecretKeySoap[] toSoapModels(SecretKey[] models) {
		SecretKeySoap[] soapModels = new SecretKeySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SecretKeySoap[][] toSoapModels(SecretKey[][] models) {
		SecretKeySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SecretKeySoap[models.length][models[0].length];
		}
		else {
			soapModels = new SecretKeySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SecretKeySoap[] toSoapModels(List<SecretKey> models) {
		List<SecretKeySoap> soapModels = new ArrayList<SecretKeySoap>(models.size());

		for (SecretKey model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SecretKeySoap[soapModels.size()]);
	}

	public SecretKeySoap() {
	}

	public long getPrimaryKey() {
		return _secretKeyId;
	}

	public void setPrimaryKey(long pk) {
		setSecretKeyId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getSecretKeyId() {
		return _secretKeyId;
	}

	public void setSecretKeyId(long secretKeyId) {
		_secretKeyId = secretKeyId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getSecretKey() {
		return _secretKey;
	}

	public void setSecretKey(String secretKey) {
		_secretKey = secretKey;
	}

	private String _uuid;
	private long _secretKeyId;
	private long _companyId;
	private long _userId;
	private String _secretKey;
}