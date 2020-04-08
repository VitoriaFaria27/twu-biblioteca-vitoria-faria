package com.twu.biblioteca;


import com.twu.biblioteca.model.Biblioteca;
import com.twu.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class BibliotecaTest {

    public static final String LIST_OF_BOOKS_INFO = "Name\tAuthor\tYear\nOdisseia\tfoo\t1993\nSofocles\tbar\t1997\n";
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

        String outputForUser = biblioteca.getOutputFromCommand("List of books");

        assertThat(outputForUser, is(LIST_OF_BOOKS_INFO));
    }

    @Test
    public void shouldreturnMenu() {

        String menu = biblioteca.getMenu();

        assertThat(menu , is("List of books"));
    }
}
