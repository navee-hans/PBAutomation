package Driver;

import PageObjects.models.LocatorsType;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitUntil {

    private final WebDriver driver;
    private final int defaultTimeout = 20;  // ⏳ You can externalize this to config

    public WaitUntil(WebDriver driver) {
        this.driver = driver;
    }

    // 🔹 Helper to create WebDriverWait instance
    private WebDriverWait getWait(int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    // 🔹 Convert LocatorsType to By (reuse same helper logic as in Driver)
    private By getBy(LocatorsType locatorType, String locator) {
        switch (locatorType) {
            case ByXpath:
                return By.xpath(locator);
            case ByID:
                return By.id(locator);
            case ByClassName:
                return By.className(locator);
            case ByName:
                return By.name(locator);
            default:
                throw new IllegalArgumentException("Invalid locator type: " + locatorType);
        }
    }

    // ✅ Wait until element is visible
    public WebElement untilElementVisible(LocatorsType locatorType, String locator) {
        return getWait(defaultTimeout).until(
                ExpectedConditions.visibilityOfElementLocated(getBy(locatorType, locator))
        );
    }

    public WebElement untilElementVisible(By by) {
        return getWait(defaultTimeout).until(
                ExpectedConditions.visibilityOfElementLocated(by)
        );
    }

    // ✅ Wait until element is clickable
    public WebElement untilElementClickable(LocatorsType locatorType, String locator) {
        return getWait(defaultTimeout).until(
                ExpectedConditions.elementToBeClickable(getBy(locatorType, locator))
        );
    }

    public WebElement untilElementClickable(By by) {
        return getWait(defaultTimeout).until(
                ExpectedConditions.elementToBeClickable(by)
        );
    }

    // ✅ Wait until element is present in DOM (not necessarily visible)
    public WebElement untilElementPresent(LocatorsType locatorType, String locator) {
        return getWait(defaultTimeout).until(
                ExpectedConditions.presenceOfElementLocated(getBy(locatorType, locator))
        );
    }

    public WebElement untilElementPresent(By by) {
        return getWait(defaultTimeout).until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    // ✅ Wait until element is invisible or gone
    public boolean untilElementInvisible(LocatorsType locatorType, String locator) {
        return getWait(defaultTimeout).until(
                ExpectedConditions.invisibilityOfElementLocated(getBy(locatorType, locator))
        );
    }

    public boolean untilElementInvisible(By by) {
        return getWait(defaultTimeout).until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    // ✅ Wait for specific text in element
    public boolean untilTextToBePresent(LocatorsType locatorType, String locator, String text) {
        return getWait(defaultTimeout).until(
                ExpectedConditions.textToBePresentInElementLocated(getBy(locatorType, locator), text)
        );
    }

    // ✅ Optional: custom timeout
    public WebElement untilElementVisible(By by, int timeout) {
        return getWait(timeout).until(
                ExpectedConditions.visibilityOfElementLocated(by)
        );
    }

    // ✅ Optional: page title wait
    public boolean untilTitleContains(String titleText) {
        return getWait(defaultTimeout).until(
                ExpectedConditions.titleContains(titleText)
        );
    }

    // ✅ Optional: alert present
    public Alert untilAlertPresent() {
        return getWait(defaultTimeout).until(ExpectedConditions.alertIsPresent());
    }
}
