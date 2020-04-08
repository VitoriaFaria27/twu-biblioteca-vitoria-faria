package com.twu.biblioteca;


import com.twu.biblioteca.model.Biblioteca;
import com.twu.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BibliotecaTest {

    private static final String LIST_OF_BOOKS_INFO = "Name\tAuthor\tYear\nOdisseia\tfoo\t1993\nSofocles\tbar\t1997\n";
    private static final String ENJOY_THE_BOOK = "Thank you! Enjoy the book";
    private static final String BOOK_NOT_AVAILABLE = "Sorry, that book is not available";
    Biblioteca biblioteca;

    @Before
    public void setup(){

        List<Book> books = new ArrayList<>();

        books.add(new Book("Odisseia", "foo", 1993));
        books.add(new Book("Sofocles", "bar", 1997));

        biblioteca = new Biblioteca(books);
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

        assertThat(menu , is("List of books\tCheckout\tQuit"));
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

        assertThat(biblioteca.listBooksInfo(), is("Name\tAuthor\tYear\nSofocles\tbar\t1997\n"));
    }

    @Test
    public void findBookByName() {

        Book book = biblioteca.findBookByName("Odisseia");

        assertThat(book.getName(), is("Odisseia"));
    }

    @Test
    public void checkOutCommandShouldReturnMessage() {

        String outputForUser = biblioteca.runUserCommand("Checkout Odisseia");

        assertThat(outputForUser, is(ENJOY_THE_BOOK));
    }

    @Test
    public void checkOutCommandShouldReturnNotAvailableMessage() {

        String outputForUser = biblioteca.runUserCommand("Checkout foo");

        assertThat(outputForUser, is(BOOK_NOT_AVAILABLE));
    }
}
