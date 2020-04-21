package com.twu.biblioteca;


import com.twu.biblioteca.model.Biblioteca;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.twu.biblioteca.Constants.Constants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BibliotecaBookTest {

    private static final String LIST_OF_BOOKS_INFO = "|     Name | Author | Year |\n| Odisseia |    foo | 1993 |\n| Sofocles |    bar | 1997 |\n";
    private static final String CHECKEDOUT_BOOK_RENTER = "123-4567";
    private static final String BOOK_LIST_WITHOUT_ODISSEIA = "|     Name | Author | Year |\n| Sofocles |    bar | 1997 |\n";
    Biblioteca biblioteca;

    @Before
    public void setup(){

        List<Book> books = new ArrayList<>();

        books.add(new Book("Odisseia", "foo", 1993));
        books.add(new Book("Sofocles", "bar", 1997));

        List<Movie> movies = new ArrayList<>();

        biblioteca = new Biblioteca(books, movies);
        biblioteca.login("123-4567","foobar");
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

        assertThat(book.getInfo(), is(new String[]{"Odisseia", "foo", "1993"}));
    }

    @Test
    public void commandShouldReturnListOfBooks() {

        String outputForUser = biblioteca.runUserCommand("List of books");

        assertThat(outputForUser, is(LIST_OF_BOOKS_INFO));
    }

    @Test
    public void checkedOutBookIsNotListedAnymore() {
        biblioteca.runUserCommand("Checkout book Odisseia");

        assertThat(biblioteca.listBooksInfo(), is(BOOK_LIST_WITHOUT_ODISSEIA));
    }

    @Test
    public void findBookByName() {

        Book book = biblioteca.findBookByName("Odisseia");

        assertThat(book.getName(), is("Odisseia"));
    }

    @Test
    public void checkOutCommandShouldReturnMessage() {

        String outputForUser = biblioteca.runUserCommand("Checkout book Odisseia");

        assertThat(outputForUser, is(SUCCESSFUL_CHECKOUT_MESSAGE));
    }

    @Test
    public void checkOutCommandShouldReturnNotAvailableMessage() {

        String outputForUser = biblioteca.runUserCommand("Checkout book foo");

        assertThat(outputForUser, is(UNSUCCESSFUL_CHECKOUT_MESSAGE));
    }

    @Test
    public void checkOutCommandShouldReturnNotAvailableMessageIfAlreadyCheckedOut() {

        biblioteca.runUserCommand("Checkout book Odisseia");

        String outputForUser = biblioteca.runUserCommand("Checkout book Odisseia");

        assertThat(outputForUser, is(UNSUCCESSFUL_CHECKOUT_MESSAGE));
    }

    @Test
    public void returnedBookIsIsListedAgain() {
        biblioteca.runUserCommand("Checkout book Odisseia");

        assertThat(biblioteca.listBooksInfo(), is(BOOK_LIST_WITHOUT_ODISSEIA));

        biblioteca.runUserCommand("Return book Odisseia");

        assertThat(biblioteca.listBooksInfo(), is(LIST_OF_BOOKS_INFO));
    }

    @Test
    public void returnCommandShouldReturnMessage() {

        biblioteca.runUserCommand("Checkout book Odisseia");

        String outputForUser = biblioteca.runUserCommand("Return book Odisseia");

        assertThat(outputForUser, is(SUCCESSFUL_RETURN_MESSAGE));
    }

    @Test
    public void returnCommandShouldReturnUnsuccessfulMessage() {

        String outputForUser = biblioteca.runUserCommand("Return book foo");

        assertThat(outputForUser, is(UNSUCCESSFUL_RETURN_MESSAGE));
    }

    @Test
    public void returnCommandShouldReturnUnsuccessfulMMessageIfAlreadyReturned() {

        biblioteca.runUserCommand("Checkout book Odisseia");
        biblioteca.runUserCommand("Return book Odisseia");

        String outputForUser = biblioteca.runUserCommand("Return book Odisseia");

        assertThat(outputForUser, is(UNSUCCESSFUL_RETURN_MESSAGE));
    }

    @Test
    public void checkedOutBookShouldHoldTheRenterInfo() {

        biblioteca.login("123-4567","foobar");
        Book book = biblioteca.getBooks().get(0);

        book.checkOut("123-4567");

        assertThat(book.getRenter(), is ("123-4567"));
    }

    @Test
    public void commandShouldReturnBookRenter() {

        biblioteca.runUserCommand("Checkout book Odisseia");
        biblioteca.login("999-9999", "barfoo");

        String outputForUser = biblioteca.runUserCommand("View Renter Odisseia");

        assertThat(outputForUser, is(CHECKEDOUT_BOOK_RENTER));
    }

    @Test
    public void commandShouldReturnNotBookRenterIfNotCheckedOut() {

        biblioteca.login("999-9999", "barfoo");

        String outputForUser = biblioteca.runUserCommand("View Renter Odisseia");

        assertThat(outputForUser, is(BOOK_NOT_CHECKEDOUT_MESSAGE));
    }

    @Test
    public void commandShouldReturnBookNotFoundIfTryingToViewNonExistentBook() {

        biblioteca.runUserCommand("Checkout book Odisseia");
        biblioteca.login("999-9999", "barfoo");

        String outputForUser = biblioteca.runUserCommand("View Renter foo");

        assertThat(outputForUser, is(BOOK_NOT_FOUND_MESSAGE));
    }

    @Test
    public void commandShouldNotReturnBookRenterIfUserIsNotLibrarian() {

        biblioteca.runUserCommand("Checkout book Odisseia");

        String outputForUser = biblioteca.runUserCommand("View Renter Odisseia");

        assertThat(outputForUser, is(YOU_DONT_HAVE_PERMISSION_MESSAGE));
    }
}
