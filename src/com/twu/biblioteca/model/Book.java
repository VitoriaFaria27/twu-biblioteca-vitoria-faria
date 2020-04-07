package com.twu.biblioteca.model;

public class Book {

    private String name;
    private String author;
    private int year;

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public Object getInfo() {
        return this.name + "\t" + this.author + "\t" + this.year + "\n";
    }
}
