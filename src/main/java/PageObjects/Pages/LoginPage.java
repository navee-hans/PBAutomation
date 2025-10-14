package PageObjects.Pages;

import PageObjects.Locators.Locators;
import PageObjects.models.LocatorsType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Pages {

    // ✅ Define locators (not elements)
    private final LocatorsType EMAIL_TYPE = LocatorsType.ByID;
    private final String EMAIL_LOCATOR = Locators.LoginPage.EmailByID;

    private final LocatorsType PASSWORD_TYPE = LocatorsType.ByID;
    private final String PASSWORD_LOCATOR = Locators.LoginPage.PasswordByID;

    private final LocatorsType SIGNIN_TYPE = LocatorsType.ByID;
    private final String SIGNIN_LOCATOR = Locators.LoginPage.SignInByID;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ✅ Main method to perform login
    public void loginWithValidCredentials(String email, String password) {
        try {
            System.out.println("🔹 Attempting login with user: " + email);

            // Wait until elements visible and interact
            WebElement emailField = wait.untilElementVisible(EMAIL_TYPE, EMAIL_LOCATOR);
            emailField.clear();
            emailField.sendKeys(email);

            WebElement passwordField = wait.untilElementVisible(PASSWORD_TYPE, PASSWORD_LOCATOR);
            passwordField.clear();
            passwordField.sendKeys(password);

            WebElement signInBtn = wait.untilElementClickable(SIGNIN_TYPE, SIGNIN_LOCATOR);
            signInBtn.click();

            System.out.println("✅ Login submitted successfully");

        } catch (Exception e) {
            System.err.println("❌ Login failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
