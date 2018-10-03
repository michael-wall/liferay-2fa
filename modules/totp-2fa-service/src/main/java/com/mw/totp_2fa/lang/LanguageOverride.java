package com.mw.totp_2fa.lang;

import com.liferay.portal.kernel.language.UTF8Control;

import java.util.Enumeration;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael Wall
 */
@Component(
	immediate = true,
    property = { "language.id=en_US" }, 
    service = ResourceBundle.class
)
public class LanguageOverride extends ResourceBundle {

	@Override
    protected Object handleGetObject(String key) {
        return _resourceBundle.getObject(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return _resourceBundle.getKeys();
    }

    private final ResourceBundle _resourceBundle = ResourceBundle.getBundle(
        "content.TOTP_2FALanguage", UTF8Control.INSTANCE);
}