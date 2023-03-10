package pl.bot;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import scrapper.Author;
import scrapper.Books;
import scrapper.Genres;
import scrapper.TopBooks;
import utils.SmallActions;

import java.io.IOException;
import java.util.List;

public class Actions extends Engine {

    Books booksScrapper;
    Genres genresScrapper;

    Author authorScrapper;

    TopBooks topBooks;
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
        authorScrapper = new Author(getDriver());
        topBooks = new TopBooks(getDriver());
    }

    @Test
    public void getRecommendation() throws IOException {
        booksScrapper.searchBooks();
    }

    @Test
    public void getByGenres() throws IOException {
        genresScrapper.getByGenres(genres[0]);
    }

    @Test
    public void getBooksByAuthor() throws IOException {
        authorScrapper.getByAuthor("proust");
    }

    @Test
    public void getTop100() throws IOException, InterruptedException {
        topBooks.getTop100(false);
    }

}
