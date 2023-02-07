package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class SmallActions {

    public static void openApp(WebDriver driver, String URL){
        driver.get(URL);
    }

    public static void scrollPage(WebDriver driver, int scrollValue){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight / 2)", "");
    }
}
