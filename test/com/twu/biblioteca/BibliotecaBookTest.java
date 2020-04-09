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

public class BibliotecaBookTest {

    private static final String LIST_OF_BOOKS_INFO = "Name\t\tAuthor\t\tYear\nOdisseia\t\tfoo\t\t1993\nSofocles\t\tbar\t\t1997\n";
    private static final String SUCCESSFUL_CHECKOUT_MESSAGE = "Thank you! Enjoy the book";
    private static final String UNSUCCESSFUL_CHECKOUT_MESSAGE = "Sorry, that book is not available";
    private static final String SUCCESSFUL_RETURN_MESSAGE = "Thank you for returning the book";
    private static final String UNSUCCESSFUL_RETURN_MESSAGE = "That is not a valid book to return.";
    private static final String BOOK_LIST_WITHOUT_ODISSEIA = "Name\t\tAuthor\t\tYear\nSofocles\t\tbar\t\t1997\n";
    Biblioteca biblioteca;

    @Before
    public void setup(){

        List<Book> books = new ArrayList<>();

        books.add(new Book("Odisseia", "foo", 1993));
        books.add(new Book("Sofocles", "bar", 1997));

        List<Movie> movies = new ArrayList<>();

        biblioteca = new Biblioteca(books, movies);
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

        assertThat(book.getInfo(), is("Odisseia\t\tfoo\t\t1993\n"));
    }

    @Test
    public void commandShouldReturnListOfBooks() {

        String outputForUser = biblioteca.runUserCommand("List of books");

        assertThat(outputForUser, is(LIST_OF_BOOKS_INFO));
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
}
