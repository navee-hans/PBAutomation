package PageObjects.Pages;

import PageObjects.Locators.Locators;
import PageObjects.models.LocatorsType;
import Utils.AllureLogger;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Pages {

    private final LocatorsType EMAIL_TYPE = LocatorsType.ByID;
    private final String EMAIL_LOCATOR = Locators.LoginPage.EmailByID;

    private final LocatorsType PASSWORD_TYPE = LocatorsType.ByID;
    private final String PASSWORD_LOCATOR = Locators.LoginPage.PasswordByID;

    private final LocatorsType SIGNIN_TYPE = LocatorsType.ByID;
    private final String SIGNIN_LOCATOR = Locators.LoginPage.SignInByID;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginWithValidCredentials(String email, String password) {
        Allure.step("🔹 Attempting login with user: " + email);

        WebElement emailField = wait.untilElementVisible(EMAIL_TYPE, EMAIL_LOCATOR);
        if (emailField == null) {
            AllureLogger.failStep("Email field not found", new Exception("Email field is null"));
            return;
        }
        emailField.clear();
        emailField.sendKeys(email);
        AllureLogger.passStep("Entered email successfully");

        WebElement passwordField = wait.untilElementVisible(PASSWORD_TYPE, PASSWORD_LOCATOR);
        if (passwordField == null) {
            AllureLogger.failStep("Password field not found", new Exception("Password field is null"));
            return;
        }
        passwordField.clear();
        passwordField.sendKeys(password);
        AllureLogger.passStep("Entered password successfully");

        WebElement signInBtn = wait.untilElementClickable(SIGNIN_TYPE, SIGNIN_LOCATOR);
        if (signInBtn == null) {
            AllureLogger.failStep("Sign In button not found", new Exception("Sign In button is null"));
            return;
        }
        signInBtn.click();
        AllureLogger.passStep("Clicked Sign In successfully");

        AllureLogger.passStep("✅ Login process completed successfully");
    }
}