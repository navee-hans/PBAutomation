package Driver;

import PageObjects.models.LocatorsType;
import Utils.AllureLogger;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUntil {

    private final WebDriver driver;
    private final int defaultTimeout = 20;  // seconds

    public WaitUntil(WebDriver driver) {
        this.driver = driver;
    }

    private WebDriverWait getWait(int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    private By getBy(LocatorsType locatorType, String locator) {
        switch (locatorType) {
            case ByXpath: return By.xpath(locator);
            case ByID: return By.id(locator);
            case ByClassName: return By.className(locator);
            case ByName: return By.name(locator);
            default: throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
    }

    // ✅ Wait until element is visible
    public WebElement untilElementVisible(LocatorsType locatorType, String locator) {
        return Allure.step("⏳ Waiting for element to be visible: [" + locatorType + "] " + locator, () -> {
            try {
                WebElement element = getWait(defaultTimeout).until(
                        ExpectedConditions.visibilityOfElementLocated(getBy(locatorType, locator))
                );
                AllureLogger.passStep("Element visible: " + locator);
                return element;
            } catch (TimeoutException | NoSuchElementException e) {
                AllureLogger.failStep("Element not visible: " + locator, e);
                throw e; // don’t throw, return null so caller can handle gracefully
            }
        });
    }

    // ✅ Wait until element is clickable
    public WebElement untilElementClickable(LocatorsType locatorType, String locator) {
        return Allure.step("⏳ Waiting for element to be clickable: [" + locatorType + "] " + locator, () -> {
            try {
                WebElement element = getWait(defaultTimeout).until(
                        ExpectedConditions.elementToBeClickable(getBy(locatorType, locator))
                );
                AllureLogger.passStep("Element clickable: " + locator);
                return element;
            } catch (TimeoutException | NoSuchElementException e) {
                AllureLogger.failStep("Element not clickable: " + locator, e);
                throw e;
            }
        });
    }

    // ✅ Wait until element is present in DOM
    public WebElement untilElementPresent(LocatorsType locatorType, String locator) {
        return Allure.step("⏳ Waiting for element to be present: [" + locatorType + "] " + locator, () -> {
            try {
                WebElement element = getWait(defaultTimeout).until(
                        ExpectedConditions.presenceOfElementLocated(getBy(locatorType, locator))
                );
                AllureLogger.passStep("Element present in DOM: " + locator);
                return element;
            } catch (TimeoutException | NoSuchElementException e) {
                AllureLogger.failStep("Element not present in DOM: " + locator, e);
                throw e;
            }
        });
    }
}