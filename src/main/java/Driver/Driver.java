package Driver;

import PageObjects.models.LocatorsType;
import Utils.AllureLogger;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Driver {

    private final WebDriver driver;

    // ✅ Constructor - makes driver instance final (no shared static)
    public Driver(WebDriver driver) {
        this.driver = driver;
        Allure.step("🚀 WebDriver initialized for current test session.");
    }

    // ✅ Unified private method to get By object based on LocatorsType
    private By getBy(LocatorsType locatorType, String locator) {
        Allure.step("🧭 Locating element using: " + locatorType + " = " + locator);
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
                String msg = "❌ Invalid locator type: " + locatorType;
                Allure.addAttachment("Locator Error", "text/plain", msg);
                throw new IllegalArgumentException(msg);
        }
    }

    // ✅ Find single element
    public WebElement findElement(LocatorsType locatorType, String locator) {
        try {
            WebElement element = driver.findElement(getBy(locatorType, locator));
            Allure.step("✅ Element found: " + locator);
            return element;
        } catch (NoSuchElementException e) {
            String msg = "❌ Element not found: " + locator;
            AllureLogger.failStep(msg,e);
            Allure.addAttachment("Element Not Found", "text/plain", e.getMessage(), StandardCharsets.UTF_8.name());
            throw e;
        }
    }

    // ✅ Find multiple elements
    public List<WebElement> findElements(LocatorsType locatorType, String locator) {
        Allure.step("🔍 Finding multiple elements by " + locatorType + ": " + locator);
        return driver.findElements(getBy(locatorType, locator));
    }

    // ✅ Generic click overloads
    public void clickElement(By by) {
        Allure.step("🖱️ Clicking element by: " + by);
        try {
            driver.findElement(by).click();
            Allure.step("✅ Click action successful.");
        } catch (Exception e) {
            AllureLogger.failStep("❌ Failed to click element: " + by , e);
            Allure.addAttachment("Click Failure", "text/plain", e.getMessage());
            throw e;
        }
    }

    public void clickElement(WebElement element) {
        try {
            if (element != null && element.isDisplayed() && element.isEnabled()) {
                Allure.step("🖱️ Clicking WebElement: " + element.toString());
                element.click();
                Allure.step("✅ Click successful on element.");
            } else {
                String msg = "❌ Element not clickable: " + element;
                Allure.step(msg);
                Allure.addAttachment("Not Clickable", "text/plain", msg);
            }
        } catch (Exception e) {
            AllureLogger.failStep("❌ Exception while clicking element.", e);
            Allure.addAttachment("Click Exception", "text/plain", e.getMessage());
            throw e;
        }
    }

    public void clickElement(LocatorsType locatorType, String locator) {
        Allure.step("🖱️ Clicking element using locator: " + locatorType + " -> " + locator);
        clickElement(getBy(locatorType, locator));
    }

    // ✅ Enter text
    public void setText(By by, String text) {
        try {
            Allure.step("⌨️ Setting text: '" + text + "' in element: " + by);
            WebElement element = driver.findElement(by);
            element.clear();
            element.sendKeys(text);
            Allure.step("✅ Text entered successfully.");
        } catch (Exception e) {
            AllureLogger.failStep("❌ Failed to set text in element: " + by, e);
            Allure.addAttachment("SetText Exception", "text/plain", e.getMessage());
            throw e;
        }
    }

    public void setTextWithEnterKey(By by, String text) {
        try {
            Allure.step("⌨️ Setting text with Enter: '" + text + "' in element: " + by);
            WebElement element = driver.findElement(by);
            element.clear();
            element.sendKeys(text, Keys.ENTER);
            Allure.step("✅ Text entered and Enter pressed.");
        } catch (Exception e) {
            Allure.step("❌ Failed to set text with Enter in element: " + by);
            Allure.addAttachment("SetTextWithEnter Exception", "text/plain", e.getMessage());
            throw e;
        }
    }

    // ✅ Element state checks
    public boolean isEnabled(By by) {
        try {
            boolean enabled = driver.findElement(by).isEnabled();
            Allure.step("🔘 Element enabled state for " + by + ": " + enabled);
            return enabled;
        } catch (NoSuchElementException e) {
            AllureLogger.failStep("⚠️ Element not found while checking isEnabled: " + by, e);
            throw e;
        }
    }

    public boolean isDisplayed(By by) {
        try {
            boolean displayed = driver.findElement(by).isDisplayed();
            Allure.step("👁️ Element displayed state for " + by + ": " + displayed);
            return displayed;
        } catch (NoSuchElementException e) {
            AllureLogger.failStep("⚠️ Element not found while checking isDisplayed: " + by ,e);
            throw e;
        }
    }

    // ✅ Get element text safely
    public String getText(By by) {
        try {
            String text = driver.findElement(by).getText().trim();
            Allure.step("📝 Text retrieved from element " + by + ": " + text);
            return text;
        } catch (NoSuchElementException e) {
            String msg = "❌ Unable to get text: element not found - " + by;
            AllureLogger.failStep(msg, e);
            Allure.addAttachment("GetText Failure", "text/plain", msg);
            throw e;
        }
    }

    // ✅ Browser close utilities
    public void closeCurrentBrowser() {
        Allure.step("❎ Closing current browser window.");
        driver.close();
    }

    public void closeAllBrowsers() {
        Allure.step("🧹 Closing all browser windows and quitting WebDriver session.");
        driver.quit();
    }
}
