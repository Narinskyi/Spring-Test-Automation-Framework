package com.onarinskyi.driver;

import com.onarinskyi.environment.Browser;
import com.onarinskyi.environment.OperatingSystem;
import com.onarinskyi.environment.Timeout;
import com.onarinskyi.environment.UrlResolver;
import com.onarinskyi.exceptions.UnknownBrowserException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.File;
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

    @Value("${version}")
    private String version;

    @Value("${browser.name}")
    private String browserName;

    @Value("${platform}")
    private String platform;

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
                System.setProperty("webdriver.gecko.driver", "src/drivers/macos/geckodriver");
                break;
            case WINDOWS:
                System.setProperty("webdriver.chrome.driver", "src/drivers/windows/chromedriver.exe");
                System.setProperty("webdriver.ie.driver", "src/drivers/windows/IEDriverServer.exe");
                System.setProperty("webdriver.edge.driver", "src/drivers/windows/MicrosoftWebDriver.exe");
                System.setProperty("webdriver.gecko.driver", "src/drivers/windows/geckodriver.exe");
                System.setProperty("phantomjs.binary.path", "src/drivers/windows/phantomjs.exe");
                break;
        }
    }

    private WebDriver getLocalDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                return new ChromeDriver(chromeCapabilities(false));
            case IE:
                return new InternetExplorerDriver(ieCapabilities());
            case EDGE:
                return new EdgeDriver(edgeCapabilities());
            case FIREFOX:
                return new FirefoxDriver(firefoxCapabilities());
            case HEADLESS:
                return new ChromeDriver(chromeCapabilities(true));
            case MOBILE_EMULATOR_CHROME:
                return new ChromeDriver(chromeMobileCapabilities(deviceName));
            case TABLET_EMULATOR_CHROME:
                return new ChromeDriver(chromeTabletCapabilities());
            default:
                throw new UnknownBrowserException(browser + "is not configured in the framework");
        }
    }

    private WebDriver getRemoteDriver(Browser browser, URL hubHost) {
        switch (browser) {
            case CHROME:
                return new RemoteWebDriver(hubHost, chromeCapabilities(false));
            case IE:
                return new RemoteWebDriver(hubHost, ieCapabilities());
            case EDGE:
                return new RemoteWebDriver(hubHost, edgeCapabilities());
            case FIREFOX:
                return new RemoteWebDriver(hubHost, firefoxCapabilities());
            case HEADLESS:
                return new RemoteWebDriver(hubHost, chromeCapabilities(true));
            case MOBILE_EMULATOR_CHROME:
                return new RemoteWebDriver(hubHost, chromeMobileCapabilities(deviceName));
            case TABLET_EMULATOR_CHROME:
                return new RemoteWebDriver(hubHost, chromeTabletCapabilities());
            default:
                throw new UnknownBrowserException(browser + "is not configured in the framework");
        }
    }

    private DesiredCapabilities chromeCapabilities(boolean isHeadless) {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();

        desiredCapabilities.setVersion(version);
        desiredCapabilities.setBrowserName(browserName);
        desiredCapabilities.setPlatform(Platform.fromString(platform));

        HashMap<String, Object> preferences = new HashMap<>();

        String downloadDirectory = System.getProperty("user.dir") + File.separator + "src";
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("download.default_directory", downloadDirectory);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", preferences);

        if (isHeadless) {
            chromeOptions.addArguments("headless");
        }

        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return desiredCapabilities;
    }

    private DesiredCapabilities ieCapabilities() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();

        desiredCapabilities.setVersion(version);
        desiredCapabilities.setBrowserName(browserName);
        desiredCapabilities.setPlatform(Platform.fromString(platform));

        desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        desiredCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        desiredCapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
        desiredCapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        return desiredCapabilities;
    }

    private DesiredCapabilities edgeCapabilities() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.edge();
        return desiredCapabilities;
    }

    private DesiredCapabilities firefoxCapabilities() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
        FirefoxProfile profile = new FirefoxProfile();
        desiredCapabilities.setCapability(FirefoxDriver.PROFILE, profile);
        return desiredCapabilities;
    }

    private DesiredCapabilities chromeMobileCapabilities(String deviceName) {
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);

        Map<String, Object> chromeOptions = new HashMap<>();
        chromeOptions.put("mobileEmulation", mobileEmulation);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return capabilities;
    }

    private DesiredCapabilities chromeTabletCapabilities() {
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
