package com.onarinskyi.core;

import com.onarinskyi.driver.DriverManager;
import com.onarinskyi.driver.WebDriverDecorator;

public abstract class AbstractTestEntity {

    protected WebDriverDecorator driver = DriverManager.getDriver();

    {
        Reflection.instantiateAnnotatedFields(this);
    }
}
