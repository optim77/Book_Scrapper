package pl.bot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Engine {



    private static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setupWebDriver(){
        setupChromeDriver();

    }

    private void setupChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "D:\\dev\\testing\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    protected WebDriver getDriver(){
        if(driver != null){
            return driver;
        }
        setupWebDriver();
        return getDriver();
    }



}
