package com.mw.totp_2fa.config;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;
import com.mw.totp_2fa.api.TOTP_2FAGenerator;

import aQute.bnd.annotation.metatype.Meta;
import aQute.bnd.annotation.metatype.Meta.Type;

@ExtendedObjectClassDefinition(category = "totp_2fa", scope = ExtendedObjectClassDefinition.Scope.SYSTEM)
//@Meta.OCD(id = TOTP_2FAConfiguration.PID, localization = "content/TOTP_2FALanguage", name = "configuration.name.totp_2fa")
@Meta.OCD(id = TOTP_2FAConfiguration.PID, name = "configuration.name.totp_2fa")
public interface TOTP_2FAConfiguration {
	public static final String ALGORITHM = "SHA1"; // Intentionally not externalized (yet).

	public static final String PID = "com.mw.totp_2fa.config.TOTP_2FAConfiguration";

	@Meta.AD(deflt = "false", required = false, type = Type.Boolean, name = "configuration.loginTotp2faEnabled.name", description = "configuration.loginTotp2faEnabled.desc")
	public boolean loginTotp2faEnabled();

	@Meta.AD(deflt = "", required = false, type = Type.String, name = "configuration.loginTotp2faSkipUserRole.name", description = "configuration.loginTotp2faSkipUserRole.desc")
	public String loginTotp2faSkipUserRole();
	
	@Meta.AD(deflt = "Banking Demo", required = false, type = Type.String, name = "configuration.applicationName.name", description = "configuration.applicationName.desc")
	public String applicationName();
	
	@Meta.AD(deflt = "Banking Demo", required = false, type = Type.String, name = "configuration.qrcodeIssuer.name", description = "configuration.qrcodeIssuer.desc")
	public String qrcodeIssuer();
	
	@Meta.AD(deflt = "http://localhost:8080/o/qrcode?token={token}&t={t}", required = false, type = Type.String, name = "configuration.qrcodeUrl.name", description = "configuration.qrcodeUrl.desc")
	public String qrcodeUrl();
	
	@Meta.AD(deflt = "http://localhost:8080/o/qrcoderesend?token={token}&t={t}", required = false, type = Type.String, name = "configuration.qrcodeResendUrl.name", description = "configuration.qrcodeResendUrl.desc")
	public String qrcodeResendUrl();
	
	@Meta.AD(deflt = "this_is_a_secret_do_not_tell_anyone", required = false, type = Type.String, name = "configuration.qrcodeUrlJWTSecret.name", description = "configuration.qrcodeUrlJWTSecret.desc")
	public String qrcodeUrlJWTSecret();

	@Meta.AD(deflt = "60", required = false, min = "0", type = Type.Integer, name = "configuration.qrcodeUrlDurationMinutes.name", description = "configuration.qrcodeUrlDurationMinutes.desc")
	public int qrcodeUrlDurationMinutes();
	
	@Meta.AD(deflt = "banking.demo@liferay.com", required = false, type = Type.String, name = "configuration.qrcodeEmailFrom.name", description = "configuration.qrcodeEmailFrom.desc")
	public String qrcodeEmailFrom();
	
	@Meta.AD(deflt = "Banking Demo", required = false, type = Type.String, name = "configuration.qrcodeEmailFromLabel.name", description = "configuration.qrcodeEmailFromLabel.desc")
	public String qrcodeEmailFromLabel();
	
	@Meta.AD(deflt = "true", required = false, type = Type.Boolean, name = "configuration.showSecretKeysOnAccountScreens.name", description = "configuration.showSecretKeysOnAccountScreens.desc")
	public boolean showSecretKeysOnAccountScreens();
	
	@Meta.AD(deflt = "false", required = false, type = Type.Boolean, name = "configuration.allowForTimeSkew.name", description = "configuration.allowForTimeSkew.desc")
	public boolean allowForTimeSkew();

	@Meta.AD(deflt = "6", required = false, optionLabels = {"6", "7", "8"}, optionValues = {"6", "7", "8"}, type = Type.Integer, name = "configuration.authenticatorCodeLength.name", description = "configuration.authenticatorCodeLength.desc")
	public int authenticatorCodeLength();
	
	@Meta.AD(deflt = "30", required = false, optionLabels = {"30", "60"}, optionValues = {"30", "60"}, type = Type.Integer, name = "configuration.authenticatorCodeDuration.name", description = "configuration.authenticatorCodeDuration.desc")
	public int authenticatorCodeDuration();
	
	@Meta.AD(deflt = TOTP_2FAGenerator.TOTP_API_IMPLEMENTATIONS.JAVA_OPT, required = false, optionLabels = {TOTP_2FAGenerator.TOTP_API_LABELS.JAVA_OPT, TOTP_2FAGenerator.TOTP_API_LABELS.J256}, optionValues = {TOTP_2FAGenerator.TOTP_API_IMPLEMENTATIONS.JAVA_OPT, TOTP_2FAGenerator.TOTP_API_IMPLEMENTATIONS.J256},  type = Type.String, name = "configuration.totp2faImplementation.name", description = "configuration.totp2faImplementation.desc")
	public String totp2faImplementation();
}