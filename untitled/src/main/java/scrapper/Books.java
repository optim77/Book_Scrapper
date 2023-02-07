package scrapper;

import framework.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.FileMaker;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
public class Books extends PageObject {

    @FindBy(css = ".searchbox__input")
    WebElement searchBox;

    @FindBy(css = "#onetrust-accept-btn-handler")
    private WebElement cookiesAcceptButton;

    String URL = "https://lubimyczytac.pl/katalog";
    String title;
    public Books(WebDriver driver, String title){
        this.driver = driver;
        this.title = title;
        PageFactory.initElements(driver, this);
    }

    public void openApp(){
        driver.get(URL);
    }

    public void searchBooks() throws IOException {
        openApp();
        // Insert title of book and try to search
        searchBox.sendKeys(title);
        searchBox.sendKeys(Keys.ENTER);

        // Get searched book
        chooseBook();
        // Get recommendation from selected book and save it in a file
        getFamiliars();

    }

    private void acceptCookies(){
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
    }

    private void chooseBook(){
        try{
            WebElement book = driver.findElement(By.className("authorAllBooks__singleImgWrap__hovered"));
            book.click();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Element doesn't exist");
        }
    }

    private void getFamiliars() throws IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight / 2)", "");
        List<WebElement> recommendations = driver.findElements(By.className("is-desktop"));
        int counter = 0;
        FileMaker fm = new FileMaker(PageObject.PATH);
        String  filename = fm.saveFile(title);
        for (WebElement i: recommendations){
            String name = i.findElement(By.className("small-book-title")).getText();
            String author = i.findElement(By.className("small-book-author")).getText();
            String rating = i.findElement(By.className("rating-value")).getText();
            String imgUrl = i.findElement(By.className("img-fluid")).getText();
            fm.addElementToFile(filename, name, author, rating, imgUrl);
        }
    }

}
