package com.onarinskyi.core;

import com.onarinskyi.annotations.PageObjectClass;
import com.onarinskyi.driver.WebDriverDecorator;
import org.springframework.beans.factory.annotation.Autowired;

@PageObjectClass
public abstract class AbstractPage implements Page {

    @Autowired
    protected WebDriverDecorator driver;

    @Override
    public void open() {
        driver.openPage(this);
    }
}
