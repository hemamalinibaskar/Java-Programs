package libraryManagement;

import java.util.ArrayList;
import java.util.List;

public class LibraryCatalog {
    private List<Book> books; // List to store all the books in catalog

    // Constructor to initialize an empty catalog
    public LibraryCatalog() {
        this.books = new ArrayList<>();
    }

    // Method to add a book to the catalog
    public void addBook(Book book) {
        books.add(book);
    }

 // Method to search for books by title or author
    public List<Book> searchByTitleOrAuthor(String query) {
        List<Book> matchingBooks = new ArrayList<>();

        // Convert the query to lowercase for case-insensitive matching
        query = query.toLowerCase();

        // Iterate through each book in the catalog
        for (Book book : books) {
            // Check if the title or author of the book matches the query (case-insensitive)
            if (book.getTitle().equalsIgnoreCase(query) || book.getAuthor().equalsIgnoreCase(query)) {
                matchingBooks.add(book); // Add matching book to the list
            }
        }

        // If no matching books found, check if the book is checked out or not found
        if (matchingBooks.isEmpty()) {
            boolean bookFound = false;
            for (Book book : books) {
                // Check if the title of the book matches the query (case-insensitive)
                if (book.getTitle().equalsIgnoreCase(query)) {
                    bookFound = true;
                    if (book.getStatus()) {
                        System.out.println("The book is already checked out.");
                    } else {
                        System.out.println("The book is not available.");
                    }
                    break;
                }
            }
            if (!bookFound) {
                System.out.println("The book is not found.");
            }
        }

        return matchingBooks; // Return list of matching books
    }


    // Method to check out a book
    public boolean checkOut(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (!book.getStatus()) {
                    book.setStatus(true); // Set the status of the book to checked out
                    return true; // Return true if the book was successfully checked out
                } else {
                    return false; // Return false if the book is already checked out
                }
            }
        }
        return false; // Return false if the book with the given title is not found
    }

    // Method to return a book 
    public boolean returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.getStatus()) {
                    book.setStatus(false); // Set the status of the book to available
                    return true; // Return true if the book was successfully returned
                } else {
                    return false; // Return false if the book is already available
                }
            }
        }
        return false; // Return false if the book with the given title is not found
    }

    // Method to get a list of available books
    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (!book.getStatus()) {
                availableBooks.add(book); // Add available book to the list
            }
        }
        return availableBooks; // Return list of available books
    }
}
