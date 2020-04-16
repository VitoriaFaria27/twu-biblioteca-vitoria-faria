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

        User user = new User("123-4567", "foobar");

        boolean loginSuccessful = biblioteca.login(user.getLibraryNumber(), user.getPassword());

        assertTrue(loginSuccessful);
    }

    @Test
    public void loggedUserShouldBeTheSameAsInput() {

        User user = new User("123-4567", "foobar");

        boolean loginSuccessful = biblioteca.login(user.getLibraryNumber(), user.getPassword());

        assertThat(biblioteca.getLoggedUser().getLibraryNumber(), is("123-4567"));
    }

    @Test
    public void shouldFailToLoginAsTheUSerIsNotRegistered() {

        User user = new User("999-4567", "barfoo");

        boolean loginSuccessful = biblioteca.login(user.getLibraryNumber(), user.getPassword());

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
}
