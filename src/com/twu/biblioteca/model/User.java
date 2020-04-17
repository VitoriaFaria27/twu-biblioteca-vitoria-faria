package com.twu.biblioteca.model;

public class User {

    private String libraryNumber;
    private String password;
    private boolean isLibrarian;
    private String name;
    private String email;
    private String phoneNumber;

    public User(String libraryNumber, String password, String name, String email, String phoneNumber, boolean isLibrarian) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.isLibrarian = isLibrarian;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User(String libraryNumber, String password, String name, String email, String phoneNumber) {
        this(libraryNumber, password, name, email, phoneNumber,false);
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

    public Object getInfo() {
        return this.name + "\t\t" + this.email + "\t\t" + this.phoneNumber + "\n";
    }
}
