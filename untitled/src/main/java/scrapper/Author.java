package scrapper;

import framework.PageObject;
import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Cookie;
import utils.FileMaker;
import utils.SmallActions;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

public class Author extends PageObject {

    String url = "https://lubimyczytac.pl/autorzy";

    public Author(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean getByAuthor(String author) throws IOException {
        openApp();
        Cookie.acceptCookie(driver, "onetrust-accept-btn-handler", "id");
        driver.findElement(By.className("preStyledForm__input")).sendKeys(author);
        driver.findElement(By.className("preStyledForm__input")).sendKeys(Keys.ENTER);
        List<WebElement> authors = searchedAuthors();
        if (author.isEmpty()){
            System.out.println("None");
            return false;
        }else{
            authors.get(0).click();
            scrapBestBooksOfAuthor(author);
            return true;
        }
    }

    private void openApp(){
        driver.get(url);

    }
    private List<WebElement> searchedAuthors(){
        List<WebElement> authors = driver.findElements(By.className("authorAllBooks__singleTextAuthor"));
        return  authors;
    }

    private void scrapBestBooksOfAuthor(String author) throws IOException {
        SmallActions.selectItemOrder(driver, "tabsNav__sortSelectInside", 2);
        SmallActions.scrollPage(driver, 50);
        List<WebElement> authorsBooks = driver.findElements(By.className("authorAllBooks__single"));
        FileMaker fm = new FileMaker(PageObject.PATH);
        String filename = fm.saveFile(author);
        for(WebElement book: authorsBooks){
            String title = book.findElement(By.className("authorAllBooks__singleTextTitle")).getText();
            String rating = book.findElement(By.className("listLibrary__ratingStarsNumber")).getText();
            String imgUrl = book.findElement(By.className("img-fluid")).getText();
            fm.addElementToFile(filename, title, author, rating, imgUrl);
        }

    }


}
