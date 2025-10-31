package Hooks;

import Utils.InitializeBrowser;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;

public class PBHooks {

    private static WebDriver driver;
    InitializeBrowser browser;

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("\n🚀 Starting Scenario: " + scenario.getName());
        browser = new InitializeBrowser();
        driver = browser.getDriver("chrome");
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                System.err.println("❌ Scenario failed: " + scenario.getName());
            } else {
                System.out.println("✅ Scenario passed: " + scenario.getName());
            }
        } finally {
            if (driver != null) {
                browser.quitDriver();  // close browser for this dataset
                System.out.println("🧹 Browser closed after scenario");
            }
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
