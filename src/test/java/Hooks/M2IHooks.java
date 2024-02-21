package Hooks;

import Driver.Driver;
import Utils.InitializeBrowser;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class M2IHooks {
    private static WebDriver driver;

    @Before
    public void beforeTest(){
        driver = InitializeBrowser.getDriver("Chrome");
    }

    @After
    public void afterTest(){
        if(driver!=null) {
            driver.close();
            driver.quit();
            InitializeBrowser.setDriverToNull();
        }
        //driver = null;
    }

//    @BeforeAll
//    public static void beforeSuite(){
//
//    }
//
//    @AfterAll
//    public static void afterSuite(){
//
//    }
//
//    @BeforeStep
//    public void beforeStep(){
//
//    }
//
//    @AfterStep
//    public void afterStep(){
//
//    }
//
//    @BeforeClass
//    public void beforeClass(){
//
//    }
//
//
//    @AfterClass
//    public void afterClass(){
//
//    }

}
