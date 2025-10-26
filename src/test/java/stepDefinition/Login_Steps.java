package stepDefinition;

import PageObjects.Pages.DashboardPage;
import PageObjects.Pages.LoginPage;
import Utils.InitializeBrowser;
import Utils.XMLFileUtility;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;
import org.junit.Assert;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Login_Steps {

    @Given("I login with {string}")
    public void iLoginWith(String role) {
        Map<String, String> user = XMLFileUtility.getUsers(role);
        LoginPage loginPage = new LoginPage(InitializeBrowser.returnDriver());

        String username = user.get("Username");
        String password = user.get("Password");

        Allure.step("Logging in with Username: " + username, () -> {
            try {
                loginPage.loginWithValidCredentials(username, password);
            } catch (Exception e) {
                // ❌ Attach failure details to Allure
                String message = "❌ Login failed for user: " + username + "\nReason: " + e.getMessage();
                Allure.addAttachment("Login Failure Details", "text/plain",
                        message, StandardCharsets.UTF_8.name());
                System.err.println(message);

                // ❌ Fail step in Allure + Cucumber
                Assert.fail(message);
            }
        });
    }

    @Then("I verify user navigate to {string} Dashboard")
    public void i_verify_user_navigate_to_homescreen(String role) {
        DashboardPage dashBoardPage = new DashboardPage(InitializeBrowser.returnDriver());

        Allure.step("Verifying dashboard for role: " + role, () -> {
            try {
                if (role.equalsIgnoreCase("Guest")) {
                    Assert.assertFalse("Guest user should NOT see skip button",
                            dashBoardPage.isSkipButtonDisplayed());
                } else if (role.equalsIgnoreCase("Admin")) {
                    Assert.assertTrue("Admin user should see skip button",
                            dashBoardPage.isSkipButtonDisplayed());
                }
            } catch (AssertionError | Exception e) {
                // ❌ Attach error to Allure report
                String message = "❌ Dashboard verification failed for role: " + role + "\nReason: " + e.getMessage();
                Allure.addAttachment("Dashboard Verification Error", "text/plain",
                        message, StandardCharsets.UTF_8.name());
                System.err.println(message);

                // ❌ Fail step
                Assert.fail(message);
            }
        });
    }
}