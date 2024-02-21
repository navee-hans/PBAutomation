package stepDefinition;



import PageObjects.Pages.HomePage;
import PageObjects.Pages.LoginPage;
import Utils.InitializeBrowser;
import Utils.XMLFileUtility;
import Driver.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.sl.In;
import org.junit.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public class Login_Steps {
    @Given("^I login with \\\"(.*)\\\"$")
    public void given_i_login_with(String role) {
         Map<String,String> user= XMLFileUtility.getUsers(role);
         PageFactory pageFactory = new PageFactory();
        LoginPage loginPage = new LoginPage(InitializeBrowser.returnDriver());
        System.out.println(user.get("Username") + "------" +user.get("Password"));
        loginPage.loginWithValidCredentials(user.get("Username"),user.get("Password"));

    }

    @Then("I verify user navigate to homescreen")
    public void i_verify_user_navigate_to_homescreen() {
        HomePage homePage = new HomePage(InitializeBrowser.returnDriver());
        Assert.assertTrue(homePage.getLoggedUserName().isDisplayed());
    }

}
