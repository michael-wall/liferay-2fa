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

package com.mw.totp_2fa.key.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.mw.totp_2fa.key.exception.NoSuchSecretKeyException;
import com.mw.totp_2fa.key.model.SecretKey;
import com.mw.totp_2fa.key.model.impl.SecretKeyImpl;
import com.mw.totp_2fa.key.model.impl.SecretKeyModelImpl;
import com.mw.totp_2fa.key.service.persistence.SecretKeyPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the secret key service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SecretKeyPersistence
 * @see com.mw.totp_2fa.key.service.persistence.SecretKeyUtil
 * @generated
 */
@ProviderType
public class SecretKeyPersistenceImpl extends BasePersistenceImpl<SecretKey>
	implements SecretKeyPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SecretKeyUtil} to access the secret key persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SecretKeyImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
			SecretKeyModelImpl.FINDER_CACHE_ENABLED, SecretKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
			SecretKeyModelImpl.FINDER_CACHE_ENABLED, SecretKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
			SecretKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
			SecretKeyModelImpl.FINDER_CACHE_ENABLED, SecretKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
			SecretKeyModelImpl.FINDER_CACHE_ENABLED, SecretKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			SecretKeyModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
			SecretKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the secret keies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching secret keies
	 */
	@Override
	public List<SecretKey> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the secret keies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecretKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of secret keies
	 * @param end the upper bound of the range of secret keies (not inclusive)
	 * @return the range of matching secret keies
	 */
	@Override
	public List<SecretKey> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the secret keies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecretKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of secret keies
	 * @param end the upper bound of the range of secret keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching secret keies
	 */
	@Override
	public List<SecretKey> findByUuid(String uuid, int start, int end,
		OrderByComparator<SecretKey> orderByComparator) {
		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the secret keies where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecretKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of secret keies
	 * @param end the upper bound of the range of secret keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching secret keies
	 */
	@Override
	public List<SecretKey> findByUuid(String uuid, int start, int end,
		OrderByComparator<SecretKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID;
			finderArgs = new Object[] { uuid, start, end, orderByComparator };
		}

		List<SecretKey> list = null;

		if (retrieveFromCache) {
			list = (List<SecretKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SecretKey secretKey : list) {
					if (!Objects.equals(uuid, secretKey.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_SECRETKEY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SecretKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				if (!pagination) {
					list = (List<SecretKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SecretKey>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first secret key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching secret key
	 * @throws NoSuchSecretKeyException if a matching secret key could not be found
	 */
	@Override
	public SecretKey findByUuid_First(String uuid,
		OrderByComparator<SecretKey> orderByComparator)
		throws NoSuchSecretKeyException {
		SecretKey secretKey = fetchByUuid_First(uuid, orderByComparator);

		if (secretKey != null) {
			return secretKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSecretKeyException(msg.toString());
	}

	/**
	 * Returns the first secret key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching secret key, or <code>null</code> if a matching secret key could not be found
	 */
	@Override
	public SecretKey fetchByUuid_First(String uuid,
		OrderByComparator<SecretKey> orderByComparator) {
		List<SecretKey> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last secret key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching secret key
	 * @throws NoSuchSecretKeyException if a matching secret key could not be found
	 */
	@Override
	public SecretKey findByUuid_Last(String uuid,
		OrderByComparator<SecretKey> orderByComparator)
		throws NoSuchSecretKeyException {
		SecretKey secretKey = fetchByUuid_Last(uuid, orderByComparator);

		if (secretKey != null) {
			return secretKey;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchSecretKeyException(msg.toString());
	}

	/**
	 * Returns the last secret key in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching secret key, or <code>null</code> if a matching secret key could not be found
	 */
	@Override
	public SecretKey fetchByUuid_Last(String uuid,
		OrderByComparator<SecretKey> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<SecretKey> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the secret keies before and after the current secret key in the ordered set where uuid = &#63;.
	 *
	 * @param secretKeyId the primary key of the current secret key
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next secret key
	 * @throws NoSuchSecretKeyException if a secret key with the primary key could not be found
	 */
	@Override
	public SecretKey[] findByUuid_PrevAndNext(long secretKeyId, String uuid,
		OrderByComparator<SecretKey> orderByComparator)
		throws NoSuchSecretKeyException {
		SecretKey secretKey = findByPrimaryKey(secretKeyId);

		Session session = null;

		try {
			session = openSession();

			SecretKey[] array = new SecretKeyImpl[3];

			array[0] = getByUuid_PrevAndNext(session, secretKey, uuid,
					orderByComparator, true);

			array[1] = secretKey;

			array[2] = getByUuid_PrevAndNext(session, secretKey, uuid,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SecretKey getByUuid_PrevAndNext(Session session,
		SecretKey secretKey, String uuid,
		OrderByComparator<SecretKey> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SECRETKEY_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(SecretKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(secretKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SecretKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the secret keies where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (SecretKey secretKey : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(secretKey);
		}
	}

	/**
	 * Returns the number of secret keies where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching secret keies
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_SECRETKEY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "secretKey.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "secretKey.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(secretKey.uuid IS NULL OR secretKey.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
			SecretKeyModelImpl.FINDER_CACHE_ENABLED, SecretKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
			SecretKeyModelImpl.FINDER_CACHE_ENABLED, SecretKeyImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			SecretKeyModelImpl.UUID_COLUMN_BITMASK |
			SecretKeyModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
			SecretKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the secret keies where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching secret keies
	 */
	@Override
	public List<SecretKey> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the secret keies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecretKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of secret keies
	 * @param end the upper bound of the range of secret keies (not inclusive)
	 * @return the range of matching secret keies
	 */
	@Override
	public List<SecretKey> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the secret keies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecretKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of secret keies
	 * @param end the upper bound of the range of secret keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching secret keies
	 */
	@Override
	public List<SecretKey> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<SecretKey> orderByComparator) {
		return findByUuid_C(uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the secret keies where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecretKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of secret keies
	 * @param end the upper bound of the range of secret keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching secret keies
	 */
	@Override
	public List<SecretKey> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<SecretKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] { uuid, companyId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C;
			finderArgs = new Object[] {
					uuid, companyId,
					
					start, end, orderByComparator
				};
		}

		List<SecretKey> list = null;

		if (retrieveFromCache) {
			list = (List<SecretKey>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (SecretKey secretKey : list) {
					if (!Objects.equals(uuid, secretKey.getUuid()) ||
							(companyId != secretKey.getCompanyId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_SECRETKEY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SecretKeyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				if (!pagination) {
					list = (List<SecretKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SecretKey>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first secret key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching secret key
	 * @throws NoSuchSecretKeyException if a matching secret key could not be found
	 */
	@Override
	public SecretKey findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SecretKey> orderByComparator)
		throws NoSuchSecretKeyException {
		SecretKey secretKey = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (secretKey != null) {
			return secretKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchSecretKeyException(msg.toString());
	}

	/**
	 * Returns the first secret key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching secret key, or <code>null</code> if a matching secret key could not be found
	 */
	@Override
	public SecretKey fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<SecretKey> orderByComparator) {
		List<SecretKey> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last secret key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching secret key
	 * @throws NoSuchSecretKeyException if a matching secret key could not be found
	 */
	@Override
	public SecretKey findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SecretKey> orderByComparator)
		throws NoSuchSecretKeyException {
		SecretKey secretKey = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (secretKey != null) {
			return secretKey;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchSecretKeyException(msg.toString());
	}

	/**
	 * Returns the last secret key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching secret key, or <code>null</code> if a matching secret key could not be found
	 */
	@Override
	public SecretKey fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<SecretKey> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<SecretKey> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the secret keies before and after the current secret key in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param secretKeyId the primary key of the current secret key
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next secret key
	 * @throws NoSuchSecretKeyException if a secret key with the primary key could not be found
	 */
	@Override
	public SecretKey[] findByUuid_C_PrevAndNext(long secretKeyId, String uuid,
		long companyId, OrderByComparator<SecretKey> orderByComparator)
		throws NoSuchSecretKeyException {
		SecretKey secretKey = findByPrimaryKey(secretKeyId);

		Session session = null;

		try {
			session = openSession();

			SecretKey[] array = new SecretKeyImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, secretKey, uuid,
					companyId, orderByComparator, true);

			array[1] = secretKey;

			array[2] = getByUuid_C_PrevAndNext(session, secretKey, uuid,
					companyId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SecretKey getByUuid_C_PrevAndNext(Session session,
		SecretKey secretKey, String uuid, long companyId,
		OrderByComparator<SecretKey> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(5 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_SECRETKEY_WHERE);

		boolean bindUuid = false;

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_1);
		}
		else if (uuid.equals("")) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(SecretKeyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(secretKey);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SecretKey> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the secret keies where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (SecretKey secretKey : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(secretKey);
		}
	}

	/**
	 * Returns the number of secret keies where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching secret keies
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SECRETKEY_WHERE);

			boolean bindUuid = false;

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_1);
			}
			else if (uuid.equals("")) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "secretKey.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "secretKey.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(secretKey.uuid IS NULL OR secretKey.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "secretKey.companyId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_U = new FinderPath(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
			SecretKeyModelImpl.FINDER_CACHE_ENABLED, SecretKeyImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_U",
			new String[] { Long.class.getName(), Long.class.getName() },
			SecretKeyModelImpl.COMPANYID_COLUMN_BITMASK |
			SecretKeyModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_U = new FinderPath(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
			SecretKeyModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_U",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the secret key where companyId = &#63; and userId = &#63; or throws a {@link NoSuchSecretKeyException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @return the matching secret key
	 * @throws NoSuchSecretKeyException if a matching secret key could not be found
	 */
	@Override
	public SecretKey findByC_U(long companyId, long userId)
		throws NoSuchSecretKeyException {
		SecretKey secretKey = fetchByC_U(companyId, userId);

		if (secretKey == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchSecretKeyException(msg.toString());
		}

		return secretKey;
	}

	/**
	 * Returns the secret key where companyId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @return the matching secret key, or <code>null</code> if a matching secret key could not be found
	 */
	@Override
	public SecretKey fetchByC_U(long companyId, long userId) {
		return fetchByC_U(companyId, userId, true);
	}

	/**
	 * Returns the secret key where companyId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching secret key, or <code>null</code> if a matching secret key could not be found
	 */
	@Override
	public SecretKey fetchByC_U(long companyId, long userId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { companyId, userId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_C_U,
					finderArgs, this);
		}

		if (result instanceof SecretKey) {
			SecretKey secretKey = (SecretKey)result;

			if ((companyId != secretKey.getCompanyId()) ||
					(userId != secretKey.getUserId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_SECRETKEY_WHERE);

			query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(userId);

				List<SecretKey> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_C_U, finderArgs,
						list);
				}
				else {
					SecretKey secretKey = list.get(0);

					result = secretKey;

					cacheResult(secretKey);

					if ((secretKey.getCompanyId() != companyId) ||
							(secretKey.getUserId() != userId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_C_U,
							finderArgs, secretKey);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_C_U, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (SecretKey)result;
		}
	}

	/**
	 * Removes the secret key where companyId = &#63; and userId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @return the secret key that was removed
	 */
	@Override
	public SecretKey removeByC_U(long companyId, long userId)
		throws NoSuchSecretKeyException {
		SecretKey secretKey = findByC_U(companyId, userId);

		return remove(secretKey);
	}

	/**
	 * Returns the number of secret keies where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @return the number of matching secret keies
	 */
	@Override
	public int countByC_U(long companyId, long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_U;

		Object[] finderArgs = new Object[] { companyId, userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SECRETKEY_WHERE);

			query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_U_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(userId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_U_COMPANYID_2 = "secretKey.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_U_USERID_2 = "secretKey.userId = ?";

	public SecretKeyPersistenceImpl() {
		setModelClass(SecretKey.class);

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
					"_dbColumnNames");

			field.setAccessible(true);

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("uuid", "uuid_");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the secret key in the entity cache if it is enabled.
	 *
	 * @param secretKey the secret key
	 */
	@Override
	public void cacheResult(SecretKey secretKey) {
		entityCache.putResult(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
			SecretKeyImpl.class, secretKey.getPrimaryKey(), secretKey);

		finderCache.putResult(FINDER_PATH_FETCH_BY_C_U,
			new Object[] { secretKey.getCompanyId(), secretKey.getUserId() },
			secretKey);

		secretKey.resetOriginalValues();
	}

	/**
	 * Caches the secret keies in the entity cache if it is enabled.
	 *
	 * @param secretKeies the secret keies
	 */
	@Override
	public void cacheResult(List<SecretKey> secretKeies) {
		for (SecretKey secretKey : secretKeies) {
			if (entityCache.getResult(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
						SecretKeyImpl.class, secretKey.getPrimaryKey()) == null) {
				cacheResult(secretKey);
			}
			else {
				secretKey.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all secret keies.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SecretKeyImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the secret key.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SecretKey secretKey) {
		entityCache.removeResult(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
			SecretKeyImpl.class, secretKey.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((SecretKeyModelImpl)secretKey, true);
	}

	@Override
	public void clearCache(List<SecretKey> secretKeies) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SecretKey secretKey : secretKeies) {
			entityCache.removeResult(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
				SecretKeyImpl.class, secretKey.getPrimaryKey());

			clearUniqueFindersCache((SecretKeyModelImpl)secretKey, true);
		}
	}

	protected void cacheUniqueFindersCache(
		SecretKeyModelImpl secretKeyModelImpl) {
		Object[] args = new Object[] {
				secretKeyModelImpl.getCompanyId(),
				secretKeyModelImpl.getUserId()
			};

		finderCache.putResult(FINDER_PATH_COUNT_BY_C_U, args, Long.valueOf(1),
			false);
		finderCache.putResult(FINDER_PATH_FETCH_BY_C_U, args,
			secretKeyModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		SecretKeyModelImpl secretKeyModelImpl, boolean clearCurrent) {
		if (clearCurrent) {
			Object[] args = new Object[] {
					secretKeyModelImpl.getCompanyId(),
					secretKeyModelImpl.getUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_U, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_U, args);
		}

		if ((secretKeyModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_U.getColumnBitmask()) != 0) {
			Object[] args = new Object[] {
					secretKeyModelImpl.getOriginalCompanyId(),
					secretKeyModelImpl.getOriginalUserId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_C_U, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_C_U, args);
		}
	}

	/**
	 * Creates a new secret key with the primary key. Does not add the secret key to the database.
	 *
	 * @param secretKeyId the primary key for the new secret key
	 * @return the new secret key
	 */
	@Override
	public SecretKey create(long secretKeyId) {
		SecretKey secretKey = new SecretKeyImpl();

		secretKey.setNew(true);
		secretKey.setPrimaryKey(secretKeyId);

		String uuid = PortalUUIDUtil.generate();

		secretKey.setUuid(uuid);

		secretKey.setCompanyId(companyProvider.getCompanyId());

		return secretKey;
	}

	/**
	 * Removes the secret key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param secretKeyId the primary key of the secret key
	 * @return the secret key that was removed
	 * @throws NoSuchSecretKeyException if a secret key with the primary key could not be found
	 */
	@Override
	public SecretKey remove(long secretKeyId) throws NoSuchSecretKeyException {
		return remove((Serializable)secretKeyId);
	}

	/**
	 * Removes the secret key with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the secret key
	 * @return the secret key that was removed
	 * @throws NoSuchSecretKeyException if a secret key with the primary key could not be found
	 */
	@Override
	public SecretKey remove(Serializable primaryKey)
		throws NoSuchSecretKeyException {
		Session session = null;

		try {
			session = openSession();

			SecretKey secretKey = (SecretKey)session.get(SecretKeyImpl.class,
					primaryKey);

			if (secretKey == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSecretKeyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(secretKey);
		}
		catch (NoSuchSecretKeyException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected SecretKey removeImpl(SecretKey secretKey) {
		secretKey = toUnwrappedModel(secretKey);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(secretKey)) {
				secretKey = (SecretKey)session.get(SecretKeyImpl.class,
						secretKey.getPrimaryKeyObj());
			}

			if (secretKey != null) {
				session.delete(secretKey);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (secretKey != null) {
			clearCache(secretKey);
		}

		return secretKey;
	}

	@Override
	public SecretKey updateImpl(SecretKey secretKey) {
		secretKey = toUnwrappedModel(secretKey);

		boolean isNew = secretKey.isNew();

		SecretKeyModelImpl secretKeyModelImpl = (SecretKeyModelImpl)secretKey;

		if (Validator.isNull(secretKey.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			secretKey.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (secretKey.isNew()) {
				session.save(secretKey);

				secretKey.setNew(false);
			}
			else {
				secretKey = (SecretKey)session.merge(secretKey);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!SecretKeyModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else
		 if (isNew) {
			Object[] args = new Object[] { secretKeyModelImpl.getUuid() };

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
				args);

			args = new Object[] {
					secretKeyModelImpl.getUuid(),
					secretKeyModelImpl.getCompanyId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
				args);

			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		else {
			if ((secretKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						secretKeyModelImpl.getOriginalUuid()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { secretKeyModelImpl.getUuid() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((secretKeyModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						secretKeyModelImpl.getOriginalUuid(),
						secretKeyModelImpl.getOriginalCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						secretKeyModelImpl.getUuid(),
						secretKeyModelImpl.getCompanyId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		entityCache.putResult(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
			SecretKeyImpl.class, secretKey.getPrimaryKey(), secretKey, false);

		clearUniqueFindersCache(secretKeyModelImpl, false);
		cacheUniqueFindersCache(secretKeyModelImpl);

		secretKey.resetOriginalValues();

		return secretKey;
	}

	protected SecretKey toUnwrappedModel(SecretKey secretKey) {
		if (secretKey instanceof SecretKeyImpl) {
			return secretKey;
		}

		SecretKeyImpl secretKeyImpl = new SecretKeyImpl();

		secretKeyImpl.setNew(secretKey.isNew());
		secretKeyImpl.setPrimaryKey(secretKey.getPrimaryKey());

		secretKeyImpl.setUuid(secretKey.getUuid());
		secretKeyImpl.setSecretKeyId(secretKey.getSecretKeyId());
		secretKeyImpl.setCompanyId(secretKey.getCompanyId());
		secretKeyImpl.setUserId(secretKey.getUserId());
		secretKeyImpl.setSecretKey(secretKey.getSecretKey());

		return secretKeyImpl;
	}

	/**
	 * Returns the secret key with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the secret key
	 * @return the secret key
	 * @throws NoSuchSecretKeyException if a secret key with the primary key could not be found
	 */
	@Override
	public SecretKey findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSecretKeyException {
		SecretKey secretKey = fetchByPrimaryKey(primaryKey);

		if (secretKey == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSecretKeyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return secretKey;
	}

	/**
	 * Returns the secret key with the primary key or throws a {@link NoSuchSecretKeyException} if it could not be found.
	 *
	 * @param secretKeyId the primary key of the secret key
	 * @return the secret key
	 * @throws NoSuchSecretKeyException if a secret key with the primary key could not be found
	 */
	@Override
	public SecretKey findByPrimaryKey(long secretKeyId)
		throws NoSuchSecretKeyException {
		return findByPrimaryKey((Serializable)secretKeyId);
	}

	/**
	 * Returns the secret key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the secret key
	 * @return the secret key, or <code>null</code> if a secret key with the primary key could not be found
	 */
	@Override
	public SecretKey fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
				SecretKeyImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SecretKey secretKey = (SecretKey)serializable;

		if (secretKey == null) {
			Session session = null;

			try {
				session = openSession();

				secretKey = (SecretKey)session.get(SecretKeyImpl.class,
						primaryKey);

				if (secretKey != null) {
					cacheResult(secretKey);
				}
				else {
					entityCache.putResult(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
						SecretKeyImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
					SecretKeyImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return secretKey;
	}

	/**
	 * Returns the secret key with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param secretKeyId the primary key of the secret key
	 * @return the secret key, or <code>null</code> if a secret key with the primary key could not be found
	 */
	@Override
	public SecretKey fetchByPrimaryKey(long secretKeyId) {
		return fetchByPrimaryKey((Serializable)secretKeyId);
	}

	@Override
	public Map<Serializable, SecretKey> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SecretKey> map = new HashMap<Serializable, SecretKey>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SecretKey secretKey = fetchByPrimaryKey(primaryKey);

			if (secretKey != null) {
				map.put(primaryKey, secretKey);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
					SecretKeyImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SecretKey)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SECRETKEY_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (SecretKey secretKey : (List<SecretKey>)q.list()) {
				map.put(secretKey.getPrimaryKeyObj(), secretKey);

				cacheResult(secretKey);

				uncachedPrimaryKeys.remove(secretKey.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SecretKeyModelImpl.ENTITY_CACHE_ENABLED,
					SecretKeyImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the secret keies.
	 *
	 * @return the secret keies
	 */
	@Override
	public List<SecretKey> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the secret keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecretKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of secret keies
	 * @param end the upper bound of the range of secret keies (not inclusive)
	 * @return the range of secret keies
	 */
	@Override
	public List<SecretKey> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the secret keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecretKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of secret keies
	 * @param end the upper bound of the range of secret keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of secret keies
	 */
	@Override
	public List<SecretKey> findAll(int start, int end,
		OrderByComparator<SecretKey> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the secret keies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecretKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of secret keies
	 * @param end the upper bound of the range of secret keies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of secret keies
	 */
	@Override
	public List<SecretKey> findAll(int start, int end,
		OrderByComparator<SecretKey> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<SecretKey> list = null;

		if (retrieveFromCache) {
			list = (List<SecretKey>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SECRETKEY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SECRETKEY;

				if (pagination) {
					sql = sql.concat(SecretKeyModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SecretKey>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SecretKey>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the secret keies from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SecretKey secretKey : findAll()) {
			remove(secretKey);
		}
	}

	/**
	 * Returns the number of secret keies.
	 *
	 * @return the number of secret keies
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SECRETKEY);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SecretKeyModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the secret key persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SecretKeyImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SECRETKEY = "SELECT secretKey FROM SecretKey secretKey";
	private static final String _SQL_SELECT_SECRETKEY_WHERE_PKS_IN = "SELECT secretKey FROM SecretKey secretKey WHERE secretKeyId IN (";
	private static final String _SQL_SELECT_SECRETKEY_WHERE = "SELECT secretKey FROM SecretKey secretKey WHERE ";
	private static final String _SQL_COUNT_SECRETKEY = "SELECT COUNT(secretKey) FROM SecretKey secretKey";
	private static final String _SQL_COUNT_SECRETKEY_WHERE = "SELECT COUNT(secretKey) FROM SecretKey secretKey WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "secretKey.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SecretKey exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SecretKey exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(SecretKeyPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
}