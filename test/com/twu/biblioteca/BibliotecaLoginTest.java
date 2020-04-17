package com.twu.biblioteca;

import com.twu.biblioteca.model.Biblioteca;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BibliotecaLoginTest {

    Biblioteca biblioteca;

    @Before
    public void setup(){
        List<Book> books = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();

        biblioteca = new Biblioteca(books, movies);
    }

    @Test
    public void shouldLoginSuccessfully() {

        boolean loginSuccessful = biblioteca.login("123-4567", "foobar");

        assertTrue(loginSuccessful);
    }

    @Test
    public void loggedUserShouldBeTheSameAsInput() {

        boolean loginSuccessful = biblioteca.login("123-4567", "foobar");

        assertThat(biblioteca.getLoggedUser().getLibraryNumber(), is("123-4567"));
    }

    @Test
    public void shouldFailToLoginAsTheUSerIsNotRegistered() {

        boolean loginSuccessful = biblioteca.login("999-4567", "barfoo");

        assertFalse(loginSuccessful);
    }

    @Test
    public void shouldLoginFromCommandLine() {

        String outputForUser = biblioteca.runUserCommand("Login 123-4567 foobar");

        assertThat(outputForUser, is("You have logged in successfully!"));
    }

    @Test
    public void shouldLoginFromAsLibrarian() {

        String outputForUser = biblioteca.runUserCommand("Login 999-9999 barfoo");

        assertThat(outputForUser, is("You have logged in successfully!"));
    }

    @Test
    public void logOutTest() {

        biblioteca.runUserCommand("Login 123-4567 foobar");
        String outputForUser = biblioteca.runUserCommand("Logout");

        assertThat(biblioteca.getLoggedUser(), is((User) null));
    }

    @Test
    public void logOutMessageTest() {

        biblioteca.runUserCommand("Login 123-4567 foobar");
        String outputForUser = biblioteca.runUserCommand("Logout");

        assertThat(outputForUser, is("You have logged out successfully!"));
    }
}
