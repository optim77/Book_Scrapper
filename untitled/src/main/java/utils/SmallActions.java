package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SmallActions {

    static String[] order = {
            "Liczba ocen malejąco",
            "Liczba ocen rosnąco",
            "Data wydania malejąco",
            "Data wydania rosnąco",
            "Ostatnio dodane malejąco",
            "Ostatnio dodane rosnąco"
    };
    static String[] order2 = {
            "Najnowsze",
            "Najstarsze",
            "Najwyżej oceniane",
            "Najniżej oceniane",
            "Alfabetycznie",
            "Liczba czytelników"
    };

    public static void openApp(WebDriver driver, String URL){
        driver.get(URL);
    }

    public static void scrollPage(WebDriver driver, int scrollValue){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight / 2)", "");
    }

    public static void selectItemOrder(WebDriver driver, String elementToClick, int orderIndex){
        Select dropDown = new Select(driver.findElement(By.className(elementToClick)));
        dropDown.selectByVisibleText(order2[orderIndex]);
    }
}
