package com.onarinskyi.listeners;

import com.onarinskyi.driver.WebDriverDecorator;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JUnitOnTestFailureWatcher extends TestWatcher {

    private WebDriverDecorator driver;

//    @Autowired
//    public JUnitOnTestFailureWatcher(WebDriverDecorator driver) {
//        this.driver = driver;
//    }

    @Override
    protected void failed(Throwable e, Description description) {
        driver.takeScreenshot();
    }

    @Override
    protected void finished(Description description) {
        driver.quit();
    }
}
