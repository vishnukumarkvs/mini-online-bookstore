package src.main.java.com.bookstore.service;

import src.main.java.com.bookstore.model.Book;
import src.main.java.com.bookstore.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
    }

    public void registerUser(String name, String email, String password) {
        if(
            users.stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email))
        ) {
            throw new IllegalArgumentException("User with email " + email + " already exists");
        }

        users.add(new User(name, email, password));
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " not found"));
    }

    public void updateUserCart(String email, Book book, int quantity) {
        User user = getUserByEmail(email);
        user.getCart().updateCart(book,quantity);
    }
}
