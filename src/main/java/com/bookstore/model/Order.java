package src.main.java.com.bookstore.model;

import src.main.java.com.bookstore.exceptions.OrderException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

class OrderItem{
    private Book book;
    private int quantity;
    private double price;

    public OrderItem(Book book, int quantity, double price) {
        this.book = book;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "book=Title: " + book.getTitle() +
                ", Author: " + book.getAuthor() +
                ", Book Price: $" + book.getPrice() +
                ", quantity=" + quantity +
                '}';
    }
}

public class Order {
    private User user;
    private List<OrderItem> orderItems;
    private final LocalDateTime orderDateTime;

    public Order(User user) {
        this.user = user;
        this.orderItems = createOrderItems(user.getCart());
        this.orderDateTime = LocalDateTime.now();
    }

    public Order(User user, Book book, int quantity) {
        if(quantity>5){
            throw new OrderException("You can't add more than 5 copies of the same book");
        }
        if(quantity<=0){
            throw new OrderException("Quantity can't be 0 or less than 0");
        }
        this.user = user;
        this.orderItems = new ArrayList<>();
        this.orderItems.add(new OrderItem(book, quantity, book.getPrice()));
        this.orderDateTime = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }

    private List<OrderItem> createOrderItems(ShoppingCart cart) {
        List<OrderItem> orderItems = new ArrayList<>();
        HashMap<Book, Integer> books = cart.getBooks();
        for (Book book : books.keySet()) {
            orderItems.add(new OrderItem(book, books.get(book), book.getPrice()));
        }
        return orderItems;
    }

    public Double getTotalOrderValue(){
        return orderItems.stream()
                .mapToDouble(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                .sum();
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", orderItems=" + orderItems +
                ", orderDateTime=" + orderDateTime +
                ", Total Order Value=" + getTotalOrderValue() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(user, order.user) && Objects.equals(orderItems, order.orderItems) && Objects.equals(orderDateTime, order.orderDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, orderItems, orderDateTime);
    }
}
