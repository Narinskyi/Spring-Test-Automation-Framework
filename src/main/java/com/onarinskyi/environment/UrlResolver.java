package com.onarinskyi.environment;

import com.onarinskyi.annotations.*;
import com.onarinskyi.api.core.Request;
import com.onarinskyi.core.Page;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component
@PropertySource("classpath:driver.properties")
public class UrlResolver {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Value("${base.url}")
    private String uiBaseUrl;

    @Autowired
    private Api api;

    public String getUiBaseUrl() {
        return uiBaseUrl;
    }

    public String getResolvedUrlFor(Object object) {
        try {
            return getPropertiesBasedUrlFor(object);
        } catch (MalformedURLException e1) {
            logger.warn("Application base url is malformed. Please review it!");
            try {
                return getClassAnnotationBasedUrl(object);
            } catch (MalformedURLException e2) {
                logger.warn("Application url in class: " + object.getClass().getSimpleName() + " is malformed. Please review it!");
                throw new RuntimeException("No valid URL was found in page declaration or properties file");
            }
        }
    }

    private String getPropertiesBasedUrlFor(Object object) throws MalformedURLException {
        String baseUrl;

        if (object instanceof Page) {
            baseUrl = uiBaseUrl;
        } else if (object instanceof Request) {
            baseUrl = api.getBaseUrl();
        } else {
            throw new MalformedURLException("Unknown object requesting url");
        }

        baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";

        new URL(baseUrl);

        String urlAnnotation = getUrlAnnotationValue(object.getClass());

        urlAnnotation = urlAnnotation.startsWith("/") ? urlAnnotation.substring(1) : urlAnnotation;

        String fullUrl = urlAnnotation.contains("http") ?
                baseUrl + urlAnnotation.substring(urlAnnotation.lastIndexOf("/")) :
                baseUrl + urlAnnotation;

        new URL(fullUrl);

        return fullUrl;
    }

    private String getClassAnnotationBasedUrl(Object object) throws MalformedURLException {
        return new URL(getUrlAnnotationValue(object.getClass())).toString();
    }

    private String getUrlAnnotationValue(Class<?> clazz) {
        return clazz.isAnnotationPresent(Url.class) ?
                clazz.getAnnotation(Url.class).value() :
                clazz.isAnnotationPresent(Get.class) ?
                        clazz.getAnnotation(Get.class).value() :
                        clazz.isAnnotationPresent(Put.class) ?
                                clazz.getAnnotation(Put.class).value() :
                                clazz.isAnnotationPresent(Post.class) ?
                                        clazz.getAnnotation(Post.class).value() :
                                        clazz.isAnnotationPresent(Delete.class) ?
                                                clazz.getAnnotation(Delete.class).value() : "";
    }
}
