package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Library library = createLibraryWithBooks();
        testLibraryOperations(library);
    }

    private static Library createLibraryWithBooks() {
        Library library = new Library();

        // Create and add books to the library
        Book book1 = new Book("The Hobbit", "J.R.R. Tolkien", 1937, 310, "Fantasy");
        Book book2 = new Book("Where the Wild Things Are", "Maurice Sendak", 1963, 48, "Children's");
        Book book3 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 180, "Fiction");
        Book book4 = new Book("Aliens", "Jeremy Guy", 1970, 400, "Fiction");
        Book book5 = new Book("Whales and the Oceans", "F. Willy", 1999, 300, "Non-Fiction");
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);

        return library;
    }

    private static void testLibraryOperations(Library library) {
        // Test adding books
        System.out.println("Books after adding:");
        library.getAllBookTitles().forEach(System.out::println);

        // Test removing a book by title
        library.removeBook("The Hobbit");
        System.out.println("\nBooks after removing 'The Hobbit':");
        library.getAllBookTitles().forEach(System.out::println);

        // Test finding books by year
        List<Book> booksPublishedIn1963 = library.findBooksByYear(1963);
        System.out.println("\nBooks published in 1963:");
        booksPublishedIn1963.forEach(System.out::println);

        // Register users
        User user1 = new User("Deano", "12345");
        User user2 = new User("Devon", "67890");
        library.registerUser(user1);
        library.registerUser(user2);

        // Loan out a book
        System.out.println("\nLoan out 'Where the Wild Things Are' to Deano: " + library.loanBook("Where the Wild Things Are", "Deano"));

        // Manually set the loan date to simulate a late return
        simulateLateReturn(user1);

        // Calculate late fees for user
        double lateFee = library.calculateLateFees(user1);
        System.out.printf("\nLate fees for Deano: $%.2f%n", lateFee);

        // Return the book to remove late fee charges
        System.out.println("\nReturn 'Where the Wild Things Are' from Deano: " + library.returnBook("Where the Wild Things Are", "Deano"));

        // Attempt to loan the same book again
        System.out.println("\nAttempt to loan 'Where the Wild Things Are' again to Devon: " + library.loanBook("Where the Wild Things Are", "Devon"));

        // Find books by author
        System.out.println("\nBooks by Maurice Sendak:");
        List<Book> sendakBooks = library.findBooksByAuthor("Maurice Sendak");
        sendakBooks.forEach(System.out::println);

        // Get all book titles sorted alphabetically
        System.out.println("\nAll book titles sorted alphabetically:");
        List<String> titles = library.getAllBookTitles();
        titles.forEach(System.out::println);

        // Find books by category
        System.out.println("\nBooks in Fiction category:");
        List<Book> fictionBooks = library.findBooksByCategory("Fiction");
        fictionBooks.forEach(System.out::println);

        // Find book with most pages
        System.out.println("\nBook with most pages:");
        library.findBookWithMostPages().ifPresent(System.out::println);

        // Find books with more than 200 pages
        System.out.println("\nBooks with more than 200 pages:");
        List<Book> booksWithMoreThan200Pages = library.findBooksWithMoreThanNPages(200);
        booksWithMoreThan200Pages.forEach(System.out::println);

        // Process each book with a Consumer
        System.out.println("\nProcessing each book to print its details:");
        Consumer<Book> printBookDetails = System.out::println;
        library.processBooks(printBookDetails);
    }

    private static void simulateLateReturn(User user) {
        for (Book book : user.getBooksOnLoan()) {
            if (book.getTitle().equals("Where the Wild Things Are")) {
                book.setLoanDate(LocalDate.now().minusDays(20));  // Loaned out 20 days ago
            }
        }
    }
}


