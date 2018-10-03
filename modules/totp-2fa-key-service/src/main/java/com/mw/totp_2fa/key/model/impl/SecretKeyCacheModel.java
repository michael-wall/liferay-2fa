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

package com.mw.totp_2fa.key.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import com.mw.totp_2fa.key.model.SecretKey;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SecretKey in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SecretKey
 * @generated
 */
@ProviderType
public class SecretKeyCacheModel implements CacheModel<SecretKey>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SecretKeyCacheModel)) {
			return false;
		}

		SecretKeyCacheModel secretKeyCacheModel = (SecretKeyCacheModel)obj;

		if (secretKeyId == secretKeyCacheModel.secretKeyId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, secretKeyId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", secretKeyId=");
		sb.append(secretKeyId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", secretKey=");
		sb.append(secretKey);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SecretKey toEntityModel() {
		SecretKeyImpl secretKeyImpl = new SecretKeyImpl();

		if (uuid == null) {
			secretKeyImpl.setUuid("");
		}
		else {
			secretKeyImpl.setUuid(uuid);
		}

		secretKeyImpl.setSecretKeyId(secretKeyId);
		secretKeyImpl.setCompanyId(companyId);
		secretKeyImpl.setUserId(userId);

		if (secretKey == null) {
			secretKeyImpl.setSecretKey("");
		}
		else {
			secretKeyImpl.setSecretKey(secretKey);
		}

		secretKeyImpl.resetOriginalValues();

		return secretKeyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		secretKeyId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		secretKey = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(secretKeyId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (secretKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(secretKey);
		}
	}

	public String uuid;
	public long secretKeyId;
	public long companyId;
	public long userId;
	public String secretKey;
}