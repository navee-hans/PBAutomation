package PageObjects.Pages;

import Driver.Driver;
import Driver.WaitUntil;
import org.openqa.selenium.WebDriver;

public abstract class Pages extends Driver {

    protected final WaitUntil wait;

    // ✅ Pass driver to parent (Driver) and initialize WaitUntil
    public Pages(WebDriver driver) {
        super(driver);
        this.wait = new WaitUntil(driver);
    }
}
