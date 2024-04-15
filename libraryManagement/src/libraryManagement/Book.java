package libraryManagement;

import java.util.*;

public class Book {
    private String title; // Title of the book
    private String author; // Author of the book
    private String genre; // Genre of the book
    private int publicationYear; // Year of the book was published
    private boolean status; // Availability of the book (true - if available, false - if not)

    // Constructor to initialize a Book 
    public Book(String title, String author, String genre, int publicationYear, boolean status) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.status = status;
    }

    // Method to get the title of the book
    public String getTitle() {
        return title;
    }

    // Method to get the author of the book
    public String getAuthor() {
        return author;
    }

    // Method to get the genre of the book
    public String getGenre() {
        return genre;
    }

    // Method to get the publication year of the book
    public int getPublicationYear() {
        return publicationYear;
    }

    // Method to get the availability status of the book
    public boolean getStatus() {
        return status;
    }

    // Method to set the availability status of the book
    public void setStatus(boolean status) {
        this.status = status;
    }
}
