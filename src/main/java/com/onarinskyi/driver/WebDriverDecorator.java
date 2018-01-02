package com.onarinskyi.driver;

import com.onarinskyi.core.Page;
import com.onarinskyi.environment.Timeout;
import com.onarinskyi.utils.UrlResolver;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebDriverDecorator implements WebDriver {

    private final Logger log = Logger.getLogger(WebDriverDecorator.class);
    private WebDriver driver;
    private WebDriverWait wait;
    private Timeout timeout;
    private UrlResolver urlResolver;

    WebDriverDecorator(Timeout timeout, UrlResolver urlResolver) {
        this.driver = DriverManager.getThreadLocalDriver();
        this.wait = new WebDriverWait(driver, timeout.explicitWait());
        this.timeout = timeout;
        this.urlResolver = urlResolver;
        this.driver.manage().timeouts().implicitlyWait(timeout.implicitWait(), TimeUnit.SECONDS);
    }

    @Override
    public void get(String s) {
        driver.get(s);
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }

    public void quit() {
        driver.quit();
        DriverManager.removeDriver();
    }

    public WebElement findElement(By locator) {

        log.info("Waiting for presence of element: " + locator);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            log.fatal("Element: " + locator + " was not present in DOM after: " + timeout.explicitWait() + " s");
        }
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator) {

        log.info("Waiting for presence of all gui: " + locator);
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            log.fatal("Elements: " + locator + " were not present in DOM after: " + timeout.explicitWait() + " s");
        }
        return driver.findElements(locator);
    }

    private WebElement findVisibleElement(By locator) {

        log.info("Waiting for visibility of element " + locator);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            log.fatal("Element: " + locator + " was not visible after: " + timeout.explicitWait() + " s");
        }
        return driver.findElement(locator);
    }

    private void waitFor(long millisec) {
        log.info("Waiting for " + millisec + " ms");
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickOn(By locator) {
        try {
            findVisibleElement(locator).click();
        } catch (WebDriverException e) {
            log.fatal("It was not possible to clickOn element " + locator);
        }
    }

    public void tap(String selector) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        //String js = "var event = $.Event( 'touchstart', { pageX:200, pageY:200 } );";
        executor.executeScript("var event = $.Event( 'touchstart', { pageX:200, pageY:200 } );"
                + "$('" + selector + "').trigger( event );");
    }

    public void clearField(By locator) {
        findVisibleElement(locator).clear();
    }

    public void type(By locator, String text) {
        findVisibleElement(locator).sendKeys(String.valueOf(text));
    }

    public void clearAndInputTextToField(By locator, String text) {
        clearField(locator);
        type(locator, text);
    }

    public void inputTextToInvisibleField(By locator, String text) {
        findElement(locator).sendKeys(String.valueOf(text));
    }

    public void selectDropdownOptionByValue(By locator, String value) {
        Select select = new Select(findElement(locator));
        select.selectByValue(value);
    }

    public void selectDropdownOptionByVisibleText(By locator, String text) {
        Select select = new Select(findElement(locator));
        select.selectByVisibleText(text);
    }

    public String getSelectedDropdownValue(By locator) {
        log.info("Getting selected option of: " + locator + " dropdown");

        Select select = new Select(findElement(locator));
        WebElement selectedOption = select.getFirstSelectedOption();
        return selectedOption.getText();
    }

    public void executeJavascript(String javascript) {
        ((JavascriptExecutor) driver).executeScript(javascript);
    }

    public void clickJS(By locator) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        log.info("Clicking the element via Javascript");
        executor.executeScript("arguments[0].click();", findElement(locator));
    }

    public void openPage(Page page) {
        String url = urlResolver.resolveUrlFor(page);
        log.info("Navigating to URL: " + url);
        driver.navigate().to(url);
    }

    public void refreshPage() {
        log.info("Refreshing page: " + driver.getCurrentUrl());
        driver.navigate().refresh();
    }

    public String getElementText(By locator) {
        log.info("Getting text of " + locator + " element");
        return findElement(locator).getText();
    }

    public String getAttribute(By locator, String attribute) {
        log.info("Getting " + attribute + " value of element: " + locator);
        return findElement(locator).getAttribute(attribute);
    }

    public String getCssValue(By locator, String cssKey) {
        log.info("Getting css " + cssKey + " value of " + locator + " element");
        return findElement(locator).getCssValue(cssKey);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getURLSuffix() {
        return driver.getCurrentUrl().
                replaceAll(urlResolver.getApplicationBaseUrl(), "");
    }

    public boolean isElementTextChangedTo(By locator, String text) {
        log.info("Getting asynchronous text of " + locator + " element");
        return wait.until(ExpectedConditions.textToBePresentInElementValue(findElement(locator), text));
    }

    public boolean isCheckboxChecked(By locator) {
        log.info("Getting text of " + locator + " element");
        return findElement(locator).isSelected();
    }

    public boolean isElementVisible(By locator) {
        log.info("Checking if " + locator + " is visible");
        return findVisibleElement(locator) != null;
    }

    public boolean isElementPresent(By locator) {
        log.info("Checking if " + locator + " is present");
        return findElement(locator) != null;
    }

    public boolean areSeveralElementsVisible(By locator, int expectedElementsCount) {
        boolean result;

        log.info("Checking if multiple gui:" + locator + " are visible");
        List<WebElement> elements = findElements(locator);
        result = (elements.size() == expectedElementsCount);

        for (WebElement element : elements) {
            result &= element.isDisplayed();
        }

        return result;
    }

    @Attachment(value = "PageObject screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
