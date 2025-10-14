package stepDefinition;



import PageObjects.Pages.DashboardPage;
import PageObjects.Pages.LoginPage;
import Utils.InitializeBrowser;
import Utils.XMLFileUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.Map;

public class Login_Steps {

    @Given("I login with {string}")
    public void iLoginWith(String role) {
        Map<String,String> user= XMLFileUtility.getUsers(role);
        PageFactory pageFactory = new PageFactory();
        LoginPage loginPage = new LoginPage(InitializeBrowser.returnDriver());
        System.out.println(user.get("Username"));
        loginPage.loginWithValidCredentials(user.get("Username"),user.get("Password"));
    }

    @Then("I verify user navigate to {string} Dashboard")
    public void i_verify_user_navigate_to_homescreen(String role) {
        DashboardPage dashBoardPage = new DashboardPage(InitializeBrowser.returnDriver());
        if(role.equalsIgnoreCase("Guest"))
            Assert.assertFalse(dashBoardPage.isSkipButtonDisplayed());
        if(role.equalsIgnoreCase("Admin"))
            Assert.assertTrue(dashBoardPage.isSkipButtonDisplayed());
    }
}
