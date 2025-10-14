package stepDefinition;

import PageObjects.Pages.DashboardPage;
import PageObjects.Pages.Pages;
import PageObjects.Pages.LoginPage;
import Utils.InitializeBrowser;

public class PageFactory {
    public Pages pages(PageObjects.models.Pages pages){
        if(pages.compareTo(PageObjects.models.Pages.LoginPage)==0){
            return new LoginPage(InitializeBrowser.returnDriver());
        }
        if(pages.compareTo(PageObjects.models.Pages.HomePage)==0){
            return new DashboardPage(InitializeBrowser.returnDriver());
        }
        return null;
    }
}
