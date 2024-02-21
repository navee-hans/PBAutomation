package stepDefinition;

import PageObjects.Pages.Pages;
import PageObjects.Pages.HomePage;
import PageObjects.Pages.LoginPage;
import Driver.Driver;
import Utils.InitializeBrowser;

public class PageFactory {
    public Pages pages(PageObjects.models.Pages pages){
        if(pages.compareTo(PageObjects.models.Pages.LoginPage)==0){
            return new LoginPage(InitializeBrowser.returnDriver());
        }
        if(pages.compareTo(PageObjects.models.Pages.HomePage)==0){
            return new HomePage(InitializeBrowser.returnDriver());
        }
        return null;
    }
}
