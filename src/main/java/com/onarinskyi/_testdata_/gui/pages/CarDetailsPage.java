package com.onarinskyi._testdata_.gui.pages;

import com.onarinskyi.annotations.ui.FindBy;
import com.onarinskyi.annotations.ui.PageObjectClass;
import com.onarinskyi.gui.AbstractPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;


@PageObjectClass
public class CarDetailsPage extends AbstractPage {

    @FindBy(css = "div.fotorama")
    private By carPhoto;

    @Step
    public void verifyCarPhotoIsDisplayed() {
        Assert.assertTrue(driver.isPresent(carPhoto));
    }
}
