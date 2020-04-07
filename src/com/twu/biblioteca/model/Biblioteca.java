package com.twu.biblioteca.model;

import java.util.List;

public class Biblioteca {

    private List<Book> books;

    public Biblioteca(List<Book> books) {
        this.books = books;
    }

    public String getWelcomeMessage(){
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }

    public String listBookNames() {

        String message = "";

        for(Book book : books){
            message += (book.getName() + "\n");
        }

        return message;
    }
}
