package Driver;

import PageObjects.models.LocatorsType;
import Utils.InitializeBrowser;
import org.openqa.selenium.*;
import java.util.List;

public class Driver {

    private final WebDriver driver;

    // ✅ Constructor - makes driver instance final (no shared static)
    public Driver(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Unified private method to get By object based on LocatorsType
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

    // ✅ Find single element
    public WebElement findElement(LocatorsType locatorType, String locator) {
        try {
            return driver.findElement(getBy(locatorType, locator));
        } catch (NoSuchElementException e) {
            System.err.println("❌ Element not found: " + locator);
            throw e;
        }
    }

    // ✅ Find multiple elements
    public List<WebElement> findElements(LocatorsType locatorType, String locator) {
        return driver.findElements(getBy(locatorType, locator));
    }

    // ✅ Generic click overloads
    public void clickElement(By by) {
        driver.findElement(by).click();
    }

    public void clickElement(WebElement element) {
        if (element != null && element.isDisplayed() && element.isEnabled()) {
            element.click();
        } else {
            System.err.println("❌ Element not clickable: " + element);
        }
    }

    public void clickElement(LocatorsType locatorType, String locator) {
        clickElement(getBy(locatorType, locator));
    }

    // ✅ Enter text
    public void setText(By by, String text) {
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(text);
    }

    public void setTextWithEnterKey(By by, String text) {
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(text, Keys.ENTER);
    }

    // ✅ Element state checks
    public boolean isEnabled(By by) {
        try {
            return driver.findElement(by).isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isDisplayed(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // ✅ Get element text safely
    public String getText(By by) {
        try {
            return driver.findElement(by).getText().trim();
        } catch (NoSuchElementException e) {
            System.err.println("❌ Unable to get text: element not found - " + by);
            return "";
        }
    }

    // ✅ Browser close utilities
    public void closeCurrentBrowser() {
        driver.close();
    }

    public void closeAllBrowsers() {
        driver.quit();
    }
}
