package com.onarinskyi.driver;

import com.onarinskyi.environment.Browser;
import com.onarinskyi.environment.OperatingSystem;
import com.onarinskyi.environment.Timeout;
import com.onarinskyi.environment.UrlResolver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Component
@PropertySource("classpath:driver.properties")
public class WebDriverFactory {

    @Value("${use.grid}")
    private Boolean useGrid;

    @Value("${grid.hub}")
    private URL hubHostUrl;

    @Value("${exception.fail}")
    private Boolean failOnException;

    @Value("${device.name}")
    private String deviceName;

    @Autowired
    private Timeout timeout;

    @Autowired
    private UrlResolver urlResolver;

    @Autowired
    private OperatingSystem operatingSystem;

    @Autowired
    private Browser browser;

    public WebDriverDecorator getInitialDriver() {
        initDriversFor(operatingSystem);

        WebDriver driver = useGrid ? getRemoteDriver(browser, hubHostUrl) :
                getLocalDriver(browser);

        return new WebDriverDecorator(driver, timeout, urlResolver, failOnException);
    }

    private void initDriversFor(OperatingSystem operatingSystem) {
        switch (operatingSystem) {
            case MACOS:
                System.setProperty("webdriver.chrome.driver", "src/drivers/macos/chromedriver");
                break;
            case WINDOWS:
                System.setProperty("webdriver.chrome.driver", "src/drivers/windows/chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", "src/drivers/windows/geckodriver.exe");
                System.setProperty("webdriver.chrome.driver", "src/drivers/windows/phantomjs.exe");
                break;
        }
    }

    private WebDriver getLocalDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                return new ChromeDriver();
            case FIREFOX:
                return new FirefoxDriver(getFirefoxCapabilities());
            case MOBILE_EMULATOR_CHROME:
                return new ChromeDriver(getChromeMobileCapabilities(deviceName));
            case TABLET_EMULATOR_CHROME:
                return new ChromeDriver(getChromeTabletCapabilities());
            default:
                return new ChromeDriver();
        }
    }

    private WebDriver getRemoteDriver(Browser browser, URL hubHost) {
        switch (browser) {
            case CHROME:
                return new RemoteWebDriver(hubHost, DesiredCapabilities.chrome());
            case FIREFOX:
                return new RemoteWebDriver(hubHost, getFirefoxCapabilities());
            case MOBILE_EMULATOR_CHROME:
                return new RemoteWebDriver(hubHost, getChromeMobileCapabilities(deviceName));
            case TABLET_EMULATOR_CHROME:
                return new RemoteWebDriver(hubHost, getChromeTabletCapabilities());
            default:
                return new RemoteWebDriver(hubHost, DesiredCapabilities.chrome());
        }
    }

    private DesiredCapabilities getFirefoxCapabilities() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        FirefoxProfile profile = new FirefoxProfile();
        desiredCapabilities.setCapability(FirefoxDriver.PROFILE, profile);
        return desiredCapabilities;
    }

    private DesiredCapabilities getChromeMobileCapabilities(String deviceName) {
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);

        Map<String, Object> chromeOptions = new HashMap<>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return capabilities;
    }

    private DesiredCapabilities getChromeTabletCapabilities() {
        String userAgent = "Mozilla/5.0 (iPad; U; CPU OS 3_2 like Mac OS X; en-us) AppleWebKit/531.21.10 " +
                "(KHTML, like Gecko) Version/4.0.4 Mobile/7B334b Safari/531.21.10";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--user-agent=" + userAgent);
        options.addArguments("window-size=1039,859");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        return capabilities;
    }
}
