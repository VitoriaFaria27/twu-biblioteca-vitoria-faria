package com.twu.biblioteca.Abstract;

public abstract class LibraryMedia {

    protected String name;
    protected int year;
    protected String renter;

    public LibraryMedia(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public boolean isCheckedOut() {
        return renter != null;
    }

    public abstract Object getInfo();

    public void checkOut(String renter) {
        this.renter = renter;
    }

    public void checkIn() {
        this.renter = null;
    }

    public String getRenter(){
        return this.renter;
    }
}
