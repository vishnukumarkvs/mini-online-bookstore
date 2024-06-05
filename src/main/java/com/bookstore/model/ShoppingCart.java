package src.main.java.com.bookstore.model;

import src.main.java.com.bookstore.exceptions.CartException;

import java.util.HashMap;

public class ShoppingCart {
    private HashMap<Book, Integer> books;

    public ShoppingCart() {
        books = new HashMap<>();
    }

    public void updateCart(Book book, int quantity) {
//        if(quantity<0){
//            throw new CartException("Quantity must be greater than 0");
//        }
        if(quantity>5){
            throw new CartException("You can't add more than 5 copies of the same book");
        }
        if(quantity==0){
            removeBook(book);
            return;
        }
        int currentCopies = book.getAvailableCopies();
        if (currentCopies < quantity) {
            throw new CartException("Not enough copies available");
        }

        book.updateAvailability(-quantity);
        books.put(book, books.getOrDefault(book, 0) + quantity);
    }

    public void removeBook(Book book) {
        if (!books.containsKey(book)) {
            throw new CartException("Book not found in the shopping cart");
        }
        book.updateAvailability(books.get(book));
        books.remove(book);
    }

    public HashMap<Book, Integer> getBooks() {
        return books;
    }

    public void clearCart() {
        books.clear();
    }

    public void displayCart() {
        for (Book book : books.keySet()) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Price: $" + book.getPrice());
            System.out.println("Quantity: " + books.get(book));
        }
    }
}
