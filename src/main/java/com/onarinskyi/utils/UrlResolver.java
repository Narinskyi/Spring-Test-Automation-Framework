package com.onarinskyi.utils;

import com.onarinskyi.core.Page;
import com.onarinskyi.core.Reflection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component
@PropertySource("classpath:application.properties")
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
                throw new RuntimeException("No valid URL was found in page declaration or application.properties file");
            }
        }
    }

//    public URL resolveUrl(String hubHostString, boolean useGrid) {
//        try {
//            return new URL(hubHostString);
//        } catch (MalformedURLException e) {
//            if (useGrid){
//                throw new RuntimeException("No valid URL for grid was found environment.properties file");
//            }
//            else {
//                logger.warn("Grid URL in environment.properties file is malformed. Please review it!");
//            }
//        }
//        return null;
//    }

    private String getPropertiesBasedUrl(Page page) throws MalformedURLException {
        applicationBaseUrl = applicationBaseUrl.endsWith("/") ? applicationBaseUrl : applicationBaseUrl + "/";
        new URL(applicationBaseUrl);

        String currentPageUrlAnnotationValue = Reflection.getUrlAnnotationValue(page.getClass());
        String currentPageFullUrl = currentPageUrlAnnotationValue.contains("http") ?
                applicationBaseUrl + currentPageUrlAnnotationValue.substring(currentPageUrlAnnotationValue.lastIndexOf("/")) :
                applicationBaseUrl + currentPageUrlAnnotationValue;
        currentPageFullUrl = currentPageFullUrl.replaceAll("//", "/");
        new URL(currentPageFullUrl);

        return currentPageFullUrl;
    }

    private String getClassAnnotationBasedUrl(Page page) throws MalformedURLException {
        return new URL(Reflection.getUrlAnnotationValue(page.getClass())).toString();
    }
}
