package Hooks;

import Utils.InitializeBrowser;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;

public class PBHooks {

    private static WebDriver driver;

    @Before(order = 0)
    public void setUp(Scenario scenario) {
        System.out.println("\n========== Test Start ==========");
        System.out.println("Scenario: " + scenario.getName());
        System.out.println("================================");

        driver = InitializeBrowser.getDriver("chrome");
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            System.out.println("[WARN] Scenario failed: " + scenario.getName());
            // You can add screenshot logic here if you want
        } else {
            System.out.println("[INFO] Scenario passed: " + scenario.getName());
        }

        InitializeBrowser.quitDriver();

        System.out.println("========== Test End ==========\n");
    }

    /**
     * Optional: Getter for driver, if you want to use it in other classes directly
     */
    public static WebDriver getDriver() {
        return driver;
    }
}