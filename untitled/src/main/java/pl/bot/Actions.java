package pl.bot;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import scrapper.Books;
import scrapper.Genres;
import utils.SmallActions;

import java.io.IOException;
import java.util.List;

public class Actions extends Engine {

    Books booksScrapper;
    Genres genresScrapper;
    String title = "1984";

    String[] genres = {
            "beletrystyka",
            "literatura-faktu",
            "literatura-popularnonaukowa",
            "literatura-dziecieca",
            "komiksy",
            "poezja-dramat-satyra",
            "pozostale"};

    String author = "Andrzej Sapkowski";

    @BeforeMethod
    public void beforeSetup(){
        booksScrapper = new Books(getDriver(), title);
        genresScrapper = new Genres(getDriver());
    }

    @Test
    public void getRecommendation() throws IOException {
        booksScrapper.searchBooks();
    }

    @Test
    public void getByGenres() throws IOException {
        genresScrapper.getByGenres(genres[0]);
    }

    public void getBooksByAuthor(){

    }

}
