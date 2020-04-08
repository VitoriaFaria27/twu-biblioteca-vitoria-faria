package com.twu.biblioteca;

import com.twu.biblioteca.model.Biblioteca;
import com.twu.biblioteca.model.Book;

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

        return new Biblioteca(books);
    }

    private static void interactWithUser(Biblioteca biblioteca) {
        boolean shouldQuit = false;

        System.out.println(biblioteca.getWelcomeMessage());

        try (Scanner scanner = new Scanner(System.in)) {
            while (!shouldQuit){

                System.out.println("Please enter a command from the following menu:\n");
                System.out.println(biblioteca.getMenu());

                String command = scanner.nextLine();

                shouldQuit = biblioteca.shouldQuit(command);

                if(!shouldQuit){
                    System.out.println(biblioteca.getOutputFromCommand(command));
                }

            }
        }
    }
}
