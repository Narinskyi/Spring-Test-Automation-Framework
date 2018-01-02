package com.onarinskyi.listeners;

import com.onarinskyi.driver.DriverManager;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestNGOnTestFailureListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult result) {
        DriverManager.getDriver().takeScreenshot();
    }
}