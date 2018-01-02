package test_data.gui.components;

import com.onarinskyi.annotations.FindBy;
import com.onarinskyi.core.AbstractPageComponent;
import org.openqa.selenium.By;

public class PromoArea extends AbstractPageComponent {

    @FindBy(id = "gw-desktop-herotator")
    private By promoAreaBlock;

    public boolean isVisible() {
        return driver.isElementVisible(promoAreaBlock);
    }
}
