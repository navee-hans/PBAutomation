package PageObjects.Pages;


import PageObjects.Locators.Locators;
import PageObjects.models.LocatorsType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends Pages {

    private WebElement LoggedUserName = FindElement(LocatorsType.ByXpath, Locators.HomePage.LoggedUserNameXpath);

    public HomePage(WebDriver driver){
        super(driver);
    }

    public WebElement getLoggedUserName(){
        return LoggedUserName;
    }

}
