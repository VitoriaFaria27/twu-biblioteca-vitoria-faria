package com.twu.biblioteca.model;

import com.twu.biblioteca.Abstract.LibraryMedia;

import java.util.List;

public class Biblioteca {

    private static final String BOOK_LIST_HEADER = "Name\t\tAuthor\t\tYear\n";
    private static final String MOVIE_LIST_HEADER = "Name\t\tYear\t\tDirector\t\tRating\n";
    private static final String LIST_OF_BOOKS = "List of books";
    private static final String LIST_OF_MOVIES = "List of movies";
    private static final String QUIT = "Quit";
    private static final String CHECKOUT = "Checkout";
    private static final String RETURN = "Return";

    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String INVALID_OPTION_MESSAGE = "Please select a valid option!";
    private static final String SUCCESSFUL_CHECKOUT_MESSAGE = "Thank you! Enjoy the book";
    private static final String UNSUCCESSFUL_CHECKOUT_MESSAGE = "Sorry, that book is not available";
    private static final String SUCCESSFUL_RETURN_MESSAGE = "Thank you for returning the book";
    private static final String UNSUCCESSFUL_RETURN_MESSAGE = "That is not a valid book to return.";
    private static final String SUCCESSFUL_MOVIE_CHECKOUT_MESSAGE = "Thank you! Enjoy the movie";
    private static final String UNSUCCESSFUL_MOVIE_CHECKOUT_MESSAGE = "Sorry, that movie is not available";

    private List<Book> books;
    private List<Movie> movies;

    public Biblioteca(List<Book> books, List<Movie> movies) {
        this.books = books;
        this.movies = movies;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public List<Movie> getMovies() {
        return this.movies;
    }

    public String getWelcomeMessage(){
        return WELCOME_MESSAGE;
    }

    public String listBooksInfo() {

        return listMediasInfo(BOOK_LIST_HEADER, this.books);
    }

    public String listMoviesInfo() {

        return listMediasInfo(MOVIE_LIST_HEADER, this.movies);
    }

    private String listMediasInfo(String header, List<? extends LibraryMedia> medias) {
        StringBuilder message = new StringBuilder();
        message.append(header);

        for(LibraryMedia media : medias){
            message.append(media.getInfo());
        }

        return message.toString();
    }

    public String runUserCommand(String command) {

        if (LIST_OF_BOOKS.equals(command)){
            return listBooksInfo();
        }

        if (LIST_OF_MOVIES.equals(command)){
            return listMoviesInfo();
        }

        if (command.contains(" ")) {
            return runSpecificMediaCommand(command);
        }

        return INVALID_OPTION_MESSAGE;
    }

    private String runSpecificMediaCommand(String command) {
        String[] splitCommand = command.split(" ");
        String rootCommand = splitCommand[0];

        if (CHECKOUT.equals(rootCommand)){
            String mediaType = splitCommand[1];
            String mediaName = command.replace(CHECKOUT + " " + mediaType + " ", "");

            return runCheckOutCommand(mediaType, mediaName);
        }

        if (RETURN.equals(rootCommand)){
            String mediaName = command.replace(RETURN + " ", "");
            return runReturnCommand(mediaName);
        }

        return INVALID_OPTION_MESSAGE;
    }

    private String runReturnCommand(String bookName) {
        Book book = this.findBookByName(bookName);

        if(book == null || !book.isCheckedOut()){
            return UNSUCCESSFUL_RETURN_MESSAGE;
        }

        book.checkIn();

        return SUCCESSFUL_RETURN_MESSAGE;
    }

    private String runCheckOutCommand(String mediaType, String mediaName) {
        if ("book".equals(mediaType)){
            return runCheckOutBookCommand(mediaName);
        }

        if ("movie".equals(mediaType)){
            return runCheckOutMovieCommand(mediaName);
        }

        return INVALID_OPTION_MESSAGE;
    }

    private String runCheckOutBookCommand(String bookName) {
        Book book = this.findBookByName(bookName);

        if(book == null || book.isCheckedOut()){
            return UNSUCCESSFUL_CHECKOUT_MESSAGE;
        }

        book.checkOut();

        return SUCCESSFUL_CHECKOUT_MESSAGE;
    }

    private String runCheckOutMovieCommand(String name) {
        Movie movie = this.findMovieByName(name);

        if(movie == null || movie.isCheckedOut()){
            return UNSUCCESSFUL_MOVIE_CHECKOUT_MESSAGE;
        }

        movie.checkOut();

        return SUCCESSFUL_MOVIE_CHECKOUT_MESSAGE;
    }

    public String getMenu() {
        return LIST_OF_BOOKS + "\t\t" + LIST_OF_MOVIES + "\t\t"+ CHECKOUT + "\t\t" + RETURN + "\t\t" + QUIT;
    }

    public boolean shouldQuit(String command) {
        return QUIT.equals(command);
    }

    public Book findBookByName(String name) {
        return (Book) findByName(books, name);
    }

    public Movie findMovieByName(String name) {
        return (Movie) findByName(movies, name);
    }

    public LibraryMedia findByName(List<? extends LibraryMedia> list, String name) {
        return list.stream().filter(media -> name.equals(media.getName())).findFirst().orElse(null);
    }
}
