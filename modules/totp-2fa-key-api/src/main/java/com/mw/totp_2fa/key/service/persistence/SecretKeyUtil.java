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

package com.mw.totp_2fa.key.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.mw.totp_2fa.key.model.SecretKey;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the secret key service. This utility wraps {@link com.mw.totp_2fa.key.service.persistence.impl.SecretKeyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SecretKeyPersistence
 * @see com.mw.totp_2fa.key.service.persistence.impl.SecretKeyPersistenceImpl
 * @generated
 */
@ProviderType
public class SecretKeyUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(SecretKey secretKey) {
		getPersistence().clearCache(secretKey);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SecretKey> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SecretKey> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SecretKey> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SecretKey> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SecretKey update(SecretKey secretKey) {
		return getPersistence().update(secretKey);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SecretKey update(SecretKey secretKey,
		ServiceContext serviceContext) {
		return getPersistence().update(secretKey, serviceContext);
	}

	/**
	* Returns all the secret keies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching secret keies
	*/
	public static List<SecretKey> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
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
	public static List<SecretKey> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
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
	public static List<SecretKey> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<SecretKey> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
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
	public static List<SecretKey> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<SecretKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first secret key in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching secret key
	* @throws NoSuchSecretKeyException if a matching secret key could not be found
	*/
	public static SecretKey findByUuid_First(java.lang.String uuid,
		OrderByComparator<SecretKey> orderByComparator)
		throws com.mw.totp_2fa.key.exception.NoSuchSecretKeyException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first secret key in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching secret key, or <code>null</code> if a matching secret key could not be found
	*/
	public static SecretKey fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<SecretKey> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last secret key in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching secret key
	* @throws NoSuchSecretKeyException if a matching secret key could not be found
	*/
	public static SecretKey findByUuid_Last(java.lang.String uuid,
		OrderByComparator<SecretKey> orderByComparator)
		throws com.mw.totp_2fa.key.exception.NoSuchSecretKeyException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last secret key in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching secret key, or <code>null</code> if a matching secret key could not be found
	*/
	public static SecretKey fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<SecretKey> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
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
	public static SecretKey[] findByUuid_PrevAndNext(long secretKeyId,
		java.lang.String uuid, OrderByComparator<SecretKey> orderByComparator)
		throws com.mw.totp_2fa.key.exception.NoSuchSecretKeyException {
		return getPersistence()
				   .findByUuid_PrevAndNext(secretKeyId, uuid, orderByComparator);
	}

	/**
	* Removes all the secret keies where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of secret keies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching secret keies
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the secret keies where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching secret keies
	*/
	public static List<SecretKey> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
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
	public static List<SecretKey> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
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
	public static List<SecretKey> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<SecretKey> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
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
	public static List<SecretKey> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<SecretKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
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
	public static SecretKey findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<SecretKey> orderByComparator)
		throws com.mw.totp_2fa.key.exception.NoSuchSecretKeyException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first secret key in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching secret key, or <code>null</code> if a matching secret key could not be found
	*/
	public static SecretKey fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<SecretKey> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
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
	public static SecretKey findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<SecretKey> orderByComparator)
		throws com.mw.totp_2fa.key.exception.NoSuchSecretKeyException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last secret key in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching secret key, or <code>null</code> if a matching secret key could not be found
	*/
	public static SecretKey fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<SecretKey> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
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
	public static SecretKey[] findByUuid_C_PrevAndNext(long secretKeyId,
		java.lang.String uuid, long companyId,
		OrderByComparator<SecretKey> orderByComparator)
		throws com.mw.totp_2fa.key.exception.NoSuchSecretKeyException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(secretKeyId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the secret keies where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of secret keies where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching secret keies
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the secret key where companyId = &#63; and userId = &#63; or throws a {@link NoSuchSecretKeyException} if it could not be found.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the matching secret key
	* @throws NoSuchSecretKeyException if a matching secret key could not be found
	*/
	public static SecretKey findByC_U(long companyId, long userId)
		throws com.mw.totp_2fa.key.exception.NoSuchSecretKeyException {
		return getPersistence().findByC_U(companyId, userId);
	}

	/**
	* Returns the secret key where companyId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the matching secret key, or <code>null</code> if a matching secret key could not be found
	*/
	public static SecretKey fetchByC_U(long companyId, long userId) {
		return getPersistence().fetchByC_U(companyId, userId);
	}

	/**
	* Returns the secret key where companyId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching secret key, or <code>null</code> if a matching secret key could not be found
	*/
	public static SecretKey fetchByC_U(long companyId, long userId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByC_U(companyId, userId, retrieveFromCache);
	}

	/**
	* Removes the secret key where companyId = &#63; and userId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the secret key that was removed
	*/
	public static SecretKey removeByC_U(long companyId, long userId)
		throws com.mw.totp_2fa.key.exception.NoSuchSecretKeyException {
		return getPersistence().removeByC_U(companyId, userId);
	}

	/**
	* Returns the number of secret keies where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the number of matching secret keies
	*/
	public static int countByC_U(long companyId, long userId) {
		return getPersistence().countByC_U(companyId, userId);
	}

	/**
	* Caches the secret key in the entity cache if it is enabled.
	*
	* @param secretKey the secret key
	*/
	public static void cacheResult(SecretKey secretKey) {
		getPersistence().cacheResult(secretKey);
	}

	/**
	* Caches the secret keies in the entity cache if it is enabled.
	*
	* @param secretKeies the secret keies
	*/
	public static void cacheResult(List<SecretKey> secretKeies) {
		getPersistence().cacheResult(secretKeies);
	}

	/**
	* Creates a new secret key with the primary key. Does not add the secret key to the database.
	*
	* @param secretKeyId the primary key for the new secret key
	* @return the new secret key
	*/
	public static SecretKey create(long secretKeyId) {
		return getPersistence().create(secretKeyId);
	}

	/**
	* Removes the secret key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param secretKeyId the primary key of the secret key
	* @return the secret key that was removed
	* @throws NoSuchSecretKeyException if a secret key with the primary key could not be found
	*/
	public static SecretKey remove(long secretKeyId)
		throws com.mw.totp_2fa.key.exception.NoSuchSecretKeyException {
		return getPersistence().remove(secretKeyId);
	}

	public static SecretKey updateImpl(SecretKey secretKey) {
		return getPersistence().updateImpl(secretKey);
	}

	/**
	* Returns the secret key with the primary key or throws a {@link NoSuchSecretKeyException} if it could not be found.
	*
	* @param secretKeyId the primary key of the secret key
	* @return the secret key
	* @throws NoSuchSecretKeyException if a secret key with the primary key could not be found
	*/
	public static SecretKey findByPrimaryKey(long secretKeyId)
		throws com.mw.totp_2fa.key.exception.NoSuchSecretKeyException {
		return getPersistence().findByPrimaryKey(secretKeyId);
	}

	/**
	* Returns the secret key with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param secretKeyId the primary key of the secret key
	* @return the secret key, or <code>null</code> if a secret key with the primary key could not be found
	*/
	public static SecretKey fetchByPrimaryKey(long secretKeyId) {
		return getPersistence().fetchByPrimaryKey(secretKeyId);
	}

	public static java.util.Map<java.io.Serializable, SecretKey> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the secret keies.
	*
	* @return the secret keies
	*/
	public static List<SecretKey> findAll() {
		return getPersistence().findAll();
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
	public static List<SecretKey> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<SecretKey> findAll(int start, int end,
		OrderByComparator<SecretKey> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<SecretKey> findAll(int start, int end,
		OrderByComparator<SecretKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the secret keies from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of secret keies.
	*
	* @return the number of secret keies
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static SecretKeyPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SecretKeyPersistence, SecretKeyPersistence> _serviceTracker =
		ServiceTrackerFactory.open(SecretKeyPersistence.class);
}