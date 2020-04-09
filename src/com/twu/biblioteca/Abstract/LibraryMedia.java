package com.twu.biblioteca.Abstract;

public abstract class LibraryMedia {

    protected String name;
    protected int year;
    protected boolean checkedOut;

    public LibraryMedia(String name, int year) {
        this.name = name;
        this.year = year;
        this.checkedOut = false;
    }

    public String getName() {
        return name;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public abstract Object getInfo();

    public void checkOut() {
        this.checkedOut = true;
    }

    public void checkIn() {
        this.checkedOut = false;
    }
}
