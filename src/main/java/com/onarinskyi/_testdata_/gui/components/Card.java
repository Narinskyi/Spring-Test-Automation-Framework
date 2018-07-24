package com.onarinskyi._testdata_.gui.components;

import com.onarinskyi.annotations.FindBy;
import com.onarinskyi.annotations.PageComponentClass;
import com.onarinskyi.gui.AbstractPageComponent;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

@PageComponentClass
public class Card extends AbstractPageComponent {

    {
        locator = By.cssSelector("div.itemscontainer");
    }

    @PageComponentClass
    public static class Car extends Card {

        @FindBy(css = "h4.RTL b")
        private By title;

        @FindBy(css = "h4.RTL a")
        private By link;

        public String getTitle() {
            driver.waitForAJAX();
            return driver.getElementText(title);
        }

        @Step
        public void clickOnTitleLink() {
            driver.clickOn(link);
        }
    }
}
