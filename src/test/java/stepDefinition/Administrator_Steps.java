package stepDefinition;

import Hooks.PBHooks;
import PageObjects.Pages.AdministratorPage;
import PageObjects.Pages.ScenariosPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import org.junit.Assert;

import java.nio.charset.StandardCharsets;

public class Administrator_Steps {

    AdministratorPage administratorPage;
    @When("I click on {string}")
    public void i_click_on_menu(String menuText) {
        administratorPage = new AdministratorPage(PBHooks.getDriver());
        Allure.step("I click on menu item: " + menuText, () -> {
            try {
               administratorPage.menuItem(menuText).click();
            } catch (AssertionError | Exception e) {
                String message = "Menu item is not displayed: " + menuText + "\nReason: " + e.getMessage();
                Allure.addAttachment("Menu item is not displayed", "text/plain",
                        message, StandardCharsets.UTF_8.name());
                Assert.fail(message);
            }
        });
    }

    @When("I click on create new scenario button")
    public void i_click_on_create_new_scenario_button() {
        ScenariosPage scenariosPage = new ScenariosPage(PBHooks.getDriver());
        Allure.step("I click on create new scenario button ", () -> {
            try {
                scenariosPage.clickCreateNewScenarioButton();
            } catch (AssertionError | Exception e) {
                String message = "Create new scenario button" + "\nReason: " + e.getMessage();
                Allure.addAttachment("Create new scenario button is not displayed ", "text/plain",
                        message, StandardCharsets.UTF_8.name());
                Assert.fail(message);
            }
        });
    }
}
