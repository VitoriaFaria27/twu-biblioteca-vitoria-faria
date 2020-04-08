package com.twu.biblioteca.model;

import java.util.List;

public class Biblioteca {

    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String LIST_HEADER = "Name\tAuthor\tYear\n";
    private static final String INVALID_OPTION_MESSAGE = "Please select a valid option!";
    private static final String LIST_OF_BOOKS = "List of books";
    private List<Book> books;

    public Biblioteca(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public String getWelcomeMessage(){
        return WELCOME_MESSAGE;
    }

    public String listBooksInfo() {

        StringBuilder message = new StringBuilder();

        message.append(LIST_HEADER);

        for(Book book : books){
            message.append(book.getInfo());
        }

        return message.toString();
    }

    public String getOutputFromCommand(String command) {

        if (command.equals(LIST_OF_BOOKS)){
            return listBooksInfo();
        }

        else return INVALID_OPTION_MESSAGE;
    }

    public String getMenu() {
        return LIST_OF_BOOKS;
    }
}
