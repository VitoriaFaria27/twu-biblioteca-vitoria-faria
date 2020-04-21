package com.twu.biblioteca.model;

import com.twu.biblioteca.Abstract.LibraryMedia;

public class Book extends LibraryMedia {

    private String author;

    public Book(String name, String author, int year) {
        super(name, year);
        this.author = author;
    }

    public Object getInfo() {
        if(super.isCheckedOut()){
            return null;
        }

        return new String[] { super.name, this.author, String.valueOf(super.year) };
    }
}
