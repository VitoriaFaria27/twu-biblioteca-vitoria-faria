package com.twu.biblioteca.model;

public class User {

    private String libraryNumber;
    private String password;
    private boolean isLibrarian;

    public User(String libraryNumber, String password, boolean isLibrarian) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.isLibrarian = isLibrarian;
    }

    public User(String libraryNumber, String password) {
        this(libraryNumber, password, false);
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLibrarian() {
        return isLibrarian;
    }
}
