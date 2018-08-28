package com.onarinskyi._testdata_.gui.components;

import com.onarinskyi.annotations.ui.PageComponentClass;
import com.onarinskyi.gui.AbstractPageComponent;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

@PageComponentClass
public class RadioGroup extends AbstractPageComponent {

    private By input;

    @Step
    public void select(int position) {
        input = By.cssSelector("ins.iCheck-helper");

        driver.findElement(locator).findElements(input).get(position).click();
    }

    @Step
    public void select(String labelText) {
        input = By.cssSelector(String.format("label[for='%s']", labelText));

        driver.findElement(locator).findElement(input).click();
    }

    @PageComponentClass
    public static class Stars extends RadioGroup {
        {
            locator = By.cssSelector(".rating");
        }

        @Override
        public void select(int position) {
            super.select(position - 1);
        }
    }

    @PageComponentClass
    public static class CarType extends RadioGroup {
        {
            locator = By.id("collapse3");
        }

        @Step
        public void select(com.onarinskyi._testdata_.gui.model.CarType carType) {
            select(carType.getDisplayedText());
        }
    }
}
