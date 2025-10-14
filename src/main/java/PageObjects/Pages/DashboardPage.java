package PageObjects.Pages;

import PageObjects.Locators.Locators;
import PageObjects.models.LocatorsType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends Pages {

    // Use the correct locator for Skip button — assuming it's in Locators.DashboardPage
    private final LocatorsType skipButtonLocatorType = LocatorsType.ByXpath;
    private final String skipButtonLocatorValue = Locators.DashboardPage.SkipButtonByXPATH;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // Method to verify if Skip button is displayed
    public boolean isSkipButtonDisplayed() {
        try {
            WebElement skipButton = wait.untilElementClickable(skipButtonLocatorType, skipButtonLocatorValue);
            return skipButton.isDisplayed();
        } catch (Exception e) {
            System.out.println("Skip button not found or not visible: " + e.getMessage());
            return false;
        }
    }

    // Optional method to click Skip button safely
    public void clickSkipButton() {
        try {
            WebElement skipButton = wait.untilElementClickable(skipButtonLocatorType, skipButtonLocatorValue);
            skipButton.click();
            System.out.println("Clicked Skip button successfully.");
        } catch (Exception e) {
            System.err.println("Failed to click Skip button: " + e.getMessage());
        }
    }
}
