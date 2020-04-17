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

    Biblioteca biblioteca;

    @Before
    public void setup(){

        List<Book> books = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();

        biblioteca = new Biblioteca(books, movies);
    }

    @Test
    public void welcomeMessageWorks () {

        String message = biblioteca.getWelcomeMessage();

        assertThat(message, is("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"));
    }

    @Test
    public void shouldReturnMenuNotLogedIn() {

        String menu = biblioteca.getMenu();

        assertThat(menu , is("List of books\t\tList of movies\t\tLogin\t\tQuit"));
    }

    @Test
    public void shouldReturnMenuLoggedInLibrarian() {

        biblioteca.login("999-9999","barfoo");

        String menu = biblioteca.getMenu();

        assertThat(menu , is("List of books\t\tList of movies\t\tView Renter\t\tView User Info\t\tLogout\t\tQuit"));
    }

    @Test
    public void shouldReturnMenuLoggedInUser() {

        biblioteca.login("123-4567","foobar");

        String menu = biblioteca.getMenu();

        assertThat(menu , is("List of books\t\tList of movies\t\tCheckout\t\tReturn\t\tView User Info\t\tLogout\t\tQuit"));
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
}
