package Driver;

import PageObjects.models.LocatorsType;
import io.cucumber.java.de.Wenn;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WaitUntil {
    WebDriverWait wait;
    long defaultTimeOut = 60;
    Driver driver;

    public void waitAndClickElement(LocatorsType locatorsType, String locator){
        try {
            wait = new WebDriverWait(Driver.driver, Duration.ofSeconds(defaultTimeOut));
            WebElement element = driver.FindElement(locatorsType, locator);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();

        }catch (NoSuchElementException e){
            e.printStackTrace();
        }

    }

    public void waitAndClickElement(WebElement element){
        try {
            wait = new WebDriverWait(Driver.driver, Duration.ofSeconds(defaultTimeOut));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();

        }catch (NoSuchElementException e){
            e.printStackTrace();
        }

    }

    public void waitAndSetText(LocatorsType locatorsType, String locator, String text){
        try {
            wait = new WebDriverWait(Driver.driver, Duration.ofSeconds(defaultTimeOut));
            WebElement element = driver.FindElement(locatorsType, locator);
            wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }

    public void waitAndClickWithJS(LocatorsType locatorsType, String locator, String text){
        try {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
            wait = new WebDriverWait(Driver.driver, Duration.ofSeconds(defaultTimeOut));
            WebElement element = driver.FindElement(locatorsType, locator);
            wait.until(ExpectedConditions.visibilityOf(element));
            javascriptExecutor.executeScript("arguments[0].click();", element);
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }

    public void waitAndSetTextWithJS(LocatorsType locatorsType, String locator, String text){
        try {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
            wait = new WebDriverWait(Driver.driver, Duration.ofSeconds(defaultTimeOut));
            WebElement element = driver.FindElement(locatorsType, locator);
            wait.until(ExpectedConditions.visibilityOf(element));
            javascriptExecutor.executeScript("arguments[0].value;", element);
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }

}
