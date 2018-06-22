package com.onarinskyi.utils;

import com.onarinskyi.annotations.Url;
import com.onarinskyi.core.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component
@PropertySource("classpath:driver.properties")
public class UrlResolver {

    private static final Logger logger = Logger.getLogger(UrlResolver.class);

    @Value("${base.url}")
    private String applicationBaseUrl;

    public String getApplicationBaseUrl() {
        return applicationBaseUrl;
    }

    public String resolveUrlFor(Page page) {
        try {
            return getPropertiesBasedUrl(page);
        } catch (MalformedURLException e1) {
            logger.warn("Application base url is malformed. Please review it!");
            try {
                return getClassAnnotationBasedUrl(page);
            } catch (MalformedURLException e2) {
                logger.warn("Application url in class: " + page.getClass().getSimpleName() + " is malformed. Please review it!");
                throw new RuntimeException("No valid URL was found in page declaration or properties file");
            }
        }
    }

    private String getPropertiesBasedUrl(Page page) throws MalformedURLException {
        applicationBaseUrl = applicationBaseUrl.endsWith("/") ? applicationBaseUrl : applicationBaseUrl + "/";
        new URL(applicationBaseUrl);

        String currentPageUrlAnnotationValue = getUrlAnnotationValue(page.getClass());
        String currentPageFullUrl = currentPageUrlAnnotationValue.contains("http") ?
                applicationBaseUrl + currentPageUrlAnnotationValue.substring(currentPageUrlAnnotationValue.lastIndexOf("/")) :
                applicationBaseUrl + currentPageUrlAnnotationValue;
        currentPageFullUrl = currentPageFullUrl.replaceAll("//", "/");
        new URL(currentPageFullUrl);

        return currentPageFullUrl;
    }

    private String getClassAnnotationBasedUrl(Page page) throws MalformedURLException {
        return new URL(getUrlAnnotationValue(page.getClass())).toString();
    }

    public static String getUrlAnnotationValue(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Url.class)) {
            return clazz.getAnnotation(Url.class).value();
        }
        return "";
    }
}
