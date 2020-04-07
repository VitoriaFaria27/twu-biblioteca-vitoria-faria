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

    Biblioteca biblioteca;

    @Before
    public void setup(){

        List<Book> books = new ArrayList<Book>();

        books.add(new Book("Odisseia"));
        books.add(new Book("Sofocles"));

        biblioteca = new Biblioteca(books);
    }

    @Test
    public void welcomeMessageWorks() {

        String message = biblioteca.getWelcomeMessage();

        assertThat(message, is("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"));
    }

    @Test
    public void bibliotecaCanlistBooks () {

        String books = biblioteca.listBookNames();

        assertThat(books, is("Odisseia\nSofocles\n"));
    }
}
