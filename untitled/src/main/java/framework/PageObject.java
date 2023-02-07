package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;
import static utils.ByLocatorFinder.getByFromWebElement;
public class PageObject {
    private static final Duration TIMEOUT = Duration.ofSeconds(5);
    protected static final Logger LOGGER = Logger.getLogger(String.valueOf(PageObject.class));
    public static final String PATH = "D:\\dev\\testing\\Bot\\untitled\\src\\main\\files\\";
    protected WebDriver driver;

    protected void waitUntilElementIsVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        By elementByLocator = getByFromWebElement(element);
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementByLocator));
    }

    protected void waitUntilElementIsClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        By elementByLocator = getByFromWebElement(element);
        wait.until(ExpectedConditions.elementToBeClickable(elementByLocator));
    }


}
