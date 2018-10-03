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

package com.mw.totp_2fa.key.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.mw.totp_2fa.key.model.SecretKey;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for SecretKey. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see SecretKeyLocalServiceUtil
 * @see com.mw.totp_2fa.key.service.base.SecretKeyLocalServiceBaseImpl
 * @see com.mw.totp_2fa.key.service.impl.SecretKeyLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SecretKeyLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SecretKeyLocalServiceUtil} to access the secret key local service. Add custom service methods to {@link com.mw.totp_2fa.key.service.impl.SecretKeyLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the secret key to the database. Also notifies the appropriate model listeners.
	*
	* @param secretKey the secret key
	* @return the secret key that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SecretKey addSecretKey(SecretKey secretKey);

	/**
	* Generate secret key and persist it.
	* Note: A secretKey is generated for any user, regardless of whether they are an Administrator or in the 'Login TOTP 2FA Skip User Role' group.
	*
	* @param user
	* @return
	*/
	public SecretKey addSecretKey(User user);

	/**
	* Creates a new secret key with the primary key. Does not add the secret key to the database.
	*
	* @param secretKeyId the primary key for the new secret key
	* @return the new secret key
	*/
	public SecretKey createSecretKey(long secretKeyId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the secret key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param secretKeyId the primary key of the secret key
	* @return the secret key that was removed
	* @throws PortalException if a secret key with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public SecretKey deleteSecretKey(long secretKeyId)
		throws PortalException;

	/**
	* Deletes the secret key from the database. Also notifies the appropriate model listeners.
	*
	* @param secretKey the secret key
	* @return the secret key that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public SecretKey deleteSecretKey(SecretKey secretKey);

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.mw.totp_2fa.key.model.impl.SecretKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.mw.totp_2fa.key.model.impl.SecretKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SecretKey fetchSecretKey(long secretKeyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SecretKey fetchSecretKeyByUserId(long companyId, long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SecretKey fetchSecretKeyByUserId(ServiceContext serviceContext,
		long userId);

	/**
	* Returns the secret key with the matching UUID and company.
	*
	* @param uuid the secret key's UUID
	* @param companyId the primary key of the company
	* @return the matching secret key, or <code>null</code> if a matching secret key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SecretKey fetchSecretKeyByUuidAndCompanyId(java.lang.String uuid,
		long companyId);

	/**
	* Generate a SecretKey String.
	*
	* @return
	*/
	public java.lang.String generateSecretKey();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns a range of all the secret keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.mw.totp_2fa.key.model.impl.SecretKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of secret keies
	* @param end the upper bound of the range of secret keies (not inclusive)
	* @return the range of secret keies
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SecretKey> getSecretKeies(int start, int end);

	/**
	* Returns the number of secret keies.
	*
	* @return the number of secret keies
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSecretKeiesCount();

	/**
	* Returns the secret key with the primary key.
	*
	* @param secretKeyId the primary key of the secret key
	* @return the secret key
	* @throws PortalException if a secret key with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SecretKey getSecretKey(long secretKeyId) throws PortalException;

	/**
	* Returns the secret key with the matching UUID and company.
	*
	* @param uuid the secret key's UUID
	* @param companyId the primary key of the company
	* @return the matching secret key
	* @throws PortalException if a matching secret key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SecretKey getSecretKeyByUuidAndCompanyId(java.lang.String uuid,
		long companyId) throws PortalException;

	/**
	* Updates the secret key in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param secretKey the secret key
	* @return the secret key that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SecretKey updateSecretKey(SecretKey secretKey);

	/**
	* Retrieves an existing SecretKey Entity and updates it with a new Secret Key. Creates and persists a new SecretKey Entity if not found.
	*
	* @param user
	* @return
	*/
	public SecretKey updateSecretKey(User user);
}