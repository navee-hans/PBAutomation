package Hooks;

import Utils.InitializeBrowser;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

/**
 * Global hooks for setup, teardown, and fail-fast logic.
 */
public class PBHooks {

    private static WebDriver driver;
    private static boolean hasPreviousFailure = false; // ⚙️ Track failure across scenarios

    @Before(order = 0)
    public void setUp(Scenario scenario) {
        if (hasPreviousFailure) {
            System.out.println("\n⚠️ Skipping scenario due to previous failure: " + scenario.getName());
            Allure.step("⚠️ Skipping scenario due to previous failure: " + scenario.getName());
            // Mark scenario as skipped intentionally
            throw new io.cucumber.java.PendingException("Skipped because previous scenario failed");
        }

        System.out.println("\n========== Test Start ==========");
        System.out.println("Scenario: " + scenario.getName());
        System.out.println("================================");

        driver = InitializeBrowser.getDriver("chrome");
    }

    @After(order = 0)
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                hasPreviousFailure = true; // ⚙️ Remember failure for next scenarios
                Allure.step("❌ Scenario failed: " + scenario.getName());
                attachScreenshot(driver, "Failed Step Screenshot");
                System.err.println("❌ Scenario failed: " + scenario.getName());
            } else {
                Allure.step("✅ Scenario passed: " + scenario.getName());
                System.out.println("✅ Scenario passed: " + scenario.getName());
            }
        } catch (Exception e) {
            Allure.step("⚠️ Error during teardown: " + e.getMessage());
            System.err.println("⚠️ Error during teardown: " + e.getMessage());
        } finally {
            InitializeBrowser.quitDriver();
        }
    }

    public static void attachScreenshot(WebDriver driver, String name) {
        try {
            if (driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
                System.out.println("📸 Screenshot attached: " + name);
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to capture screenshot: " + e.getMessage());
        }
    }
}