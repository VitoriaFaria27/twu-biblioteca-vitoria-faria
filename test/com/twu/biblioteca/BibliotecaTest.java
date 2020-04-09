package com.twu.biblioteca;


import com.twu.biblioteca.model.Biblioteca;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BibliotecaTest {

    private static final String LIST_OF_BOOKS_INFO = "Name\tAuthor\tYear\nOdisseia\tfoo\t1993\nSofocles\tbar\t1997\n";
    private static final String LIST_OF_MOVIES_INFO = "Name\tYear\tDirector\tRating\nStar Wars\t1977\tGeorge Lucas\t9\nLego Movie\t2014\tSome Dude\t8\n";
    private static final String SUCCESSFUL_CHECKOUT_MESSAGE = "Thank you! Enjoy the book";
    private static final String UNSUCCESSFUL_CHECKOUT_MESSAGE = "Sorry, that book is not available";
    private static final String SUCCESSFUL_RETURN_MESSAGE = "Thank you for returning the book";
    private static final String UNSUCCESSFUL_RETURN_MESSAGE = "That is not a valid book to return.";
    private static final String BOOK_LIST_WITHOUT_ODISSEIA = "Name\tAuthor\tYear\nSofocles\tbar\t1997\n";
    Biblioteca biblioteca;

    @Before
    public void setup(){

        List<Book> books = new ArrayList<>();

        books.add(new Book("Odisseia", "foo", 1993));
        books.add(new Book("Sofocles", "bar", 1997));

        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Star Wars", 1977, "George Lucas", 9));
        movies.add(new Movie("Lego Movie", 2014, "Some Dude", 8));

        biblioteca = new Biblioteca(books, movies);
    }

    @Test
    public void welcomeMessageWorks () {

        String message = biblioteca.getWelcomeMessage();

        assertThat(message, is("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"));
    }

    @Test
    public void bookListIsFilled () {
        List<Book> books = biblioteca.getBooks();

        assertThat(books.size(), is(2));
    }

    @Test
    public void bookListHasRightContent () {
        List<Book> books = biblioteca.getBooks();

        assertThat(books.get(0).getName(), is("Odisseia"));
    }

    @Test
    public void bibliotecaCanlistBooks () {

        String books = biblioteca.listBooksInfo();

        assertThat(books, is(LIST_OF_BOOKS_INFO));
    }

    @Test
    public void bookCanReturnInfo (){

        Book book = biblioteca.getBooks().get(0);

        assertThat(book.getInfo(), is("Odisseia\tfoo\t1993\n"));
    }

    @Test
    public void commandShouldReturnListOfBooks() {

        String outputForUser = biblioteca.runUserCommand("List of books");

        assertThat(outputForUser, is(LIST_OF_BOOKS_INFO));
    }

    @Test
    public void shouldReturnMenu() {

        String menu = biblioteca.getMenu();

        assertThat(menu , is("List of books\tList of movies\tCheckout\tReturn\tQuit"));
    }

    @Test
    public void invalidCommandShouldReturnMessage() {

        String outputForUser = biblioteca.runUserCommand("foo");

        assertThat(outputForUser, is("Please select a valid option!"));
    }

    @Test
    public void shouldQuitIfQuitMessageIsProvided() {

        boolean shouldQuit = biblioteca.shouldQuit("Quit");

        assertTrue(shouldQuit);
    }

    @Test
    public void shouldNotQuitIfQuitMessageIsNotProvided() {

        boolean shouldNotQuit = biblioteca.shouldQuit("foo");

        assertFalse(shouldNotQuit);
    }

    @Test
    public void checkedOutBookIsNotListedAnymore() {
        biblioteca.runUserCommand("Checkout Odisseia");

        assertThat(biblioteca.listBooksInfo(), is(BOOK_LIST_WITHOUT_ODISSEIA));
    }

    @Test
    public void findBookByName() {

        Book book = biblioteca.findBookByName("Odisseia");

        assertThat(book.getName(), is("Odisseia"));
    }

    @Test
    public void checkOutCommandShouldReturnMessage() {

        String outputForUser = biblioteca.runUserCommand("Checkout Odisseia");

        assertThat(outputForUser, is(SUCCESSFUL_CHECKOUT_MESSAGE));
    }

    @Test
    public void checkOutCommandShouldReturnNotAvailableMessage() {

        String outputForUser = biblioteca.runUserCommand("Checkout foo");

        assertThat(outputForUser, is(UNSUCCESSFUL_CHECKOUT_MESSAGE));
    }

    @Test
    public void checkOutCommandShouldReturnNotAvailableMessageIfAlreadyCheckedOut() {

        biblioteca.runUserCommand("Checkout Odisseia");

        String outputForUser = biblioteca.runUserCommand("Checkout Odisseia");

        assertThat(outputForUser, is(UNSUCCESSFUL_CHECKOUT_MESSAGE));
    }

    @Test
    public void returnedBookIsIsListedAgain() {
        biblioteca.runUserCommand("Checkout Odisseia");

        assertThat(biblioteca.listBooksInfo(), is(BOOK_LIST_WITHOUT_ODISSEIA));

        biblioteca.runUserCommand("Return Odisseia");

        assertThat(biblioteca.listBooksInfo(), is(LIST_OF_BOOKS_INFO));
    }

    @Test
    public void returnCommandShouldReturnMessage() {

        biblioteca.runUserCommand("Checkout Odisseia");

        String outputForUser = biblioteca.runUserCommand("Return Odisseia");

        assertThat(outputForUser, is(SUCCESSFUL_RETURN_MESSAGE));
    }

    @Test
    public void returnCommandShouldReturnUnsuccessfulMessage() {

        String outputForUser = biblioteca.runUserCommand("Return foo");

        assertThat(outputForUser, is(UNSUCCESSFUL_RETURN_MESSAGE));
    }

    @Test
    public void returnCommandShouldReturnUnsuccessfulMMessageIfAlreadyReturned() {

        biblioteca.runUserCommand("Checkout Odisseia");
        biblioteca.runUserCommand("Return Odisseia");

        String outputForUser = biblioteca.runUserCommand("Return Odisseia");

        assertThat(outputForUser, is(UNSUCCESSFUL_RETURN_MESSAGE));
    }

    @Test
    public void movieListIsFilled () {
        List<Movie> movies = biblioteca.getMovies();

        assertThat(movies.size(), is(2));
    }

    @Test
    public void movieListHasRightContent () {
        List<Movie> movies = biblioteca.getMovies();

        assertThat(movies.get(0).getName(), is("Star Wars"));
    }

    @Test
    public void bibliotecaCanlistMovies () {

        String movies = biblioteca.listMoviesInfo();

        assertThat(movies, is(LIST_OF_MOVIES_INFO));
    }

    @Test
    public void movieCanReturnInfo (){

        Movie movie = biblioteca.getMovies().get(0);

        assertThat(movie.getInfo(), is("Star Wars\t1977\tGeorge Lucas\t9\n"));
    }

    @Test
    public void commandShouldReturnListOfmovies() {

        String outputForUser = biblioteca.runUserCommand("List of movies");

        assertThat(outputForUser, is(LIST_OF_MOVIES_INFO));
    }
}
