package com.twu.biblioteca;

import com.twu.biblioteca.model.Biblioteca;
import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {

        Biblioteca biblioteca = buildBiblioteca();

        interactWithUser(biblioteca);
    }

    private static Biblioteca buildBiblioteca() {
        List<Book> books = new ArrayList<>();

        books.add(new Book("Odisseia", "foo", 1993));
        books.add(new Book("Sofocles", "bar", 1997));

        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Star Wars", 1977, "George Lucas", 9));
        movies.add(new Movie("Lego Movie", 2014, "Some Dude", 8));

        return new Biblioteca(books, movies);
    }

    private static void interactWithUser(Biblioteca biblioteca) {
        boolean shouldQuit = false;

        System.out.println(biblioteca.getWelcomeMessage());

        try (Scanner scanner = new Scanner(System.in)) {
            while (!shouldQuit){

                printMenu(biblioteca);

                String command = scanner.nextLine();

                shouldQuit = biblioteca.shouldQuit(command);

                if(!shouldQuit){
                    System.out.println(biblioteca.runUserCommand(command));
                }

            }
        }
    }

    private static void printMenu(Biblioteca biblioteca) {
        System.out.println("Please enter a command from the following menu:");
        System.out.println(biblioteca.getMenu());
    }
}
