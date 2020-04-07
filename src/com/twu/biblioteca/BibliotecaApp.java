package com.twu.biblioteca;

import com.twu.biblioteca.model.Biblioteca;
import com.twu.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {

        List<Book> books = new ArrayList<Book>();

        books.add(new Book("Odisseia"));
        books.add(new Book("Sofocles"));

        Biblioteca biblioteca = new Biblioteca(books);

        System.out.println(biblioteca.getWelcomeMessage());
        System.out.print(biblioteca.listBookNames());
    }
}
