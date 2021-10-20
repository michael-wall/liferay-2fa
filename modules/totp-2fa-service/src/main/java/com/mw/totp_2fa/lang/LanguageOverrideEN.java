package com.mw.totp_2fa.lang;

import com.liferay.portal.kernel.language.UTF8Control;

import java.util.Enumeration;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael Wall / siehe https://help.liferay.com/hc/en-us/articles/360017886312-Overriding-Global-Language-Keys
 */
@Component(
	immediate = true,
    service = ResourceBundle.class,
    property = { "language.id=en_US" }
)
public class LanguageOverrideEN extends ResourceBundle {

	@Override
    protected Object handleGetObject(String key) {
        return _resourceBundle.getObject(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return _resourceBundle.getKeys();
    }

    private final ResourceBundle _resourceBundle = ResourceBundle.getBundle(
        "content.Language", UTF8Control.INSTANCE);
}