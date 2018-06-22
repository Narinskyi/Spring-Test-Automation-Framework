package com.onarinskyi.core;

import com.onarinskyi.config.AppConfig;
import com.onarinskyi.driver.WebDriverDecorator;
import com.onarinskyi.listeners.TestNGExecutionListener;
import com.onarinskyi.listeners.TestNGSuiteListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import javax.annotation.PostConstruct;

@Listeners({TestNGExecutionListener.class, TestNGSuiteListener.class})
@ContextConfiguration(classes = AppConfig.class)
@TestExecutionListeners(inheritListeners = false,
        listeners = {DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public abstract class AbstractTestNGTest extends AbstractTestNGSpringContextTests {

    @Autowired
    protected WebDriverDecorator driver;

    @Autowired
    private ThreadLocal<WebDriverDecorator> threadLocal;

    protected SoftAssert softly = new SoftAssert();

    @PostConstruct
    public void initAppContext() {
        ApplicationContextManager.initWith(applicationContext);
    }

    @AfterClass(alwaysRun = true)
    public void quitDriver() {
        driver.quit();
        threadLocal.remove();
    }
}

