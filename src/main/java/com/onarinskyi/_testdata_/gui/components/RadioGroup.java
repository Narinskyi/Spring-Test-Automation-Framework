package com.onarinskyi._testdata_.gui.components;

import com.onarinskyi.annotations.ui.PageComponentClass;
import com.onarinskyi.gui.AbstractPageComponent;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

@PageComponentClass
public abstract class RadioGroup extends AbstractPageComponent {

    private By input;

    @Step
    public void select(int position) {
        input = By.cssSelector("ins.iCheck-helper");

        driver.findElements(chained(ancestor, locator, input)).get(position).click();
    }

    @Step
    public void select(String labelText) {
        input = By.cssSelector(String.format("label[for='%s']", labelText));

        driver.clickOn(chained(ancestor, locator, input));
    }

    @PageComponentClass(css = ".rating")
    public static class Stars extends RadioGroup {
        @Override
        public void select(int position) {
            super.select(position - 1);
        }
    }

    @PageComponentClass(id = "collapse3")
    public static class CarType extends RadioGroup {
        @Step
        public void select(com.onarinskyi._testdata_.gui.model.CarType carType) {
            select(carType.getDisplayedText());
        }
    }
}
