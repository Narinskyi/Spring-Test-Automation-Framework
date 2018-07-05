package com.onarinskyi._testdata_.gui.components;

import com.onarinskyi.annotations.PageComponent;
import com.onarinskyi.annotations.PageComponentClass;
import com.onarinskyi.gui.AbstractPageComponent;

@PageComponentClass
public class Filter extends AbstractPageComponent {

    @PageComponent
    private RadioGroup.Stars radioStars;

    @PageComponent
    private Slider.Price sliderPrice;

    @PageComponentClass
    public static class Cars extends Filter {
        @PageComponent
        private RadioGroup.CarType radioCarType;

    }
}
