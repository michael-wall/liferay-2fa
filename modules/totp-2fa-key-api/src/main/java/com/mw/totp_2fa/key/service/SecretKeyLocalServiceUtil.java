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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for SecretKey. This utility wraps
 * {@link com.mw.totp_2fa.key.service.impl.SecretKeyLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SecretKeyLocalService
 * @see com.mw.totp_2fa.key.service.base.SecretKeyLocalServiceBaseImpl
 * @see com.mw.totp_2fa.key.service.impl.SecretKeyLocalServiceImpl
 * @generated
 */
@ProviderType
public class SecretKeyLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.mw.totp_2fa.key.service.impl.SecretKeyLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the secret key to the database. Also notifies the appropriate model listeners.
	*
	* @param secretKey the secret key
	* @return the secret key that was added
	*/
	public static com.mw.totp_2fa.key.model.SecretKey addSecretKey(
		com.mw.totp_2fa.key.model.SecretKey secretKey) {
		return getService().addSecretKey(secretKey);
	}

	/**
	* Generate secret key and persist it.
	* Note: A secretKey is generated for any user, regardless of whether they are an Administrator or in the 'Login TOTP 2FA Skip User Role' group.
	*
	* @param user
	* @return
	*/
	public static com.mw.totp_2fa.key.model.SecretKey addSecretKey(
		com.liferay.portal.kernel.model.User user) {
		return getService().addSecretKey(user);
	}

	/**
	* Creates a new secret key with the primary key. Does not add the secret key to the database.
	*
	* @param secretKeyId the primary key for the new secret key
	* @return the new secret key
	*/
	public static com.mw.totp_2fa.key.model.SecretKey createSecretKey(
		long secretKeyId) {
		return getService().createSecretKey(secretKeyId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the secret key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param secretKeyId the primary key of the secret key
	* @return the secret key that was removed
	* @throws PortalException if a secret key with the primary key could not be found
	*/
	public static com.mw.totp_2fa.key.model.SecretKey deleteSecretKey(
		long secretKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSecretKey(secretKeyId);
	}

	/**
	* Deletes the secret key from the database. Also notifies the appropriate model listeners.
	*
	* @param secretKey the secret key
	* @return the secret key that was removed
	*/
	public static com.mw.totp_2fa.key.model.SecretKey deleteSecretKey(
		com.mw.totp_2fa.key.model.SecretKey secretKey) {
		return getService().deleteSecretKey(secretKey);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.mw.totp_2fa.key.model.SecretKey fetchSecretKey(
		long secretKeyId) {
		return getService().fetchSecretKey(secretKeyId);
	}

	public static com.mw.totp_2fa.key.model.SecretKey fetchSecretKeyByUserId(
		long companyId, long userId) {
		return getService().fetchSecretKeyByUserId(companyId, userId);
	}

	public static com.mw.totp_2fa.key.model.SecretKey fetchSecretKeyByUserId(
		com.liferay.portal.kernel.service.ServiceContext serviceContext,
		long userId) {
		return getService().fetchSecretKeyByUserId(serviceContext, userId);
	}

	/**
	* Returns the secret key with the matching UUID and company.
	*
	* @param uuid the secret key's UUID
	* @param companyId the primary key of the company
	* @return the matching secret key, or <code>null</code> if a matching secret key could not be found
	*/
	public static com.mw.totp_2fa.key.model.SecretKey fetchSecretKeyByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().fetchSecretKeyByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Generate a SecretKey String.
	*
	* @return
	*/
	public static java.lang.String generateSecretKey() {
		return getService().generateSecretKey();
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.mw.totp_2fa.key.model.SecretKey> getSecretKeies(
		int start, int end) {
		return getService().getSecretKeies(start, end);
	}

	/**
	* Returns the number of secret keies.
	*
	* @return the number of secret keies
	*/
	public static int getSecretKeiesCount() {
		return getService().getSecretKeiesCount();
	}

	/**
	* Returns the secret key with the primary key.
	*
	* @param secretKeyId the primary key of the secret key
	* @return the secret key
	* @throws PortalException if a secret key with the primary key could not be found
	*/
	public static com.mw.totp_2fa.key.model.SecretKey getSecretKey(
		long secretKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSecretKey(secretKeyId);
	}

	/**
	* Returns the secret key with the matching UUID and company.
	*
	* @param uuid the secret key's UUID
	* @param companyId the primary key of the company
	* @return the matching secret key
	* @throws PortalException if a matching secret key could not be found
	*/
	public static com.mw.totp_2fa.key.model.SecretKey getSecretKeyByUuidAndCompanyId(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSecretKeyByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Updates the secret key in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param secretKey the secret key
	* @return the secret key that was updated
	*/
	public static com.mw.totp_2fa.key.model.SecretKey updateSecretKey(
		com.mw.totp_2fa.key.model.SecretKey secretKey) {
		return getService().updateSecretKey(secretKey);
	}

	/**
	* Retrieves an existing SecretKey Entity and updates it with a new Secret Key. Creates and persists a new SecretKey Entity if not found.
	*
	* @param user
	* @return
	*/
	public static com.mw.totp_2fa.key.model.SecretKey updateSecretKey(
		com.liferay.portal.kernel.model.User user) {
		return getService().updateSecretKey(user);
	}

	public static SecretKeyLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SecretKeyLocalService, SecretKeyLocalService> _serviceTracker =
		ServiceTrackerFactory.open(SecretKeyLocalService.class);
}