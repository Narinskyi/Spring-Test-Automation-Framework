package com.onarinskyi._testdata_.gui.pages;

import com.onarinskyi.annotations.FindBy;
import com.onarinskyi.annotations.PageComponent;
import com.onarinskyi.annotations.PageObjectClass;
import com.onarinskyi.annotations.Url;
import com.onarinskyi._testdata_.gui.components.Header;
import com.onarinskyi._testdata_.gui.components.PromoArea;
import org.openqa.selenium.By;

@PageObjectClass
@Url("/")
public class HomePage extends BasePage {

    @PageComponent
    private PromoArea promoArea;

    @PageComponent
    private Header header;

    @FindBy(css = ".dummy")
    private By dummy;

    public boolean isPromoAreaVisible() {
        return promoArea.isVisible();
    }

    public void findProduct(String product) {
        header.searchFor(product);
    }

    public void findDummy() {
        driver.findElement(dummy);
    }
}
