package com.onarinskyi._testdata_.gui.components;

import com.onarinskyi.annotations.ui.PageComponent;
import com.onarinskyi.annotations.ui.PageComponentClass;
import com.onarinskyi.gui.AbstractPageComponent;

@PageComponentClass
public class Header extends AbstractPageComponent {

    @PageComponentClass
    public static class Cars extends Header {

        @PageComponent(text = "Search")
        private Button.Search buttonSearch;

    }
}
