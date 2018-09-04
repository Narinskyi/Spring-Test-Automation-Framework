package com.onarinskyi._testdata_.gui.components;

import com.onarinskyi.annotations.ui.PageComponentClass;
import com.onarinskyi.gui.AbstractPageComponent;
import ru.yandex.qatools.allure.annotations.Step;

@PageComponentClass
public class Slider extends AbstractPageComponent {

    @PageComponentClass
    public static class Price extends Slider {

        @Step
        public void select(int price) {
        }
    }
}
