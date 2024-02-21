package PageObjects.Pages;

import PageObjects.Locators.Locators;
import PageObjects.models.LocatorsType;
import Driver.Driver;
import Utils.InitializeBrowser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends Pages {

    private WebElement UserName = FindElement(LocatorsType.ByID, Locators.LoginPage.UserNameID);
    private WebElement Password = FindElement(LocatorsType.ByID, Locators.LoginPage.PasswordID);
    private WebElement LoginButton = FindElement(LocatorsType.ByID, Locators.LoginPage.LoginButtonID);


    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void loginWithValidCredentials(String username,String password) {
        try {
            UserName.sendKeys(username);
            Password.sendKeys(password);
            wait.waitAndClickElement(LoginButton);
            Thread.sleep(20);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
