package com.onarinskyi.config;

import com.onarinskyi.driver.WebDriverDecorator;
import com.onarinskyi.driver.WebDriverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DriverConfig {

    private WebDriverFactory driverFactory;

    @Autowired
    public DriverConfig(WebDriverFactory driverFactory) {
        this.driverFactory = driverFactory;
    }

    @Bean
    @Scope("prototype")
    public ThreadLocal<WebDriverDecorator> threadLocal() {
        return ThreadLocal.withInitial(driverFactory::getInitialDriver);
    }
}