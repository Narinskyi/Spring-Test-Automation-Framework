package com.onarinskyi._testdata_.gui.components;

import com.onarinskyi.annotations.FindBy;
import com.onarinskyi.annotations.PageComponentClass;
import com.onarinskyi.core.AbstractPageComponent;
import org.openqa.selenium.By;

@PageComponentClass
public class PromoArea extends AbstractPageComponent {

    @FindBy(id = "gw-desktop-herotator")
    private By promoAreaBlock;

    public boolean isVisible() {
        return driver.isVisible(promoAreaBlock);
    }
}
