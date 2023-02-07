package scrapper;

import framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pl.bot.Actions;
import pl.bot.Engine;
import utils.FileMaker;

import java.io.IOException;
import java.util.List;

import static utils.SmallActions.scrollPage;
public class Genres extends PageObject {

    private String URL = "https://lubimyczytac.pl/ksiazki/kategorie";
    String[] order = {
            "Liczba ocen malejąco",
            "Liczba ocen rosnąco",
            "Data wydania malejąco",
            "Data wydania rosnąco",
            "Ostatnio dodane malejąco",
            "Ostatnio dodane rosnąco"
    };

    public Genres(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void openApp() {
        driver.get(URL);
    }

    public void getByGenres(String genres) throws IOException {
        openApp();
        acceptCookies();
        openGenres(genres);
        selectItemOrder();
        scrapAndSaveToFile(genres);
    }

    private void acceptCookies() {
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
    }

    private void openGenres(String genreName) {
        driver.get("https://lubimyczytac.pl/kategoria/" + genreName);
        List<WebElement> getAll = driver.findElements(By.className("btn-primary"));
        getAll.get(0).click();
    }

    /* Select dropdown menu and select ordering type by such type */
    private void selectItemOrder(){
        Select dropDown = new Select(driver.findElement(By.className("tabsNav__sortSelectInside")));
        dropDown.selectByVisibleText(order[0]);
    }

    private void scrapAndSaveToFile(String genres) throws IOException {
        List<WebElement> books = driver.findElements(By.id("listLibrary"));
        FileMaker fm = new FileMaker(PageObject.PATH);
        String filename = fm.saveFile(genres);
        for(WebElement book :books){
            String name = book.findElement(By.className("authorAllBooks__singleTextTitle")).getText();
            String author = book.findElement(By.className("authorAllBooks__singleTextAuthor")).getText();
            String rating = book.findElement(By.className("listLibrary__ratingStarsNumber")).getText();
            String imgUrl = book.findElement(By.className("img-fluid")).getText();
            fm.addElementToFile(filename, name, author, rating, imgUrl);
        }
    }
}
