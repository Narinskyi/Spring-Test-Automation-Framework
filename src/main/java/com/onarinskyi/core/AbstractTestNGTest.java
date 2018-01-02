package com.onarinskyi.core;

import com.onarinskyi.config.AppConfig;
import com.onarinskyi.driver.DriverManager;
import com.onarinskyi.driver.WebDriverDecorator;
import com.onarinskyi.listeners.TestNGOnTestFailureListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import javax.annotation.PostConstruct;

@Listeners(TestNGOnTestFailureListener.class)
@ContextConfiguration(classes = AppConfig.class)
public abstract class AbstractTestNGTest extends AbstractTestNGSpringContextTests {

    protected WebDriverDecorator driver;

    protected SoftAssert softly = new SoftAssert();

    @PostConstruct
    public void init() {
        Reflection.instantiateAnnotatedFields(this);
        driver = DriverManager.getDriver();
    }

    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
    }
}

