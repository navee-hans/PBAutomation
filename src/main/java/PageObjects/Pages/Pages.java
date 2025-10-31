package PageObjects.Pages;

import Driver.Driver;
import Driver.WaitUntil;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;

public class Pages extends Driver {
    protected final WaitUntil wait;

    public Pages(WebDriver driver) {
        super(driver);

        Allure.step("🔧 Initializing page object: " + this.getClass().getSimpleName());
        System.out.println("🔧 Initializing page object: " + this.getClass().getSimpleName());

        this.wait = new WaitUntil(driver);
    }
}