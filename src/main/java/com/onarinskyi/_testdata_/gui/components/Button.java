package com.onarinskyi._testdata_.gui.components;

import com.onarinskyi.annotations.PageComponentClass;
import com.onarinskyi.gui.AbstractPageComponent;
import org.openqa.selenium.By;

@PageComponentClass
public class Button extends AbstractPageComponent {

    public void click() {
        driver.clickOn(locator);
    }

    @PageComponentClass
    public static class Search extends Button {
        {
            locator = By.cssSelector("#searchform, #search");
        }
    }

    @PageComponentClass
    public static class Ok extends Button {
        {
            locator = By.cssSelector("#submit");
        }
    }
}
