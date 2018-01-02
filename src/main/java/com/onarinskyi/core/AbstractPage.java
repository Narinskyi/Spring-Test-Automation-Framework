package com.onarinskyi.core;

public abstract class AbstractPage extends AbstractTestEntity implements Page {

    @Override
    public void open() {
        driver.openPage(this);
    }
}
