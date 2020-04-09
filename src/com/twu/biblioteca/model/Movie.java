package com.twu.biblioteca.model;

import com.twu.biblioteca.Abstract.LibraryMedia;

public class Movie extends LibraryMedia {

    private String director;
    private Integer rating;

    public Movie(String name, int year, String director, Integer rating) {
        super(name, year);
        this.director = director;
        this.rating = rating;
    }

    public Object getInfo() {
        if(super.checkedOut){
            return "";
        }
        return super.name + "\t" + super.year + "\t" + this.director + "\t" + ratingToString() + "\n";
    }

    private String ratingToString() {
        return this.rating == null ? "unrated" : rating.toString();
    }
}
