package com.onarinskyi._testdata_.gui.components;

import com.onarinskyi.annotations.ui.PageComponentClass;
import com.onarinskyi.gui.AbstractPageComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ByChained;

@PageComponentClass
public class Button extends AbstractPageComponent {

    public void click() {
        driver.clickOn(new ByChained(ancestor, locator));
    }

    @PageComponentClass(css = "#searchform, #search")
    public static class Search extends Button {
    }

    @PageComponentClass
    public static class Ok extends Button {
        {
            locator = By.cssSelector("#submit");
        }
    }
}
