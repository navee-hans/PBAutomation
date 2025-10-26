package PageObjects.Pages;

import Driver.Driver;
import Driver.WaitUntil;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;

public abstract class Pages extends Driver {

    protected final WaitUntil wait;

    // ✅ Pass driver to parent (Driver) and initialize WaitUntil
    public Pages(WebDriver driver) {
        super(driver);

        Allure.step("🔧 Initializing page object: " + this.getClass().getSimpleName());
        System.out.println("🔧 Initializing page object: " + this.getClass().getSimpleName());

        this.wait = new WaitUntil(driver);

        Allure.step("⏳ WaitUntil helper initialized for: " + this.getClass().getSimpleName());
        System.out.println("⏳ WaitUntil helper initialized for: " + this.getClass().getSimpleName());
    }
}
