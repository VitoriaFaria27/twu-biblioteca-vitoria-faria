package com.twu.biblioteca.Abstract;

public abstract class LibraryMedia {

    protected String name;
    protected int year;
    protected boolean checkedOut;
    protected String renter;

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

    public void checkOut(String renter) {
        this.renter = renter;
        this.checkedOut = true;
    }

    public void checkIn() {
        this.checkedOut = false;
    }

    public String getRenter(){
        return this.renter;
    }
}
