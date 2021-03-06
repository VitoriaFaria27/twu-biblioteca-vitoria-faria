package com.twu.biblioteca.model;

import com.twu.biblioteca.Abstract.LibraryMedia;
import com.twu.biblioteca.Util.TableBeautifier;

import java.util.*;

import static com.twu.biblioteca.Constants.Constants.*;

public class Biblioteca {

    private List<Book> books;
    private List<Movie> movies;
    private User loggedUser;

    private ArrayList<User> validUsers = new ArrayList<User>(){{
        add(new User("123-4567", "foobar", "Dude", "dude@gmail.com", "5555-5555"));
        add(new User("999-9999", "barfoo", "Dudette", "dudette@gmail.com", "5555-6666", true));
    }};

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

    public User getLoggedUser() {
        return loggedUser;
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

    private String listMediasInfo(String[] header, List<? extends LibraryMedia> medias) {
        List<String[]> list = new ArrayList<>();
        list.add(header);

        for(LibraryMedia media : medias){

            String[] info = (String[]) media.getInfo();

            if(info != null){
                list.add(info);
            }
        }

        String[][] table = list.toArray(new String[list.size()][header.length]);

        return TableBeautifier.beautifyTable(table);
    }

    public String runUserCommand(String command) {

        if(SHOW_USER_INFO.equals(command)){
            return runShowUserInfoCoomand();
        }

        if(command.matches("^View Renter.*")){
            return runViewRenterCommand(command);
        }

        if(command.matches("^Login.*")){
            return runLoginCommand(command);
        }

        if(LOGOUT.equals(command)){
            return runLogoutCommand(command);
        }

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

    private String runViewRenterCommand(String command) {

        if(!loggedUser.isLibrarian()){
            return YOU_DONT_HAVE_PERMISSION_MESSAGE;
        }

        String bookName = command.replace(VIEW_RENTER + " ", "");

        Book book = findBookByName(bookName);
        if(book == null){
            return BOOK_NOT_FOUND_MESSAGE;
        }

        if(!book.isCheckedOut() || book.getRenter() == null){
            return BOOK_NOT_CHECKEDOUT_MESSAGE;
        }

        return book.getRenter();
    }

    private String runLogoutCommand(String command) {
        if (logout()) {
            return SUCCESSFUL_LOGOUT_MESSAGE;
        }

        return UNSUCCESSFUL_LOGOUT_MESSAGE;
    }

    private String runLoginCommand(String command) {
        String[] splitCommand = command.split(" ");

        if(splitCommand.length != 3){
            return INVALID_OPTION_MESSAGE;
        }

        String libraryNumber = splitCommand[1];
        String password = splitCommand[2];

        if(login(libraryNumber, password)) {
            return SUCCESSFUL_LOGIN_MESSAGE;
        }

        return UNSUCCESSFUL_LOGIN_MESSAGE;
    }

    private String runSpecificMediaCommand(String command) {
        String[] splitCommand = command.split(" ");
        String rootCommand = splitCommand[0];
        String mediaType = splitCommand[1];

        if (CHECKOUT.equals(rootCommand)){
            String mediaName = command.replace(CHECKOUT + " " + mediaType + " ", "");
            return runCheckOutCommand(mediaType, mediaName);
        }

        if (RETURN.equals(rootCommand)){
            String mediaName = command.replace(RETURN + " " + mediaType + " ", "");
            return runReturnCommand(mediaType, mediaName);
        }

        return INVALID_OPTION_MESSAGE;
    }

    private String runReturnCommand(String mediaType, String mediaName) {
        if (BOOK.equals(mediaType)){
            return runReturnBookCommand(mediaName);
        }

        if (MOVIE.equals(mediaType)){
            return runReturnMovieCommand(mediaName);
        }

        return INVALID_OPTION_MESSAGE;
    }

    private String runReturnMovieCommand(String movieName) {
        Movie movie = this.findMovieByName(movieName);

        if(movie == null || !movie.isCheckedOut()){
            return UNSUCCESSFUL_MOVIE_RETURN_MESSAGE;
        }

        movie.checkIn();

        return SUCCESSFUL_MOVIE_RETURN_MESSAGE;
    }

    private String runReturnBookCommand(String bookName) {
        Book book = this.findBookByName(bookName);

        if(book == null || !book.isCheckedOut()){
            return UNSUCCESSFUL_RETURN_MESSAGE;
        }

        book.checkIn();

        return SUCCESSFUL_RETURN_MESSAGE;
    }

    private String runCheckOutCommand(String mediaType, String mediaName) {
        if (BOOK.equals(mediaType)){
            return runCheckOutBookCommand(mediaName);
        }

        if (MOVIE.equals(mediaType)){
            return runCheckOutMovieCommand(mediaName);
        }

        return INVALID_OPTION_MESSAGE;
    }

    private String runCheckOutBookCommand(String bookName) {
        Book book = this.findBookByName(bookName);

        if(validateCheckout(book)){
            return UNSUCCESSFUL_CHECKOUT_MESSAGE;
        }

        book.checkOut(loggedUser.getLibraryNumber());

        return SUCCESSFUL_CHECKOUT_MESSAGE;
    }

    private String runCheckOutMovieCommand(String name) {
        Movie movie = this.findMovieByName(name);

        if(validateCheckout(movie)){
            return UNSUCCESSFUL_MOVIE_CHECKOUT_MESSAGE;
        }

        movie.checkOut(loggedUser.getLibraryNumber());

        return SUCCESSFUL_MOVIE_CHECKOUT_MESSAGE;
    }

    private boolean validateCheckout(LibraryMedia media) {
        return media == null || media.isCheckedOut() || loggedUser == null || loggedUser.isLibrarian();
    }

    private String runShowUserInfoCoomand() {
        if(loggedUser == null){
            return USER_NOT_LOGGED_MESSAGE;
        }

        return USER_HEADER + getLoggedUser().getInfo();
    }

    public String getMenu() {

        if (loggedUser == null){
            return LIST_OF_BOOKS + "\t\t" + LIST_OF_MOVIES + "\t\t" + LOG_IN + "\t\t" + QUIT;
        } else if(loggedUser.isLibrarian()){
            return LIST_OF_BOOKS + "\t\t" + LIST_OF_MOVIES + "\t\t" + VIEW_RENTER + "\t\t" + VIEW_USER_INFO + "\t\t" + LOGOUT + "\t\t" + QUIT;
        }
        return LIST_OF_BOOKS + "\t\t" + LIST_OF_MOVIES + "\t\t"+ CHECKOUT + "\t\t" + RETURN + "\t\t" + VIEW_USER_INFO + "\t\t" + LOGOUT + "\t\t" + QUIT;
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

    private User findUserByLibraryNumber(String libraryNumber) {
        return validUsers.stream().filter(user -> libraryNumber.equals(user.getLibraryNumber())).findFirst().orElse(null);
    }

    public boolean login(String libraryNumber, String password) {

        User user = findUserByLibraryNumber(libraryNumber);

        if(user == null || !password.equals(user.getPassword())){
            return false;
        }

        this.loggedUser = user;
        return true;
    }

    private boolean logout() {

        if(this.loggedUser == null){
            return false;
        }

        this.loggedUser = null;
        return true;
    }
}
