package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public class LibraryTest {

    // This test verifies that a book is correctly added to the library's collection.
    @Test
    void testAddBook() {
        Library library = new Library();  // Create a new Library instance
        Book book = new Book("1984", "George Orwell", 1949, 328, "Dystopian");  // Create a new Book instance
        library.addBook(book);  // Add the book to the library

        // Assert that the book title is in the list of all book titles in the library
        assertTrue(library.getAllBookTitles().contains("1984"), "Book should be added to the library.");
    }

    // This test ensures that a book is correctly removed from the library's collection by its title.
    @Test
    void testRemoveBook() {
        Library library = new Library();  // Create a new Library instance
        Book book = new Book("1984", "George Orwell", 1949, 328, "Dystopian");  // Create a new Book instance
        library.addBook(book);  // Add the book to the library
        library.removeBook("1984");  // Remove the book from the library by its title

        // Assert that the book title is no longer in the list of all book titles in the library
        assertFalse(library.getAllBookTitles().contains("1984"), "Book should be removed from the library.");
    }

    // This test checks that the method correctly finds and returns all books by a specific author.
    @Test
    void testFindBooksByAuthor() {
        Library library = new Library();  // Create a new Library instance
        Book book1 = new Book("1984", "George Orwell", 1949, 328, "Dystopian");  // Create a new Book instance
        Book book2 = new Book("Animal Farm", "George Orwell", 1945, 112, "Allegory");  // Create another Book instance
        library.addBook(book1);  // Add the first book to the library
        library.addBook(book2);  // Add the second book to the library

        // Find all books by "George Orwell"
        List<Book> orwellBooks = library.findBooksByAuthor("George Orwell");

        // Assert that the number of books found by George Orwell is 2
        assertEquals(2, orwellBooks.size(), "There should be 2 books by George Orwell");
    }

    // This test verifies that a book can be loaned out successfully, updating both the book's loan status and the user's loaned books list.
    @Test
    void testLoanBook() {
        Library library = new Library();  // Create a new Library instance
        Book book = new Book("1984", "George Orwell", 1949, 328, "Dystopian");  // Create a new Book instance
        User user = new User("Jack Pops", "09809");  // Create a new User instance
        library.addBook(book);  // Add the book to the library
        library.registerUser(user);  // Register the user in the library

        // Attempt to loan the book to the user
        boolean loaned = library.loanBook("1984", "Jack Pops");

        // Assert that the book was successfully loaned, is marked as on loan, and is in the user's loaned books list
        assertTrue(loaned, "Book should be loaned.");
        assertTrue(book.isOnLoan(), "This book should be marked as on loan");
        assertTrue(user.getBooksOnLoan().contains(book), "Book should be loaned.");
    }

    // This test ensures that a book can be returned successfully, updating both the book's loan status and the user's loaned books list.
    @Test
    void testReturnBook() {
        Library library = new Library();  // Create a new Library instance
        Book book = new Book("1984", "George Orwell", 1949, 328, "Dystopian");  // Create a new Book instance
        User user = new User("Jack Pops", "09809");  // Create a new User instance
        library.addBook(book);  // Add the book to the library
        library.registerUser(user);  // Register the user in the library
        library.loanBook("1984", "Jack Pops");  // Loan the book to the user

        // Attempt to return the book
        boolean returned = library.returnBook("1984", "Jack Pops");

        // Assert that the book was successfully returned, is no longer marked as on loan, and is removed from the user's loaned books list
        assertTrue(returned, "Book should be returned successfully.");
        assertFalse(book.isOnLoan(), "This book should be marked as not on loan");
        assertFalse(user.getBooksOnLoan().contains(book), "The user's list should no longer contain the returned book.");
    }

    @Test
    void testFindBooksWithMoreThanNPages() {
        Library library = new Library();  // Create a new Library instance
        Book book1 = new Book("1984", "George Orwell", 1949, 328, "Dystopian");  // Create a new Book instance
        Book book2 = new Book("Animal Farm", "George Orwell", 1945, 112, "Allegory");  // Create another Book instance
        library.addBook(book1);  // Add the first book to the library
        library.addBook(book2);  // Add the second book to the library

        // Find all books with more than 200 pages
        List<Book> booksWithMoreThan200Pages = library.findBooksWithMoreThanNPages(200);

        // Assert that only one book is found, and it's the correct one
        assertEquals(1, booksWithMoreThan200Pages.size(), "There should be 1 book with more than 200 pages.");
        assertTrue(booksWithMoreThan200Pages.contains(book1), "The list should contain '1984'.");
    }

    @Test
    void testFindBookWithMostPages() {
        Library library = new Library();  // Create a new Library instance
        Book book1 = new Book("1984", "George Orwell", 1949, 328, "Dystopian");  // Create a new Book instance
        Book book2 = new Book("War and Peace", "Leo Tolstoy", 1869, 1225, "Historical Fiction");  // Create another Book instance
        library.addBook(book1);  // Add the first book to the library
        library.addBook(book2);  // Add the second book to the library

        // Find the book with the most pages
        Optional<Book> bookWithMostPages = library.findBookWithMostPages();

        // Assert that the book with the most pages is found, and it's the correct one
        assertTrue(bookWithMostPages.isPresent(), "There should be a book with the most pages.");
        assertEquals(book2, bookWithMostPages.get(), "The book with the most pages should be 'War and Peace'.");
    }

    @Test
    void testGetAllBookTitles() {
        Library library = new Library();  // Create a new Library instance
        Book book1 = new Book("1984", "George Orwell", 1949, 328, "Dystopian");  // Create a new Book instance
        Book book2 = new Book("Animal Farm", "George Orwell", 1945, 112, "Allegory");  // Create another Book instance
        Book book3 = new Book("Brave New World", "Aldous Huxley", 1932, 268, "Dystopian");  // Create another Book instance
        library.addBook(book1);  // Add the first book to the library
        library.addBook(book2);  // Add the second book to the library
        library.addBook(book3);  // Add the third book to the library

        // Retrieve all book titles
        List<String> titles = library.getAllBookTitles();

        // Assert that the titles are returned in alphabetical order
        assertEquals("1984", titles.get(0), "First title should be '1984'.");
        assertEquals("Animal Farm", titles.get(1), "Second title should be 'Animal Farm'.");
        assertEquals("Brave New World", titles.get(2), "Third title should be 'Brave New World'.");
    }

    @Test
    void testFindBooksByCategory() {
        Library library = new Library();  // Create a new Library instance
        Book book1 = new Book("1984", "George Orwell", 1949, 328, "Dystopian");  // Create a new Book instance
        Book book2 = new Book("Brave New World", "Aldous Huxley", 1932, 268, "Dystopian");  // Create another Book instance
        Book book3 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 180, "Fiction");  // Create another Book instance
        library.addBook(book1);  // Add the first book to the library
        library.addBook(book2);  // Add the second book to the library
        library.addBook(book3);  // Add the third book to the library

        // Find all books in the 'Dystopian' category
        List<Book> dystopianBooks = library.findBooksByCategory("Dystopian");

        // Assert that the correct books are found
        assertEquals(2, dystopianBooks.size(), "There should be 2 books in the 'Dystopian' category.");
        assertTrue(dystopianBooks.contains(book1), "The list should contain '1984'.");
        assertTrue(dystopianBooks.contains(book2), "The list should contain 'Brave New World'.");
    }

    @Test
    void testCalculateLateFees() {
        Library library = new Library();  // Create a new Library instance
        Book book = new Book("1984", "George Orwell", 1949, 328, "Dystopian");  // Create a new Book instance
        User user = new User("Jack Pops", "12345");  // Create a new User instance
        library.addBook(book);  // Add the book to the library
        library.registerUser(user);  // Register the user in the library
        library.loanBook("1984", "Jack Pops");  // Loan the book to the user

        // Simulate a late return by manually setting the loan date to 20 days ago
        book.setLoanDate(LocalDate.now().minusDays(20));

        // Calculate late fees
        double lateFees = library.calculateLateFees(user);

        // Assert that the late fees are calculated correctly (6 days overdue * $0.50/day)
        assertEquals(3.00, lateFees, 0.01, "Late fees should be $3.00.");
    }
}
