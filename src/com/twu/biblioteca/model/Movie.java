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
        if(super.isCheckedOut()){
            return null;
        }

        return new String[] { super.name, String.valueOf(super.year), this.director, ratingToString() };
    }

    private String ratingToString() {
        return this.rating == null ? "unrated" : rating.toString();
    }
}
