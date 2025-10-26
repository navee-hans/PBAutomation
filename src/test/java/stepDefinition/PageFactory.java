package stepDefinition;

import PageObjects.Pages.DashboardPage;
import PageObjects.Pages.LoginPage;
import PageObjects.Pages.Pages;
import Utils.InitializeBrowser;
import io.qameta.allure.Allure;

public class PageFactory {

    public Pages pages(PageObjects.models.Pages pageType) {
        Allure.step("🔧 Initializing page object for: " + pageType.name());

        try {
            if (pageType.compareTo(PageObjects.models.Pages.LoginPage) == 0) {
                Allure.step("➡ Creating LoginPage instance");
                return new LoginPage(InitializeBrowser.returnDriver());
            }

            if (pageType.compareTo(PageObjects.models.Pages.DashboardPage) == 0) {
                Allure.step("➡ Creating DashboardPage instance");
                return new DashboardPage(InitializeBrowser.returnDriver());
            }

            Allure.step("⚠ Unknown page type: " + pageType.name());
            return null;

        } catch (Exception e) {
            Allure.step("❌ Failed to initialize page: " + pageType.name());
            Allure.addAttachment("PageFactory Exception", "text/plain", e.getMessage());
            throw e;
        }
    }
}
