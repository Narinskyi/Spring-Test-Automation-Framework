package com.onarinskyi._testdata_.gui.components;

import com.onarinskyi._testdata_.gui.model.CarType;
import com.onarinskyi.annotations.ui.PageComponent;
import com.onarinskyi.annotations.ui.PageComponentClass;
import com.onarinskyi.gui.AbstractPageComponent;
import ru.yandex.qatools.allure.annotations.Step;

@PageComponentClass
public class Filter extends AbstractPageComponent {

    @PageComponent
    private RadioGroup.Stars radioStars;

    @PageComponent
    private Slider.Price sliderPrice;

    @PageComponent(text = "Search")
    private Button.Search buttonSearch;

    @Step
    public void selectRating(int rating) {
        radioStars.select(rating);
    }

    @Step
    public void clickSearch() {
        buttonSearch.click();
    }

    @PageComponentClass
    public static class Cars extends Filter {
        @PageComponent
        private RadioGroup.CarType radioCarType;

        @Step
        public void selectCarType(CarType type) {
            radioCarType.select(type);
        }
    }
}
