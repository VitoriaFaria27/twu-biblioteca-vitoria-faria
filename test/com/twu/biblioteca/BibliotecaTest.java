package com.twu.biblioteca;


import com.twu.biblioteca.model.Biblioteca;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class BibliotecaTest {

    Biblioteca biblioteca;

    @Before
    public void setup(){
        biblioteca = new Biblioteca();
    }

    @Test
    public void welcomeMessageWorks() {

        String message = biblioteca.getWelcomeMessage();

        assertThat(message, is("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!"));
    }
}
