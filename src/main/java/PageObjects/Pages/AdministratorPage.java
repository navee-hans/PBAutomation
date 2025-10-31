package PageObjects.Pages;

import PageObjects.Locators.Locators;
import PageObjects.models.LocatorsType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdministratorPage extends Pages {

    public AdministratorPage(WebDriver driver) {
        super(driver);
    }

    public WebElement menuItem(String linkName){
            WebElement element =  findElement(LocatorsType.ByXpath, Locators.AdministratorPage.menuLinkXpath(linkName));
            try {
             return element;
            }catch (Exception e){
              e.printStackTrace();
            }
            return element;
    }

}
