package com.onarinskyi._testdata_.gui.pages;

import com.onarinskyi._testdata_.gui.components.Button;
import com.onarinskyi._testdata_.gui.components.Card;
import com.onarinskyi._testdata_.gui.components.Filter;
import com.onarinskyi._testdata_.gui.components.Header;
import com.onarinskyi._testdata_.gui.model.Car;
import com.onarinskyi._testdata_.gui.model.CarType;
import com.onarinskyi.annotations.ui.PageComponent;
import com.onarinskyi.annotations.ui.PageObjectClass;
import com.onarinskyi.annotations.ui.Url;
import com.onarinskyi.gui.AbstractPage;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import ru.yandex.qatools.allure.annotations.Step;


@Url("/cars")
@PageObjectClass
public class CarsPage extends AbstractPage {

    @PageComponent
    private Filter.Cars filterSearch;

    @PageComponent
    private Card.Car cardCar;

    @PageComponent
    private Header.Cars header;

    // demonstration of ancestor selectors
    @PageComponent(css = "form[role='search']")
    private Button.Search buttonSearchInHeader;

    @PageComponent(name = "fFilters")
    private Button.Search buttonSearchInFilter;

    @Step
    public void clickSearchInHeader() {
        buttonSearchInHeader.click();
    }

    @Step
    public void clickSearchInFilter() {
        buttonSearchInFilter.click();
    }

    @Step
    public void selectCarRating(int rating) {
        filterSearch.selectRating(rating);
    }

    @Step
    public void selectCarType(CarType type) {
        filterSearch.selectCarType(type);
    }

    @Step
    public void clickSearch() {
        filterSearch.clickSearch();
    }

    @Step
    public void find(Car car) {
        selectCarRating(car.getRaiting());
        selectCarType(car.getType());
        clickSearch();
    }

    @Step
    public void verifySearchResultsContain(Car car) {
        MatcherAssert.assertThat(cardCar.getTitle(), CoreMatchers.containsString(car.getName()));
    }

    @Step
    public void openCarDetails() {
        cardCar.clickOnTitleLink();
    }
}
