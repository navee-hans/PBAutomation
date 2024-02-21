package PageObjects.Pages;
import Driver.Driver;
import Driver.WaitUntil;
import org.openqa.selenium.WebDriver;


public abstract class Pages extends Driver {
    WaitUntil wait = new WaitUntil();
    public Pages(WebDriver driver){
        this.driver = driver;
    }

    protected Pages() {
    }
}
