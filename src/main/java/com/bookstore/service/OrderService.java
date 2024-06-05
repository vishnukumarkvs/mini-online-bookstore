package src.main.java.com.bookstore.service;

import src.main.java.com.bookstore.model.Book;
import src.main.java.com.bookstore.model.Order;
import src.main.java.com.bookstore.model.User;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    public List<Order> orders;

    public OrderService() {
        orders = new ArrayList<>();
    }

    public void addOrder(User user) {
        orders.add(new Order(user));
        user.getCart().clearCart();
    }

    public void addOrder(User user, Book book, int quantity) {
        orders.add(new Order(user, book, quantity));
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Order> getOrdersByUser(User user) {
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getUser().getEmail().equals(user.getEmail())) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }
}
