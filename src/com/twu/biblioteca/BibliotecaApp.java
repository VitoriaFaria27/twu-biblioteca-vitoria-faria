package com.twu.biblioteca;

import com.twu.biblioteca.model.Biblioteca;
import com.twu.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {

        List<Book> books = new ArrayList<>();

        books.add(new Book("Odisseia", "foo", 1993));
        books.add(new Book("Sofocles", "bar", 1997));

        Biblioteca biblioteca = new Biblioteca(books);

        System.out.println(biblioteca.getWelcomeMessage());
        System.out.print(biblioteca.listBookNames());
    }
}
