package scrapper;

import framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Cookie;
import utils.FileMaker;
import utils.SmallActions;

import java.io.IOException;
import java.util.List;

public class TopBooks extends PageObject {

    String url = "https://lubimyczytac.pl/top100";

    public TopBooks(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void openApp() {
        driver.get(url);
    }

    public void getTop100(boolean allPages) throws IOException, InterruptedException {
        openApp();
        Cookie.acceptCookie(driver, "onetrust-accept-btn-handler", "id");
        if (allPages){
            for (int i = 2; i <= getPages(); i ++){
                scrapByPages();
                getNextPage(i);
            }
        }else{
            scrapByPages();
        }


    }

    private void scrapByPages() throws IOException {
        List<WebElement> books = driver.findElements(By.className("authorAllBooks__single"));
        FileMaker fm = new FileMaker(PageObject.PATH);
        String filename = fm.saveFile("Top-100");
        for (WebElement book : books){
            String title = book.findElement(By.className("authorAllBooks__singleTextTitle")).getText();
            String author = book.findElement(By.className("authorAllBooks__singleTextAuthor")).getText();
            String rating = book.findElement(By.className("listLibrary__ratingStarsNumber")).getText();
            String imgUrl = book.findElement(By.className("img-fluid")).getText();
            fm.addElementToFile(filename, title, author, rating, imgUrl);
        }
    }

    private int getPages(){
        String pages = driver.findElement(By.className("paginationList__info")).getText();
        String digits = pages.replaceAll("[^\\d-]", "");
        return Integer.parseInt(digits);
    }
    private void getNextPage(int page) throws InterruptedException {
        driver.findElement(By.className("paginationList__input")).clear();
        driver.findElement(By.className("paginationList__input")).sendKeys(Integer.toString(page));
        driver.findElement(By.className("paginationList__input")).sendKeys(Keys.ENTER);
        waitUntilElementIsClickable(driver.findElement(By.className("authorAllBooks__singleTextAuthor")));
    }

}
