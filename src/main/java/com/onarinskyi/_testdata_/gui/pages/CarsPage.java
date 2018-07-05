package com.onarinskyi._testdata_.gui.pages;

import com.onarinskyi._testdata_.gui.components.Filter;
import com.onarinskyi.annotations.PageComponent;
import com.onarinskyi.annotations.PageObjectClass;
import com.onarinskyi.gui.AbstractPage;

@PageObjectClass
public class CarsPage extends AbstractPage {

    @PageComponent
    private Filter.Cars filterSearch;
}
