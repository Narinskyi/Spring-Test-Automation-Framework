package com.onarinskyi._testdata_.gui.components;

import com.onarinskyi.annotations.ui.PageComponentClass;
import com.onarinskyi.gui.AbstractPageComponent;

@PageComponentClass
public abstract class Header extends AbstractPageComponent {

    @PageComponentClass
    public static class Cars extends Header {
    }
}
