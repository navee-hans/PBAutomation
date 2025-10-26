package PageObjects.Pages;

import PageObjects.Locators.Locators;
import PageObjects.models.LocatorsType;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends Pages {

    private final LocatorsType skipButtonLocatorType = LocatorsType.ByXpath;
    private final String skipButtonLocatorValue = Locators.DashboardPage.SkipButtonByXPATH;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // ✅ Verify if Skip button is displayed
    public boolean isSkipButtonDisplayed() {
        Allure.step("Verifying if Skip button is displayed on Dashboard");
        try {
            WebElement skipButton = wait.untilElementClickable(skipButtonLocatorType, skipButtonLocatorValue);
            boolean displayed = skipButton.isDisplayed();
            Allure.step("Skip button display status: " + displayed);
            return displayed;
        } catch (Exception e) {
            Allure.step("❌ Skip button not found or not visible: " + e.getMessage());
            System.err.println("Skip button not found or not visible: " + e.getMessage());
            return false;
        }
    }

    // ✅ Click Skip button safely
    public void clickSkipButton() {
        Allure.step("Attempting to click Skip button on Dashboard");
        try {
            WebElement skipButton = wait.untilElementClickable(skipButtonLocatorType, skipButtonLocatorValue);
            skipButton.click();
            Allure.step("✅ Clicked Skip button successfully.");
            System.out.println("Clicked Skip button successfully.");
        } catch (Exception e) {
            Allure.step("❌ Failed to click Skip button: " + e.getMessage());
            System.err.println("Failed to click Skip button: " + e.getMessage());
        }
    }
}