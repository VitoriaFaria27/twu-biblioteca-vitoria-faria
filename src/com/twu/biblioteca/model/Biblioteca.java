package com.twu.biblioteca.model;

import java.util.List;

public class Biblioteca {

    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String LIST_HEADER = "Name\tAuthor\tYear\n";
    private static final String INVALID_OPTION_MESSAGE = "Please select a valid option!";
    private static final String LIST_OF_BOOKS = "List of books";
    private static final String QUIT = "Quit";
    private static final String CHECKOUT = "Checkout";
    private static final String ENJOY_THE_BOOK = "Thank you! Enjoy the book";
    private static final String BOOK_NOT_AVAILABLE = "Sorry, that book is not available";
    private static final String RETURN = "Return";
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

    public String runUserCommand(String command) {

        if (LIST_OF_BOOKS.equals(command)){
            return listBooksInfo();
        }

        if (command.contains(" ")) {
            return runSpecificBookCommand(command);
        }

        return INVALID_OPTION_MESSAGE;
    }

    private String runSpecificBookCommand(String command) {
        String[] splitCommand = command.split(" ");
        String rootCommand = splitCommand[0];
        String bookName = splitCommand[1];

        if (CHECKOUT.equals(rootCommand)){
            return runCheckOutCommand(bookName);
        }

        if (RETURN.equals(rootCommand)){
            return runReturnCommand(bookName);
        }

        return INVALID_OPTION_MESSAGE;
    }

    private String runReturnCommand(String bookName) {
        Book book = this.findBookByName(bookName);

        if(book == null){
            return BOOK_NOT_AVAILABLE;
        }

        book.checkIn();

        return ENJOY_THE_BOOK;
    }

    private String runCheckOutCommand(String bookName) {
        Book book = this.findBookByName(bookName);

        if(book == null){
            return BOOK_NOT_AVAILABLE;
        }

        book.checkOut();

        return ENJOY_THE_BOOK;
    }

    public String getMenu() {
        return LIST_OF_BOOKS + "\t" + CHECKOUT + "\t" + RETURN + "\t" + QUIT;
    }

    public boolean shouldQuit(String command) {
        return QUIT.equals(command);
    }

    public Book findBookByName(String name) {
        return books.stream().filter(book -> name.equals(book.getName())).findFirst().orElse(null);
    }
}
