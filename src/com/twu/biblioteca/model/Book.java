package com.twu.biblioteca.model;

import com.twu.biblioteca.Abstract.LibraryMedia;

public class Book extends LibraryMedia {

    private String author;

    public Book(String name, String author, int year) {
        super(name, year);
        this.author = author;
    }

    public Object getInfo() {
        if(super.checkedOut){
            return "";
        }
        return super.name + "\t\t" + this.author + "\t\t" + super.year + "\n";
    }
}
