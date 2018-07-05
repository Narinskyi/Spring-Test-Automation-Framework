package com.onarinskyi._testdata_.gui.components;

import com.onarinskyi.annotations.PageComponentClass;
import com.onarinskyi.gui.AbstractPageComponent;

@PageComponentClass
public class Slider extends AbstractPageComponent {

    @PageComponentClass
    public static class Price extends Slider {

    }
}
