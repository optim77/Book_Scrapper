package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cookie {
    public static void acceptCookie(WebDriver driver, String element, String type){
        switch (type) {
            case "id" -> driver.findElement(By.id(element)).click();
            case "class" -> driver.findElement(By.className(element)).click();
        }
    }
}
