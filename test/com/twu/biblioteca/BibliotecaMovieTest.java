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

public class BibliotecaMovieTest {

    private static final String LIST_OF_MOVIES_INFO = "Name\t\tYear\t\tDirector\t\tRating\nStar Wars\t\t1977\t\tGeorge Lucas\t\t9\nLego Movie\t\t2014\t\tSome Dude\t\t8\n";
    private static final String LIST_OF_MOVIES_INFO_WITHOUT_STAR_WARS = "Name\t\tYear\t\tDirector\t\tRating\nLego Movie\t\t2014\t\tSome Dude\t\t8\n";
    private static final String SUCCESSFUL_CHECKOUT_MESSAGE = "Thank you! Enjoy the movie";
    private static final String UNSUCCESSFUL_CHECKOUT_MESSAGE = "Sorry, that movie is not available";
    Biblioteca biblioteca;

    @Before
    public void setup(){

        List<Book> books = new ArrayList<>();

        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Star Wars", 1977, "George Lucas", 9));
        movies.add(new Movie("Lego Movie", 2014, "Some Dude", 8));

        biblioteca = new Biblioteca(books, movies);
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

        assertThat(movie.getInfo(), is("Star Wars\t\t1977\t\tGeorge Lucas\t\t9\n"));
    }

    @Test
    public void commandShouldReturnListOfMovies() {

        String outputForUser = biblioteca.runUserCommand("List of movies");

        assertThat(outputForUser, is(LIST_OF_MOVIES_INFO));
    }

    @Test
    public void checkedOutMovieIsNotListedAnymore() {
        biblioteca.runUserCommand("Checkout movie Star Wars");

        assertThat(biblioteca.listMoviesInfo(), is(LIST_OF_MOVIES_INFO_WITHOUT_STAR_WARS));
    }

    @Test
    public void findMovieByName() {

        Movie movie = biblioteca.findMovieByName("Star Wars");

        assertThat(movie.getName(), is("Star Wars"));
    }

    @Test
    public void checkOutCommandShouldReturnMessage() {

        String outputForUser = biblioteca.runUserCommand("Checkout movie Star Wars");

        assertThat(outputForUser, is(SUCCESSFUL_CHECKOUT_MESSAGE));
    }

    @Test
    public void checkOutCommandShouldReturnNotAvailableMessage() {

        String outputForUser = biblioteca.runUserCommand("Checkout movie foo");

        assertThat(outputForUser, is(UNSUCCESSFUL_CHECKOUT_MESSAGE));
    }

    @Test
    public void checkOutCommandShouldReturnNotAvailableMessageIfAlreadyCheckedOut() {

        biblioteca.runUserCommand("Checkout movie Star Wars");

        String outputForUser = biblioteca.runUserCommand("Checkout movie Star Wars");

        assertThat(outputForUser, is(UNSUCCESSFUL_CHECKOUT_MESSAGE));
    }
}
