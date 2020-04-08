package com.twu.biblioteca.model;

import java.util.List;

public class Biblioteca {

    private final String LIST_OF_BOOKS = "List of books";
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

    public String listBooksInfo() {

        StringBuilder message = new StringBuilder();

        message.append("Name\tAuthor\tYear\n");

        for(Book book : books){
            message.append(book.getInfo());
        }

        return message.toString();
    }

    public String getOutputFromCommand(String command) {

        if (command.equals(LIST_OF_BOOKS)){
            return listBooksInfo();
        }

        else return null;
    }

    public String getMenu() {
        return LIST_OF_BOOKS;
    }
}
