package libraryManagement;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();

        // Add initial books to the library catalog
        addInitialBooks(catalog);

        // Display welcome message
        System.out.println("--------------------------------------------------------");
        System.out.println("|    Welcome to BookHUB Library Management System:-    |");
        System.out.println("--------------------------------------------------------");

        // User registration
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        userManager.registerUser(username, password);

        boolean exit = false;

        // Main menu loop
        while (!exit) {
            System.out.println("\nBookHUB Library Management System");
            System.out.println("---------------------------------");
            System.out.println("1. Search for Books");
            System.out.println("2. Check Out a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Add a New Book");
            System.out.println("5. Display Available Books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer choice.");
                continue;
            }

            // Switch case for user choices
            switch (choice) {
                case 1:
                    // Search for books
                    System.out.print("\nEnter the Title or Author: ");
                    String query = scanner.nextLine().toLowerCase();
                    System.out.println("\nMatching Books:");
                    if (!query.isEmpty()) {
                        List<Book> matchingBooks = catalog.searchByTitleOrAuthor(query);
                        if (!matchingBooks.isEmpty()) {
                            matchingBooks.forEach(book -> System.out.println("\nTitle: " + book.getTitle() + "\nAuthor: " + book.getAuthor() + "\nGenre: " + book.getGenre() + "\nPublication Year: " + book.getPublicationYear()));
                        } else {
                            System.out.println("No matching books found.");
                        }
                    } else {
                        System.out.println("Invalid input! Enter the valid input.");
                    }
                    break;

                case 2:
                    // Check out a book
                    System.out.print("Enter the title of the book: ");
                    String checkoutTitle = scanner.nextLine().trim();
                    System.out.print("Enter the author's name: ");
                    String checkoutAuthor = scanner.nextLine().trim();
                    if (!checkoutTitle.isEmpty() && !checkoutAuthor.isEmpty()) {
                        List<Book> matchingBooks = catalog.searchByTitleOrAuthor(checkoutTitle);
                        boolean bookFound = false;
                        for (Book book : matchingBooks) {
                            if (book.getAuthor().equalsIgnoreCase(checkoutAuthor)) {
                                bookFound = true;
                                if (catalog.checkOut(book.getTitle())) {
                                    System.out.println("Book checked out Successfully!!!");
                                } else {
                                    System.out.println("Book is already checked out.");
                                }
                                break;
                            }
                        }
                        if (!bookFound) {
                            System.out.println("Book not found.");
                        }
                    } else {
                        System.out.println("Invalid input! Enter the valid book title and author's name.");
                    }
                    break;

                case 3:
                    // Return a book
                    System.out.print("Enter the title of the book to return: ");
                    String returnTitle = scanner.nextLine().trim();
                    System.out.print("Enter the author's name of the book to return: ");
                    String returnAuthor = scanner.nextLine().trim();
                    if (!returnTitle.isEmpty() && !returnAuthor.isEmpty()) {
                        List<Book> matchingBooks = catalog.searchByTitleOrAuthor(returnTitle);
                        boolean bookFound = false;
                        for (Book book : matchingBooks) {
                            if (book.getAuthor().equalsIgnoreCase(returnAuthor)) {
                                bookFound = true;
                                if (catalog.returnBook(book.getTitle())) {
                                    System.out.println("Book returned Successfully!!!");
                                } else {
                                    System.out.println("Book is already available.");
                                }
                                break;
                            }
                        }
                        if (!bookFound) {
                            System.out.println("Book not found.");
                        }
                    } else {
                        System.out.println("Invalid input! Enter the valid book title and author's name.");
                    }
                    break;

                case 4:
                    // Add a new book
                    System.out.print("Enter the title of the new book: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter the author of the new book: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter the genre of the new book: ");
                    String newGenre = scanner.nextLine();
                    System.out.print("Enter the Publication year of the new book: ");
                    int newPublicationYear;
                    try {
                        newPublicationYear = Integer.parseInt(scanner.nextLine());
                        if (newPublicationYear < 0) {
                            System.out.println("Invalid input! Publication year must be a positive integer.");
                            break;
                        }
                        catalog.addBook(new Book(newTitle, newAuthor, newGenre, newPublicationYear, false));
                        System.out.println("New book added successfully!");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter a valid integer for the publication year.");
                    }
                    break;

                case 5:
                    // Display available books
                    System.out.println("\nAvailable Books:");
                    catalog.getAvailableBooks().forEach(book -> System.out.println("\nBook name: " + book.getTitle() + "    Author name: " + book.getAuthor()));
                    break;

                case 6:
                    // Exit the program
                    exit = true;
                    System.out.println("Thank you for using BookHUB Library");
                    break;

                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }

        scanner.close(); // Close the scanner object
    }

    // Method to add initial books to the library catalog
    public static void addInitialBooks(LibraryCatalog catalog) {
        // Adding books with additional details
        catalog.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Classic", 1925, false));
        catalog.addBook(new Book("Clean Code", "Robert C. Martin", "Programming", 2008, true));
        catalog.addBook(new Book("The Pragmatic Programmer", "Andrew Hunt and David Thomas", "Programming", 1999, false));
        catalog.addBook(new Book("Code Complete", "Steve McConnell", "Programming", 1993, false));
        catalog.addBook(new Book("Effective Java", "Joshua Bloch", "Programming", 2001, false));
        catalog.addBook(new Book("Refactoring", "Martin Fowler", "Programming", 1999, true));
        catalog.addBook(new Book("Cracking the Coding Interview", "Gayle Laakmann McDowell", "Programming", 2015, true));
        catalog.addBook(new Book("Head First Java", "Kathy Sierra and Bert Bates", "Programming", 2005, false));
        catalog.addBook(new Book("Programming Python", "Mark Lutz", "Programming", 2013, true));
        catalog.addBook(new Book("Domain-Driven Design", "Eric Evans", "Programming", 2003, false));
        catalog.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction", 1960, false));
        catalog.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy", 1937, false));
        catalog.addBook(new Book("1984", "George Orwell", "Dystopian", 1949, false));
        catalog.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", "Literary Fiction", 1951, false));
        catalog.addBook(new Book("Pride and Prejudice", "Jane Austen", "Romance", 1813, false));
    }
}
