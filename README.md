# Mini Online Bookstore System

## Overview

This project is a simplified version of an online bookstore system implemented in Java. It includes functionalities for managing books, users, and orders. The system demonstrates basic object-oriented principles such as encapsulation and inheritance and handles error cases using custom exception handling.

## Features

1. **Book Management:**
    - Add a new book to the bookstore inventory.
    - Display the list of available books.

2. **User Management:**
    - Register a new user.
    - Display the list of registered users.

3. **Order Management:**
    - Allow users to add books to their shopping cart.
    - Allow users to place an order.
    - Calculate the total cost of the order.
    - List all orders placed.

## Project Structure
```dtd
.
└── main
    └── java
        └── com
            └── bookstore
                ├── BookstoreApplication.java
                ├── exceptions
                │   ├── BookException.java
                │   ├── CartException.java
                │   ├── InvalidInputException.java
                │   └── OrderException.java
                ├── model
                │   ├── Book.java
                │   ├── Order.java
                │   ├── ShoppingCart.java
                │   └── User.java
                └── service
                    ├── BookstoreService.java
                    ├── OrderService.java
                    └── UserService.java

```

## Classes
 1. **Book**: Represents a book with attributes like title, author, price, and available copies.
 2. **User** : Represents a user with attributes like name, email, and password.
 3. **ShoppingCart** : Manages a list of order items added by the user and calculates the total cost.
 4. **OrderItem** : Represents an item in an order, with a book and quantity.
 5. **Order** : Represents an order placed by a user, containing order items and the order date.
 6. **BookstoreService** : Manages the collection of books, users, and orders, and handles business logic.
 7. **UserService** : Manages user registration and user-specific operations.
 8. **OrderService** : Manages the orders placed by users.
 9. **InvalidInputException** : Custom exception for invalid input scenarios.
 10. **BookException** : Custom exception for book-related scenarios.
 11. **CartException** : Custom exception for cart-related scenarios.
 12. **OrderException** : Custom exception for order-related scenarios.

## CLI Options

1. **Add a new book:** Allows the user to add a new book by providing the title, author, price, and available copies.
2. **Display available books:** Displays the list of all available books in the bookstore.
3. **Register a new user:** Registers a new user by taking the name, email, and password.
4. **Display registered users:** Displays the list of all registered users.
5. **Add book to shopping cart:** Adds a specified book to the shopping cart of a user.
6. **Remove book from shopping cart:** Removes a specified book from the shopping cart of a user.
7. **Display cart:** Displays the contents of the user's shopping cart.
8. **Place an order:** Places an order for the books in the user's shopping cart.
9. **List orders:** Displays a list of all orders that have been placed.
10. **Exit:** Exits the application.

## Usage

To run the system, compile and run the `BookstoreApplication` class. The main method demonstrates adding books, registering users, adding books to the cart, placing an order, and listing all orders.

### Run Program

```bash
java src/main/java/com/bookstore/BookstoreApplication.java
```

