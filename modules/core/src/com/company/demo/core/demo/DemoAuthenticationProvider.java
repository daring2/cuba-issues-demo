package com.company.demo.core.demo;

import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.security.auth.providers.LoginPasswordAuthenticationProvider;

import java.util.Locale;

public class DemoAuthenticationProvider extends LoginPasswordAuthenticationProvider {

    public DemoAuthenticationProvider(Persistence persistence, Messages messages) {
        super(persistence, messages);
    }

    @Override
    protected String getInvalidCredentialsMessage(String login, Locale locale) {
        return messages.formatMessage("com.company.demo.core.demo", "LoginException.InvalidLoginOrPassword", locale, login);
    }

}
