package com.onarinskyi._testdata_.gui.components;

import com.onarinskyi.annotations.ui.PageComponentClass;
import com.onarinskyi.gui.AbstractPageComponent;
import ru.yandex.qatools.allure.annotations.Step;

@PageComponentClass
public abstract class Button extends AbstractPageComponent {

    @Step
    public void click() {
        driver.clickOn(chained(ancestor, locator));
    }

    @PageComponentClass(css = "#searchform, #search")
    public static class Search extends Button {
    }

    @PageComponentClass(id = "submit")
    public static class Ok extends Button {
    }
}
