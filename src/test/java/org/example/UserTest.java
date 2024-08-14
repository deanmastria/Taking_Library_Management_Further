package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class UserTest {

    // This test verifies that a book is correctly added to the user's list of books on loan.
    @Test
    void addBookOnLoan() {
        User user = new User("Jack Pops", "83738");  // Create a new User instance
        Book book = new Book("1984", "George Orwell", 1984, 328, "Dystopian");  // Create a new Book instance

        // Add the book to the user's loaned books list
        user.addBookOnLoan(book);

        // Assert that the user's loaned books list contains the added book
        assertTrue(user.getBooksOnLoan().contains(book), "The book should be added to the user's books on loan.");
    }

    // This test ensures that a book is correctly removed from the user's list of books on loan.
    @Test
    void testRemoveBookOnLoan() {
        User user = new User("Jack Pops", "13455");  // Create a new User instance
        Book book = new Book("1984", "George Orwell", 1949, 328, "Dystopian");  // Create a new Book instance

        // Add and then remove the book from the user's loaned books list
        user.addBookOnLoan(book);
        user.removeBookOnLoan(book);

        // Assert that the user's loaned books list no longer contains the removed book
        assertFalse(user.getBooksOnLoan().contains(book), "The book should be removed from the user's books on loan.");
    }

    // This test checks that the getBooksOnLoan method returns the correct list of books currently on loan to the user.
    @Test
    void testGetBooksOnLoan() {
        User user = new User("Jack Pops", "84948");  // Create a new User instance
        Book book1 = new Book("1984", "George Orwell", 1949, 328, "Dystopian");  // Create a new Book instance
        Book book2 = new Book("The Great Divide", "Jack Johnson", 1960, 281, "Fiction");  // Create another Book instance

        // Add two books to the user's loaned books list
        user.addBookOnLoan(book1);
        user.addBookOnLoan(book2);

        // Retrieve the list of books on loan
        List<Book> booksOnLoan = user.getBooksOnLoan();

        // Assert that the user's loaned books list contains exactly 2 books
        assertEquals(2, booksOnLoan.size(), "The user should have 2 books on loan.");
        // Assert that the list contains both added books
        assertTrue(booksOnLoan.contains(book1), "The list should contain '1984'.");
        assertTrue(booksOnLoan.contains(book2), "The list should contain 'The Great Divide'.");
    }

    // This test verifies that the getName method correctly returns the user's name.
    @Test
    void testGetName() {
        User user = new User("Jack Pops", "39458");  // Create a new User instance

        // Assert that the user's name is returned correctly
        assertEquals("Jack Pops", user.getName(), "The user's name should be 'Jack Pops'.");
    }

    // This test checks that the getLibraryCardNumber method returns the correct library card number.
    @Test
    void testGetLibraryCardNumber() {
        User user = new User("Jack Pops", "92783");  // Create a new User instance

        // Assert that the user's library card number is returned correctly
        assertEquals("92783", user.getLibraryCardNumber(), "The library card number should be '92783'.");
    }

    @Test
    void testSetName() {
        User user = new User("Jack Pops", "11111");  // Create a new User instance

        // Update the user's name
        user.setName("Jack Pops");

        // Assert that the user's name was updated correctly
        assertEquals("Jack Pops", user.getName(), "The user's name should be updated to 'Jack Pops'.");
    }

    @Test
    void testSetLibraryCardNumber() {
        User user = new User("Jack Pops", "11111");  // Create a new User instance

        // Update the user's library card number
        user.setLibraryCardNumber("67890");

        // Assert that the library card number was updated correctly
        assertEquals("67890", user.getLibraryCardNumber(), "The library card number should be updated to '67890'.");
    }

    @Test
    void testToString() {
        User user = new User("Jack Pops", "11111");  // Create a new User instance
        String expected = "User{name='Jack Pops', libraryCardNumber='11111', booksOnLoan=[]}";

        // Assert that the toString method returns the correct string representation
        assertEquals(expected, user.toString(), "The toString method should return the correct string representation.");
    }

    @Test
    void testAddMultipleBooksOnLoan() {
        User user = new User("Jack Pops", "83738");  // Create a new User instance
        Book book1 = new Book("1984", "George Orwell", 1949, 328, "Dystopian");  // Create a new Book instance
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 1960, 281, "Fiction");  // Create another Book instance

        // Add both books to the user's loaned books list
        user.addBookOnLoan(book1);
        user.addBookOnLoan(book2);

        // Assert that both books are in the user's loaned books list
        assertTrue(user.getBooksOnLoan().contains(book1), "The list should contain '1984'.");
        assertTrue(user.getBooksOnLoan().contains(book2), "The list should contain 'To Kill a Mockingbird'.");
    }

    @Test
    void testRemoveAllBooksOnLoan() {
        User user = new User("Jack Pops", "13455");  // Create a new User instance
        Book book1 = new Book("1984", "George Orwell", 1949, 328, "Dystopian");  // Create a new Book instance
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 1960, 281, "Fiction");  // Create another Book instance

        // Add both books to the user's loaned books list
        user.addBookOnLoan(book1);
        user.addBookOnLoan(book2);

        // Remove both books from the user's loaned books list
        user.removeBookOnLoan(book1);
        user.removeBookOnLoan(book2);

        // Assert that the user's loaned books list is empty
        assertTrue(user.getBooksOnLoan().isEmpty(), "The user's books on loan should be empty.");
    }




}
