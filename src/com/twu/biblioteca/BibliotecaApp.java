package com.twu.biblioteca;

import com.twu.biblioteca.model.Biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();

        System.out.println(biblioteca.getWelcomeMessage());
    }
}
