package PageObjects.Pages;

import PageObjects.Locators.Locators;
import PageObjects.models.LocatorsType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScenariosPage extends Pages {

    private final String CREATE_NEW_SCENARIO_BUTTON = Locators.ScenariosPage.CreateNewScenarioButtonByID;

    public ScenariosPage(WebDriver driver) {
        super(driver);
    }

    public void clickCreateNewScenarioButton(){
        clickElement(LocatorsType.ByID,CREATE_NEW_SCENARIO_BUTTON);
    }

}
