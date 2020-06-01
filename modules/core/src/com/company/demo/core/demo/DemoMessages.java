package com.company.demo.core.demo;

import com.haulmont.cuba.core.sys.MessagesImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Properties;
import java.util.Set;

public class DemoMessages extends MessagesImpl {

    @Override
    protected String searchIncludes(Properties properties, String key, Locale locale, Locale truncatedLocale, Set<String> passedPacks) {
        String includesProperty = properties.getProperty("@include");
        if (includesProperty != null) {
            // multiple includes separated by comma
            String[] includes = StringUtils.split(includesProperty, " ,");
            if (includes != null && includes.length > 0) {
                ArrayUtils.reverse(includes);
                for (String includePath : includes) {
                    includePath = StringUtils.trimToNull(includePath);
                    if (includePath != null) {
                        String message;
                        Locale userLocale = getUserLocale();
                        if (truncatedLocale == null && userLocale.equals(locale)) {
                            message = searchMessage(includePath, key, locale, userLocale, passedPacks);
                        } else {
                            message = searchMessage(includePath, key, locale, truncatedLocale, passedPacks);
                        }
//                        if (truncatedLocale == null) {
//                            message = searchMessage(includePath, key, locale, messageTools.getDefaultLocale(), passedPacks);
//                        } else {
//                            message = searchMessage(includePath, key, locale, truncatedLocale, passedPacks);
//                        }
                        if (message != null) {
                            return message;
                        }
                    }
                }
            }
        }
        return null;
    }

}
