package com.twu.biblioteca;

import com.twu.biblioteca.model.Biblioteca;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.twu.biblioteca.Constants.Constants.USER_NOT_LOGGED_MESSAGE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BibliotecaUserTest {

    Biblioteca biblioteca;

    @Before
    public void setup(){
        List<Book> books = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();

        biblioteca = new Biblioteca(books, movies);
    }

    @Test
    public void shouldDisplayUserInfo() {

        biblioteca.login("123-4567","foobar");

        String output = biblioteca.runUserCommand("Show User Info");

        assertThat(output, is("Name\t\tE-mail\t\tPhone number\nDude\t\tdude@gmail.com\t\t5555-5555\n"));
    }

    @Test
    public void shouldNotDisplayUserInfo() {

        String output = biblioteca.runUserCommand("Show User Info");

        assertThat(output, is(USER_NOT_LOGGED_MESSAGE));
    }
}

