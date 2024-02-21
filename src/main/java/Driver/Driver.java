
package Driver;
import PageObjects.models.LocatorsType;
import Utils.InitializeBrowser;
import org.openqa.selenium.*;
import java.util.List;

public class Driver {
    public static WebDriver driver = null;

    public WebElement FindElement(LocatorsType locatorType, String locator){
        WebElement element = null;
        try {
            if (locatorType == LocatorsType.ByXpath) {
                element =  driver.findElement(By.xpath(locator));
            }else if(locatorType == LocatorsType.ByID)
            {
                element = driver.findElement(By.id(locator));
            }else if(locatorType == LocatorsType.ByClassName)
            {
                element = driver.findElement(By.className(locator));
            }else if(locatorType == LocatorsType.ByName) {
                element = driver.findElement(By.name(locator));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }

    public List<WebElement> FindElements(LocatorsType locatorType, String locator){
        List<WebElement> elements = null;
        try {
            if (locatorType == LocatorsType.ByXpath) {
                elements =  driver.findElements(By.xpath(locator));
            }else if(locatorType == LocatorsType.ByID)
            {
                elements = driver.findElements(By.id(locator));
            }else if(locatorType == LocatorsType.ByClassName)
            {
                elements = driver.findElements(By.className(locator));
            }else if(locatorType == LocatorsType.ByName) {
                elements = driver.findElements(By.name(locator));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elements;
    }

    public void clickElement(By by){
        driver.findElement(by).click();
    }

    public void clickElement(WebElement element){
        element.click();
        WaitUntil wait = new WaitUntil();

    }

    public void clickElement(LocatorsType locatorsType, String locator){
        FindElement(locatorsType,locator).click();
    }

    public void setTextWithPressEnterKey(By by, String text){
        WebElement element = driver.findElement(by);
       element.sendKeys(text);
       element.sendKeys(Keys.ENTER);
    }

    public void setText(By by, String text){
        driver.findElement(by).sendKeys(text);
    }

    public boolean isEnabled(By by){
        try {
            if(driver.findElement(by).isEnabled())
            {
                return true;
            }else{
                return false;
            }
        }catch (Exception e) {
        e.printStackTrace();
        }
        return true;
    }

    public String getText(By by){
        String text = "";
        try {
            text = driver.findElement(by).getText();
            return text;
        }catch (NoSuchElementException e){
            e.printStackTrace();
        }
        return text;
    }

    public void closeCurrentBrowser(){
        driver.close();
    }

    public void closeAllBrowsers(){
        driver.quit();
    }
}
