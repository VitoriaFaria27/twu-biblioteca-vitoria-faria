package com.twu.biblioteca.model;

public class Book {

    private String name;
    private String author;
    private int year;
    private boolean checkedOut;

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.checkedOut = false;
    }

    public String getName() {
        return name;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public Object getInfo() {
        if(this.checkedOut){
            return "";
        }
        return this.name + "\t" + this.author + "\t" + this.year + "\n";
    }

    public void checkOut() {
        this.checkedOut = true;
    }

    public void checkIn() {
        this.checkedOut = false;
    }
}
