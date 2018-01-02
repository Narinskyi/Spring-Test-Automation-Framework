package com.onarinskyi.core;

import com.onarinskyi.config.AppConfig;
import com.onarinskyi.driver.WebDriverDecorator;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public abstract class AbstractJUnitTest {

    @Rule
    @Autowired
    protected TestWatcher onFailureWatcher;

//    @Autowired
//    protected WebDriverDecorator driver;

    {
        Reflection.instantiateAnnotatedFields(this);
    }

    //protected SoftAssertions softAssertions = new SoftAssertions();
}

