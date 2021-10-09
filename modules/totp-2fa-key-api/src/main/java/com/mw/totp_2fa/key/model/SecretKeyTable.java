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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;totp_SecretKey&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see SecretKey
 * @generated
 */
public class SecretKeyTable extends BaseTable<SecretKeyTable> {

	public static final SecretKeyTable INSTANCE = new SecretKeyTable();

	public final Column<SecretKeyTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<SecretKeyTable, Long> secretKeyId = createColumn(
		"secretKeyId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<SecretKeyTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SecretKeyTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<SecretKeyTable, String> secretKey = createColumn(
		"secretKey", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private SecretKeyTable() {
		super("totp_SecretKey", SecretKeyTable::new);
	}

}