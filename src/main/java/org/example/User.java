package org.example;

import java.util.ArrayList;
import java.util.List;

// The User class represents a library user with a name, library card number, and list of books on loan.
public class User {
    private String name;
    private String libraryCardNumber;
    private List<Book> booksOnLoan;

    //* Constructor to initialize a User object.

    public User(String name, String libraryCardNumber) {
        this.name = name;
        this.libraryCardNumber = libraryCardNumber;
        this.booksOnLoan = new ArrayList<>();
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public void setLibraryCardNumber(String libraryCardNumber) {
        this.libraryCardNumber = libraryCardNumber;
    }

    public List<Book> getBooksOnLoan() {
        return booksOnLoan;
    }

    //Adds a book to the list of books on loan.
    public void addBookOnLoan(Book book) {
        booksOnLoan.add(book);
    }

    // Removes a book from the list of books on loan.
    public void removeBookOnLoan(Book book) {
        booksOnLoan.remove(book);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", libraryCardNumber='" + libraryCardNumber + '\'' +
                ", booksOnLoan=" + booksOnLoan +
                '}';
    }
}


