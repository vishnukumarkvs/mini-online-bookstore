package src.main.java.com.bookstore.service;

import src.main.java.com.bookstore.model.*;
import src.main.java.com.bookstore.exceptions.BookException;
import java.util.ArrayList;
import java.util.List;

public class BookstoreService {
    private List<Book> books;

    public BookstoreService() {
        books = new ArrayList<>();
    }

    public void addBook(String title, String author, double price, int availableCopies) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                throw new BookException("Book with title " + title + " already exists");
            }
        }
        books.add(new Book(title, author, price, availableCopies));
    }

    public List<Book> getBooks() {
        return books;
    }


    public Book findBookByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new BookException("Book with title " + title + " not found"));
    }

    //find book by author
    public Book findBookByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .findFirst()
                .orElseThrow(() -> new BookException("Book with author " + author + " not found"));
    }

}
