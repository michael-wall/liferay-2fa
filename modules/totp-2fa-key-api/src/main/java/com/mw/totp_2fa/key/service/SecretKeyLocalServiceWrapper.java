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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SecretKeyLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SecretKeyLocalService
 * @generated
 */
@ProviderType
public class SecretKeyLocalServiceWrapper implements SecretKeyLocalService,
	ServiceWrapper<SecretKeyLocalService> {
	public SecretKeyLocalServiceWrapper(
		SecretKeyLocalService secretKeyLocalService) {
		_secretKeyLocalService = secretKeyLocalService;
	}

	/**
	* Adds the secret key to the database. Also notifies the appropriate model listeners.
	*
	* @param secretKey the secret key
	* @return the secret key that was added
	*/
	@Override
	public com.mw.totp_2fa.key.model.SecretKey addSecretKey(
		com.mw.totp_2fa.key.model.SecretKey secretKey) {
		return _secretKeyLocalService.addSecretKey(secretKey);
	}

	/**
	* Generate secret key and persist it.
	* Note: A secretKey is generated for any user, regardless of whether they are an Administrator or in the 'Login TOTP 2FA Skip User Role' group.
	*
	* @param user
	* @return
	*/
	@Override
	public com.mw.totp_2fa.key.model.SecretKey addSecretKey(
		com.liferay.portal.kernel.model.User user) {
		return _secretKeyLocalService.addSecretKey(user);
	}

	/**
	* Creates a new secret key with the primary key. Does not add the secret key to the database.
	*
	* @param secretKeyId the primary key for the new secret key
	* @return the new secret key
	*/
	@Override
	public com.mw.totp_2fa.key.model.SecretKey createSecretKey(long secretKeyId) {
		return _secretKeyLocalService.createSecretKey(secretKeyId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _secretKeyLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the secret key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param secretKeyId the primary key of the secret key
	* @return the secret key that was removed
	* @throws PortalException if a secret key with the primary key could not be found
	*/
	@Override
	public com.mw.totp_2fa.key.model.SecretKey deleteSecretKey(long secretKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _secretKeyLocalService.deleteSecretKey(secretKeyId);
	}

	/**
	* Deletes the secret key from the database. Also notifies the appropriate model listeners.
	*
	* @param secretKey the secret key
	* @return the secret key that was removed
	*/
	@Override
	public com.mw.totp_2fa.key.model.SecretKey deleteSecretKey(
		com.mw.totp_2fa.key.model.SecretKey secretKey) {
		return _secretKeyLocalService.deleteSecretKey(secretKey);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _secretKeyLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _secretKeyLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _secretKeyLocalService.dynamicQuery(dynamicQuery, start, end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _secretKeyLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _secretKeyLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _secretKeyLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.mw.totp_2fa.key.model.SecretKey fetchSecretKey(long secretKeyId) {
		return _secretKeyLocalService.fetchSecretKey(secretKeyId);
	}

	@Override
	public com.mw.totp_2fa.key.model.SecretKey fetchSecretKeyByUserId(
		long companyId, long userId) {
		return _secretKeyLocalService.fetchSecretKeyByUserId(companyId, userId);
	}

	@Override
	public com.mw.totp_2fa.key.model.SecretKey fetchSecretKeyByUserId(
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		long userId) {
		return _secretKeyLocalService.fetchSecretKeyByUserId(serviceContext,
			userId);
	}

	/**
	* Returns the secret key with the matching UUID and company.
	*
	* @param uuid the secret key's UUID
	* @param companyId the primary key of the company
	* @return the matching secret key, or <code>null</code> if a matching secret key could not be found
	*/
	@Override
	public com.mw.totp_2fa.key.model.SecretKey fetchSecretKeyByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _secretKeyLocalService.fetchSecretKeyByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Generate a SecretKey String.
	*
	* @return
	*/
	@Override
	public java.lang.String generateSecretKey() {
		return _secretKeyLocalService.generateSecretKey();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _secretKeyLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _secretKeyLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _secretKeyLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _secretKeyLocalService.getPersistedModel(primaryKeyObj);
	}

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
	@Override
	public java.util.List<com.mw.totp_2fa.key.model.SecretKey> getSecretKeies(
		int start, int end) {
		return _secretKeyLocalService.getSecretKeies(start, end);
	}

	/**
	* Returns the number of secret keies.
	*
	* @return the number of secret keies
	*/
	@Override
	public int getSecretKeiesCount() {
		return _secretKeyLocalService.getSecretKeiesCount();
	}

	/**
	* Returns the secret key with the primary key.
	*
	* @param secretKeyId the primary key of the secret key
	* @return the secret key
	* @throws PortalException if a secret key with the primary key could not be found
	*/
	@Override
	public com.mw.totp_2fa.key.model.SecretKey getSecretKey(long secretKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _secretKeyLocalService.getSecretKey(secretKeyId);
	}

	/**
	* Returns the secret key with the matching UUID and company.
	*
	* @param uuid the secret key's UUID
	* @param companyId the primary key of the company
	* @return the matching secret key
	* @throws PortalException if a matching secret key could not be found
	*/
	@Override
	public com.mw.totp_2fa.key.model.SecretKey getSecretKeyByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _secretKeyLocalService.getSecretKeyByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Updates the secret key in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param secretKey the secret key
	* @return the secret key that was updated
	*/
	@Override
	public com.mw.totp_2fa.key.model.SecretKey updateSecretKey(
		com.mw.totp_2fa.key.model.SecretKey secretKey) {
		return _secretKeyLocalService.updateSecretKey(secretKey);
	}

	/**
	* Retrieves an existing SecretKey Entity and updates it with a new Secret Key. Creates and persists a new SecretKey Entity if not found.
	*
	* @param user
	* @return
	*/
	@Override
	public com.mw.totp_2fa.key.model.SecretKey updateSecretKey(
		com.liferay.portal.kernel.model.User user) {
		return _secretKeyLocalService.updateSecretKey(user);
	}

	@Override
	public SecretKeyLocalService getWrappedService() {
		return _secretKeyLocalService;
	}

	@Override
	public void setWrappedService(SecretKeyLocalService secretKeyLocalService) {
		_secretKeyLocalService = secretKeyLocalService;
	}

	private SecretKeyLocalService _secretKeyLocalService;
}