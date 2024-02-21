package Utils;

import Driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;


public class InitializeBrowser {

    private static WebDriver driver;

    private InitializeBrowser(){

    }

    public static WebDriver getDriver(String browserName){
        if(driver==null && browserName.equals("Chrome"))
        {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
            driver = new ChromeDriver(BrowserHelpers.chromeOptions());
        }else if(driver==null && browserName.equals("Firefox"))
        {
            driver = new FirefoxDriver(new FirefoxOptions(BrowserHelpers.firefoxOptions()));
        }
        driver.get(XMLFileUtility.getURL());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        return driver;
    }

    public static WebDriver returnDriver(){
        return  driver;
    }

    public static void setDriverToNull(){
        driver = null;
    }
}
