package com.onarinskyi._testdata_.gui.components;

import com.onarinskyi.annotations.PageComponentClass;
import com.onarinskyi.gui.AbstractPageComponent;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

@PageComponentClass
public class RadioGroup extends AbstractPageComponent {

    private By input;

    @Step
    public void select(int position) {
        input = By.cssSelector("input");

        driver.findElement(locator).findElements(input).get(position).click();
        driver.waitForAngular();
    }

    @Step
    public void select(String labelText) {
        input = By.cssSelector(String.format("label[for='%s']", labelText));
        driver.findElement(locator).findElement(input).click();
        driver.waitForAngular();
    }

    @PageComponentClass
    public static class Stars extends RadioGroup {
//        {
//            locator =
//        }


    }

    @PageComponentClass
    public static class CarType extends RadioGroup {

    }
}
