package com.twu.biblioteca.model;

import java.util.List;

public class Biblioteca {

    private List<Book> books;

    public Biblioteca(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public String getWelcomeMessage(){
        return "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    }

    public String listBookNames() {

        StringBuilder message = new StringBuilder();

        for(Book book : books){
            message.append(book.getInfo());
        }

        return message.toString();
    }
}
