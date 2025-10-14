package Utils;

import Driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class InitializeBrowser {

    private static WebDriver driver;

    // Private constructor to prevent instantiation
    private InitializeBrowser() {
    }

    /**
     * Initialize WebDriver instance based on the browser name.
     *
     * @param browserName the name of the browser (e.g., "Chrome" or "Firefox")
     * @return WebDriver instance
     */
    public static WebDriver getDriver(String browserName) {
        if (driver == null) {
            switch (browserName.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver(BrowserHelpers.chromeOptions());
                    break;

                case "firefox":
                    FirefoxOptions options = BrowserHelpers.firefoxOptions();
                    driver = new FirefoxDriver(options);
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browserName);
            }

            // Basic browser setup
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
            driver.manage().window().maximize();
            driver.get(XMLFileUtility.getURL());
        }
        return driver;
    }

    /**
     * Return existing WebDriver instance.
     */
    public static WebDriver returnDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Call getDriver() first.");
        }
        return driver;
    }

    /**
     * Cleanly close and quit the WebDriver instance.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}