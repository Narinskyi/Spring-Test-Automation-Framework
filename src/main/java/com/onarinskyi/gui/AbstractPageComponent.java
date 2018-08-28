package com.onarinskyi.gui;

import com.onarinskyi.annotations.ui.PageComponentClass;
import com.onarinskyi.driver.WebDriverDecorator;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

@PageComponentClass
public abstract class AbstractPageComponent {

    @Autowired
    protected WebDriverDecorator driver;

    protected By locator;

    public By getLocator() {
        return locator;
    }
}
