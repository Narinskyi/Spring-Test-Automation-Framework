package test_data.gui.components;

import com.onarinskyi.annotations.FindBy;
import com.onarinskyi.core.AbstractPageComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultsFilter extends AbstractPageComponent {

    @FindBy(css = "#leftNavContainer ul h4")
    private By listOfCategories;

    public List<WebElement> getFoundCategoriesHeaders() {
        return driver.findElements(listOfCategories);
    }
}
