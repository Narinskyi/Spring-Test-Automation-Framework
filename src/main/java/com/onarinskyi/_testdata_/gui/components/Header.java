package com.onarinskyi._testdata_.gui.components;

import com.onarinskyi.annotations.FindBy;
import com.onarinskyi.annotations.PageComponentClass;
import com.onarinskyi.gui.AbstractPageComponent;
import com.onarinskyi._testdata_.gui.model.Category;
import org.openqa.selenium.By;

@PageComponentClass
public class Header extends AbstractPageComponent {

    @FindBy(id = "twotabsearchtextbox")
    private By inputSearch;

    @FindBy(css = "nav-search-submit-text+input")
    private By buttonSearch;

    @FindBy(id = "searchDropdownBox")
    private By selectSearchType;

    public void searchFor(String request) {
        driver.type(inputSearch, request);
        driver.clickOn(buttonSearch);
    }

    public void searchFor(String request, Category category) {
        driver.type(inputSearch, request);
        driver.selectByVisibleText(selectSearchType, category.value());
        driver.clickOn(buttonSearch);
    }
}
