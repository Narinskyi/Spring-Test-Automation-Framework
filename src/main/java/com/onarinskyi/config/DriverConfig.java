package com.onarinskyi.config;

import com.onarinskyi.driver.WebDriverFactory;
import com.onarinskyi.environment.BrowserType;
import com.onarinskyi.environment.OperatingSystem;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.URL;

@Configuration
@PropertySource("classpath:driver.properties")
public class DriverConfig {

    @Bean
    public ThreadLocal<WebDriver> initialDriver(WebDriverFactory webDriverFactory,
                                   @Value("${browser.type}") String browser,
                                   @Value("${system.os}") String systemOs,
                                   @Value("${grid.hub}") URL hubHostUrl,
                                   @Value("${use.grid}") boolean useGrid) {

        initDrivers(OperatingSystem.of(systemOs));

        BrowserType browserType = BrowserType.of(browser);
        return ThreadLocal.withInitial(() -> useGrid ? webDriverFactory.getDriverOf(browserType, hubHostUrl) :
                webDriverFactory.getDriverOf(browserType));
    }

    private void initDrivers(OperatingSystem systemOs) {
        switch (systemOs) {
            case WINDOWS:
                System.setProperty("webdriver.gecko.driver", "src/drivers/windows/geckodriver.exe");
                System.setProperty("webdriver.chrome.driver", "src/drivers/windows/chromedriver.exe");
                System.setProperty("phantomjs.binary.path", "src/drivers/windows/phantomjs.exe");
            case MACOS:
                System.setProperty("webdriver.chrome.driver", "src/drivers/macos/chromedriver");
        }
    }
}