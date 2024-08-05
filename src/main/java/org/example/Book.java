package org.example;

import java.time.LocalDate;

/**
 * The Book class represents a book with its details and loan status.
 */
public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private int pages;
    private String category;
    private boolean isOnLoan;
    private LocalDate loanDate;

    /**
     * Constructor to initialize a Book object.
     */
    public Book(String title, String author, int publicationYear, int pages, String category) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.pages = pages;
        this.category = category;
        this.isOnLoan = false;
        this.loanDate = null;
    }

    // Getter and Setter methods
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isOnLoan() {
        return isOnLoan;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    /**
     * Marks the book as on loan and sets the loan date to today.
     */
    public void loanBook() {
        this.isOnLoan = true;
        this.loanDate = LocalDate.now();
    }

    /**
     * Marks the book as returned and clears the loan date.
     */
    public void returnBook() {
        this.isOnLoan = false;
        this.loanDate = null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", pages=" + pages +
                ", category='" + category + '\'' +
                ", isOnLoan=" + isOnLoan +
                ", loanDate=" + loanDate +
                '}';
    }
}


