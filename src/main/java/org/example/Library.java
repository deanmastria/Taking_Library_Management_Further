package org.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The Library class manages a collection of books and registered users.
 */
public class Library {
    private List<Book> books;
    private List<User> users;

    /**
     * Constructor to initialize a Library object.
     */
    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    /**
     * Adds a book to the library's collection.
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Removes a book from the library by its title.
     */
    public void removeBook(String title) {
        Predicate<Book> titlePredicate = book -> book.getTitle().equalsIgnoreCase(title);
        books.removeIf(titlePredicate);
    }

    /**
     * Finds all books published in a specific year.
     */
    public List<Book> findBooksByYear(int year) {
        Predicate<Book> yearPredicate = book -> book.getPublicationYear() == year;
        return books.stream()
                .filter(yearPredicate)
                .collect(Collectors.toList());
    }

    /**
     * Finds all books by a specific author.
     */
    public List<Book> findBooksByAuthor(String author) {
        Predicate<Book> authorPredicate = book -> book.getAuthor().equalsIgnoreCase(author);
        return books.stream()
                .filter(authorPredicate)
                .collect(Collectors.toList());
    }

    /**
     * Finds the book with the most pages.
     */
    public Optional<Book> findBookWithMostPages() {
        return books.stream()
                .max(Comparator.comparingInt(Book::getPages));
    }

    /**
     * Finds all books with more than a specified number of pages.
     */
    public List<Book> findBooksWithMoreThanNPages(int n) {
        Predicate<Book> pagesPredicate = book -> book.getPages() > n;
        return books.stream()
                .filter(pagesPredicate)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all book titles in the library, sorted alphabetically.
     */
    public List<String> getAllBookTitles() {
        Function<Book, String> titleFunction = Book::getTitle;
        return books.stream()
                .map(titleFunction)
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Finds all books in a specific category.
     */
    public List<Book> findBooksByCategory(String category) {
        Predicate<Book> categoryPredicate = book -> book.getCategory().equalsIgnoreCase(category);
        return books.stream()
                .filter(categoryPredicate)
                .collect(Collectors.toList());
    }

    /**
     * Loans out a book to a user if the book is not already on loan.
     */
    public boolean loanBook(String title, String userName) {
        Predicate<Book> bookPredicate = book -> book.getTitle().equalsIgnoreCase(title) && !book.isOnLoan();
        Optional<Book> bookOpt = books.stream()
                .filter(bookPredicate)
                .findFirst();

        Predicate<User> userPredicate = user -> user.getName().equalsIgnoreCase(userName);
        Optional<User> userOpt = users.stream()
                .filter(userPredicate)
                .findFirst();

        if (bookOpt.isPresent() && userOpt.isPresent()) {
            Book book = bookOpt.get();
            User user = userOpt.get();
            book.loanBook();
            user.addBookOnLoan(book);
            return true;
        }
        return false;
    }

    /**
     * Returns a book from a user if the book is currently on loan.
     */
    public boolean returnBook(String title, String userName) {
        Predicate<Book> bookPredicate = book -> book.getTitle().equalsIgnoreCase(title) && book.isOnLoan();
        Optional<Book> bookOpt = books.stream()
                .filter(bookPredicate)
                .findFirst();

        Predicate<User> userPredicate = user -> user.getName().equalsIgnoreCase(userName);
        Optional<User> userOpt = users.stream()
                .filter(userPredicate)
                .findFirst();

        if (bookOpt.isPresent() && userOpt.isPresent()) {
            Book book = bookOpt.get();
            User user = userOpt.get();
            book.returnBook();
            user.removeBookOnLoan(book);
            return true;
        }
        return false;
    }

    /**
     * Registers a new user with the library.
     */
    public void registerUser(User user) {
        users.add(user);
    }

    // Calculates late fees for a user based on overdue books.
    public double calculateLateFees(User user) {
        double lateFee = 0.00;
        Predicate<Book> overduePredicate = book -> {
            LocalDate loanDate = book.getLoanDate();
            return loanDate != null && ChronoUnit.DAYS.between(loanDate, LocalDate.now()) > 14;
        };
        Function<Book, Long> overdueDaysFunction = book -> ChronoUnit.DAYS.between(book.getLoanDate().plusDays(14), LocalDate.now());

        for (Book book : user.getBooksOnLoan()) {
            if (overduePredicate.test(book)) {
                long overdueDays = overdueDaysFunction.apply(book);
                lateFee += overdueDays * 0.50; // Assuming the fee is $0.50 per day
            }
        }
        return lateFee;
    }

    // Processes each book in the library using a Consumer.
    public void processBooks(Consumer<Book> bookConsumer) {
        books.forEach(bookConsumer);
    }
}



