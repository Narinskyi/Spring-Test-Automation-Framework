package com.onarinskyi.driver;

import com.onarinskyi.environment.Timeout;
import com.onarinskyi.utils.UrlResolver;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DriverManager {

    private static ThreadLocal<WebDriver> threadLocalDriver;

    private static Timeout timeout;
    private static UrlResolver urlResolver;

    @Autowired
    public DriverManager(ThreadLocal<WebDriver> threadLocal, Timeout timeout, UrlResolver urlResolver) {
        threadLocalDriver = threadLocal;
        DriverManager.timeout = timeout;
        DriverManager.urlResolver = urlResolver;
    }

    static WebDriver getThreadLocalDriver() {
        return threadLocalDriver.get();
    }

    public static WebDriverDecorator getDriver() {
        return new WebDriverDecorator(timeout, urlResolver);
    }

    static void removeDriver() {
        threadLocalDriver.remove();
    }
}
