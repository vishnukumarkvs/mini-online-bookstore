package src.main.java.com.bookstore.model;

import src.main.java.com.bookstore.exceptions.InvalidInputException;

import java.util.Objects;

public class Book {

    private final String title;
    private final String author;
    private double price;
    private int availableCopies;

    public Book(String title, String author, double price, int availableCopies) {
        if (availableCopies < 0) {
            throw new InvalidInputException("Available copies must be non-negative");
        }
        if (price < 0) {
            throw new InvalidInputException("Price must be non-negative");
        }
        this.price = price;
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public void updateAvailability(int num) {
        if (this.availableCopies + num < 0) {
            throw new InvalidInputException("Invalid number of books to add or remove");
        }
        this.availableCopies += num;
    }

    public boolean isAvailable() {
        return availableCopies > 0;
    }

    public void displayDetails() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: $" + price);
        System.out.println("Available Copies: " + availableCopies);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Price: $" + price + ", Available Copies: " + availableCopies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }
}
