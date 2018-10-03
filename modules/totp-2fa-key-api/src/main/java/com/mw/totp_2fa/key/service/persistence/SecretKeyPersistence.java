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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.mw.totp_2fa.key.exception.NoSuchSecretKeyException;
import com.mw.totp_2fa.key.model.SecretKey;

/**
 * The persistence interface for the secret key service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.mw.totp_2fa.key.service.persistence.impl.SecretKeyPersistenceImpl
 * @see SecretKeyUtil
 * @generated
 */
@ProviderType
public interface SecretKeyPersistence extends BasePersistence<SecretKey> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SecretKeyUtil} to access the secret key persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the secret keies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching secret keies
	*/
	public java.util.List<SecretKey> findByUuid(java.lang.String uuid);

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
	public java.util.List<SecretKey> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<SecretKey> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SecretKey> orderByComparator);

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
	public java.util.List<SecretKey> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SecretKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first secret key in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching secret key
	* @throws NoSuchSecretKeyException if a matching secret key could not be found
	*/
	public SecretKey findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SecretKey> orderByComparator)
		throws NoSuchSecretKeyException;

	/**
	* Returns the first secret key in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching secret key, or <code>null</code> if a matching secret key could not be found
	*/
	public SecretKey fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SecretKey> orderByComparator);

	/**
	* Returns the last secret key in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching secret key
	* @throws NoSuchSecretKeyException if a matching secret key could not be found
	*/
	public SecretKey findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SecretKey> orderByComparator)
		throws NoSuchSecretKeyException;

	/**
	* Returns the last secret key in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching secret key, or <code>null</code> if a matching secret key could not be found
	*/
	public SecretKey fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SecretKey> orderByComparator);

	/**
	* Returns the secret keies before and after the current secret key in the ordered set where uuid = &#63;.
	*
	* @param secretKeyId the primary key of the current secret key
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next secret key
	* @throws NoSuchSecretKeyException if a secret key with the primary key could not be found
	*/
	public SecretKey[] findByUuid_PrevAndNext(long secretKeyId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<SecretKey> orderByComparator)
		throws NoSuchSecretKeyException;

	/**
	* Removes all the secret keies where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of secret keies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching secret keies
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns all the secret keies where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching secret keies
	*/
	public java.util.List<SecretKey> findByUuid_C(java.lang.String uuid,
		long companyId);

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
	public java.util.List<SecretKey> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end);

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
	public java.util.List<SecretKey> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SecretKey> orderByComparator);

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
	public java.util.List<SecretKey> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SecretKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first secret key in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching secret key
	* @throws NoSuchSecretKeyException if a matching secret key could not be found
	*/
	public SecretKey findByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SecretKey> orderByComparator)
		throws NoSuchSecretKeyException;

	/**
	* Returns the first secret key in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching secret key, or <code>null</code> if a matching secret key could not be found
	*/
	public SecretKey fetchByUuid_C_First(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SecretKey> orderByComparator);

	/**
	* Returns the last secret key in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching secret key
	* @throws NoSuchSecretKeyException if a matching secret key could not be found
	*/
	public SecretKey findByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SecretKey> orderByComparator)
		throws NoSuchSecretKeyException;

	/**
	* Returns the last secret key in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching secret key, or <code>null</code> if a matching secret key could not be found
	*/
	public SecretKey fetchByUuid_C_Last(java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SecretKey> orderByComparator);

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
	public SecretKey[] findByUuid_C_PrevAndNext(long secretKeyId,
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<SecretKey> orderByComparator)
		throws NoSuchSecretKeyException;

	/**
	* Removes all the secret keies where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the number of secret keies where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching secret keies
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId);

	/**
	* Returns the secret key where companyId = &#63; and userId = &#63; or throws a {@link NoSuchSecretKeyException} if it could not be found.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the matching secret key
	* @throws NoSuchSecretKeyException if a matching secret key could not be found
	*/
	public SecretKey findByC_U(long companyId, long userId)
		throws NoSuchSecretKeyException;

	/**
	* Returns the secret key where companyId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the matching secret key, or <code>null</code> if a matching secret key could not be found
	*/
	public SecretKey fetchByC_U(long companyId, long userId);

	/**
	* Returns the secret key where companyId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching secret key, or <code>null</code> if a matching secret key could not be found
	*/
	public SecretKey fetchByC_U(long companyId, long userId,
		boolean retrieveFromCache);

	/**
	* Removes the secret key where companyId = &#63; and userId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the secret key that was removed
	*/
	public SecretKey removeByC_U(long companyId, long userId)
		throws NoSuchSecretKeyException;

	/**
	* Returns the number of secret keies where companyId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param userId the user ID
	* @return the number of matching secret keies
	*/
	public int countByC_U(long companyId, long userId);

	/**
	* Caches the secret key in the entity cache if it is enabled.
	*
	* @param secretKey the secret key
	*/
	public void cacheResult(SecretKey secretKey);

	/**
	* Caches the secret keies in the entity cache if it is enabled.
	*
	* @param secretKeies the secret keies
	*/
	public void cacheResult(java.util.List<SecretKey> secretKeies);

	/**
	* Creates a new secret key with the primary key. Does not add the secret key to the database.
	*
	* @param secretKeyId the primary key for the new secret key
	* @return the new secret key
	*/
	public SecretKey create(long secretKeyId);

	/**
	* Removes the secret key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param secretKeyId the primary key of the secret key
	* @return the secret key that was removed
	* @throws NoSuchSecretKeyException if a secret key with the primary key could not be found
	*/
	public SecretKey remove(long secretKeyId) throws NoSuchSecretKeyException;

	public SecretKey updateImpl(SecretKey secretKey);

	/**
	* Returns the secret key with the primary key or throws a {@link NoSuchSecretKeyException} if it could not be found.
	*
	* @param secretKeyId the primary key of the secret key
	* @return the secret key
	* @throws NoSuchSecretKeyException if a secret key with the primary key could not be found
	*/
	public SecretKey findByPrimaryKey(long secretKeyId)
		throws NoSuchSecretKeyException;

	/**
	* Returns the secret key with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param secretKeyId the primary key of the secret key
	* @return the secret key, or <code>null</code> if a secret key with the primary key could not be found
	*/
	public SecretKey fetchByPrimaryKey(long secretKeyId);

	@Override
	public java.util.Map<java.io.Serializable, SecretKey> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the secret keies.
	*
	* @return the secret keies
	*/
	public java.util.List<SecretKey> findAll();

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
	public java.util.List<SecretKey> findAll(int start, int end);

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
	public java.util.List<SecretKey> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SecretKey> orderByComparator);

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
	public java.util.List<SecretKey> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SecretKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the secret keies from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of secret keies.
	*
	* @return the number of secret keies
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}