package com.onarinskyi.core;

import com.onarinskyi.driver.WebDriverDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterClass;

public abstract class AbstractUITest extends AbstractTestNGTest {

    @Autowired
    protected WebDriverDecorator driver;

    @Autowired
    private ThreadLocal<WebDriverDecorator> threadLocal;

    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
        threadLocal.remove();
    }
}

