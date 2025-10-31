package Utils;

import Driver.Driver;
import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class InitializeBrowser {

    private WebDriver driver;

    /**
     * Initialize WebDriver instance based on the browser name.
     *
     * @param browserName the name of the browser (e.g., "Chrome" or "Firefox")
     * @return WebDriver instance
     */
    public WebDriver getDriver(String browserName) {
        try {
            if (driver == null) {
                Allure.step("🚀 Launching browser: " + browserName);
                System.out.println("🚀 Launching browser: " + browserName);

                switch (browserName.toLowerCase()) {
                    case "chrome":
                        driver = new ChromeDriver(BrowserHelpers.chromeOptions());
                        Allure.step("✅ ChromeDriver initialized successfully");
                        System.out.println("✅ ChromeDriver initialized successfully");
                        break;

                    case "firefox":
                        FirefoxOptions options = BrowserHelpers.firefoxOptions();
                        driver = new FirefoxDriver(options);
                        Allure.step("✅ FirefoxDriver initialized successfully");
                        System.out.println("✅ FirefoxDriver initialized successfully");
                        break;

                    default:
                        Allure.step("❌ Unsupported browser: " + browserName);
                        throw new IllegalArgumentException("Unsupported browser: " + browserName);
                }

                // 🔧 Basic setup
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                driver.manage().window().maximize();

                String appURL = XMLFileUtility.getURL();
                driver.get(appURL);

                Allure.step("🌐 Navigated to URL: " + appURL);
                System.out.println("🌐 Navigated to URL: " + appURL);
            } else {
                Allure.step("♻️ Reusing existing WebDriver instance");
                System.out.println("♻️ Reusing existing WebDriver instance");
            }
        } catch (Exception e) {
            Allure.step("❌ Failed to initialize browser: " + e.getMessage());
            System.err.println("❌ Failed to initialize browser: " + e.getMessage());
            e.printStackTrace();
        }
        return driver;
    }

    /**
     * Return existing WebDriver instance.
     */
    public WebDriver returnDriver() {
        if (driver == null) {
            Allure.step("⚠️ Attempted to access driver before initialization");
            throw new IllegalStateException("Driver not initialized. Call getDriver() first.");
        }
        return driver;
    }

    /**
     * Cleanly close and quit the WebDriver instance.
     */
    public void quitDriver() {
        try {
            if (driver != null) {
                Allure.step("🧹 Closing browser and quitting WebDriver...");
                System.out.println("🧹 Closing browser and quitting WebDriver...");
                driver.quit();
                driver = null;
                Allure.step("✅ WebDriver closed successfully");
                System.out.println("✅ WebDriver closed successfully");
            }
        } catch (Exception e) {
            Allure.step("❌ Error while quitting WebDriver: " + e.getMessage());
            System.err.println("❌ Error while quitting WebDriver: " + e.getMessage());
        }
    }
}