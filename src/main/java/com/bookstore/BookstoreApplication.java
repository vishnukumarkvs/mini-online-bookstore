package src.main.java.com.bookstore;

import src.main.java.com.bookstore.model.Book;
import src.main.java.com.bookstore.model.Order;
import src.main.java.com.bookstore.model.User;
import src.main.java.com.bookstore.service.BookstoreService;
import src.main.java.com.bookstore.service.OrderService;
import src.main.java.com.bookstore.service.UserService;
import src.main.java.com.bookstore.exceptions.BookException;
import src.main.java.com.bookstore.exceptions.InvalidInputException;
import src.main.java.com.bookstore.exceptions.CartException;
import src.main.java.com.bookstore.exceptions.OrderException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BookstoreApplication {
    private static BookstoreService bookstoreService = new BookstoreService();
    private static UserService userService = new UserService();
    private static OrderService orderService = new OrderService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        addNewBook(scanner);
                        break;
                    case 2:
                        displayAvailableBooks();
                        break;
                    case 3:
                        registerNewUser(scanner);
                        break;
                    case 4:
                        displayRegisteredUsers();
                        break;
                    case 5:
                        addBookToCart(scanner);
                        break;
                    case 6:
                        removeBookFromCart(scanner);
                        break;
                    case 7:
                        displayCart(scanner);
                        break;
                    case 8:
                        placeOrder(scanner);
                        break;
                    case 9:
                        listOrders();
                        break;
                    case 10:
                        running = false;
                        System.out.println("Exiting the application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();  // Consume invalid input
            } catch (BookException | InvalidInputException | CartException | OrderException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=== Mini Online Bookstore ===");
        System.out.println("1. Add a new book");
        System.out.println("2. Display available books");
        System.out.println("3. Register a new user");
        System.out.println("4. Display registered users");
        System.out.println("5. Add book to shopping cart");
        System.out.println("6. Remove book from shopping cart");
        System.out.println("7. Display cart");
        System.out.println("8. Place an order");
        System.out.println("9. List orders");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addNewBook(Scanner scanner) {
        try {
            String title = "";
            while (title.isEmpty()) {
                System.out.print("Enter book title: ");
                title = scanner.nextLine().trim();
                if (title.isEmpty()) {
                    System.out.println("Title cannot be empty. Please enter a valid title.");
                }
            }

            String author = "";
            while (author.isEmpty()) {
                System.out.print("Enter book author: ");
                author = scanner.nextLine().trim();
                if (author.isEmpty()) {
                    System.out.println("Author cannot be empty. Please enter a valid author.");
                }
            }

            double price = -1;
            while (price < 0) {
                try {
                    System.out.print("Enter book price: ");
                    price = scanner.nextDouble();
                    if (price < 0) {
                        System.out.println("Price cannot be negative. Please enter a valid price.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid price.");
                    scanner.nextLine();  // Consume invalid input
                }
            }

            int availableCopies = -1;
            while (availableCopies < 0) {
                try {
                    System.out.print("Enter available copies: ");
                    availableCopies = scanner.nextInt();
                    if (availableCopies < 0) {
                        System.out.println("Available copies cannot be negative. Please enter a valid number.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();  // Consume invalid input
                }
            }

            bookstoreService.addBook(title, author, price, availableCopies);
            System.out.println("Book added successfully!");
        } catch (BookException | InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayAvailableBooks() {
        try {
            List<Book> books = bookstoreService.getBooks();
            if (books.isEmpty()) {
                System.out.println("No books available.");
            } else {
                System.out.println("Available Books:");
                books.forEach(book -> {
                    book.displayDetails();
                    System.out.println();
                });
            }
        } catch (BookException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void registerNewUser(Scanner scanner) {
        try {
            String name = "";
            while (name.isEmpty()) {
                System.out.print("Enter user name: ");
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty. Please enter a valid name.");
                }
            }

            String email = "";
            while (email.isEmpty()) {
                System.out.print("Enter user email: ");
                email = scanner.nextLine().trim();
                if (email.isEmpty()) {
                    System.out.println("Email cannot be empty. Please enter a valid email.");
                }
            }

            String password = "";
            while (password.isEmpty()) {
                System.out.print("Enter user password: ");
                password = scanner.nextLine().trim();
                if (password.isEmpty()) {
                    System.out.println("Password cannot be empty. Please enter a valid password.");
                }
            }

            userService.registerUser(name, email, password);
            System.out.println("User registered successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayRegisteredUsers() {
        try {
            List<User> users = userService.getUsers();
            if (users.isEmpty()) {
                System.out.println("No registered users.");
            } else {
                System.out.println("Registered Users:");
                users.forEach(user -> {
                    System.out.println(user);
                    System.out.println();
                });
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addBookToCart(Scanner scanner) {
        try {
            String email = "";
            while (email.isEmpty()) {
                System.out.print("Enter user email: ");
                email = scanner.nextLine().trim();
                if (email.isEmpty()) {
                    System.out.println("Email cannot be empty. Please enter a valid email.");
                }
            }

            String title = "";
            while (title.isEmpty()) {
                System.out.print("Enter book title to add to cart: ");
                title = scanner.nextLine().trim();
                if (title.isEmpty()) {
                    System.out.println("Title cannot be empty. Please enter a valid title.");
                }
            }

            int quantity = -1;
            while (quantity <= 0) {
                try {
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    if (quantity < 0) {
                        System.out.println("Quantity cannot be negative. Please enter a valid number.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();  // Consume invalid input
                }
            }

            Book book = bookstoreService.findBookByTitle(title);
            userService.updateUserCart(email, book, quantity);
            System.out.println("Book added to cart successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data.");
            scanner.nextLine();  // Consume invalid input
        } catch (BookException | IllegalArgumentException | CartException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // remove book from cart
    private static void removeBookFromCart(Scanner scanner) {
        try {
            String email = "";
            while (email.isEmpty()) {
                System.out.print("Enter user email: ");
                email = scanner.nextLine().trim();
                if (email.isEmpty()) {
                    System.out.println("Email cannot be empty. Please enter a valid email.");
                }
            }

            String title = "";
            while (title.isEmpty()) {
                System.out.print("Enter book title to remove from cart: ");
                title = scanner.nextLine().trim();
                if (title.isEmpty()) {
                    System.out.println("Title cannot be empty. Please enter a valid title.");
                }
            }

            int quantity = -1;
            while (quantity <= 0) {
                try {
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    if (quantity < 0) {
                        System.out.println("Quantity cannot be negative. Please enter a valid number.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();  // Consume invalid input
                }
            }

            Book book = bookstoreService.findBookByTitle(title);
            userService.updateUserCart(email, book, -quantity);
            System.out.println("Book removed from cart successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data.");
            scanner.nextLine();  // Consume invalid input
        } catch (BookException | IllegalArgumentException | CartException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void placeOrder(Scanner scanner) {
        try {
            String email = "";
            while (email.isEmpty()) {
                System.out.print("Enter user email: ");
                email = scanner.nextLine().trim();
                if (email.isEmpty()) {
                    System.out.println("Email cannot be empty. Please enter a valid email.");
                }
            }

            User user = userService.getUserByEmail(email);
            // check if cart empty or not
            if (user.getCart().getBooks().isEmpty()) {
                throw new OrderException("Cart is empty. Please add books to cart before placing an order.");
            }

            orderService.addOrder(user);
            double totalCost = orderService.getOrdersByUser(user)
                    .get(orderService.getOrdersByUser(user).size() - 1)
                    .getTotalOrderValue();

            System.out.println("Order placed successfully! Total cost: $" + totalCost);
        } catch (IllegalArgumentException | OrderException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // complete list orders function
    private static void listOrders() {
        System.out.println("List of Orders:");
        List<Order> orders = orderService.getOrders();
        if(orders.isEmpty()){
            System.out.println("No orders placed yet.");
            return;
        }
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    private static void displayCart(Scanner scanner) {
        try {
            String email = "";
            while (email.isEmpty()) {
                System.out.print("Enter user email: ");
                email = scanner.nextLine().trim();
                if (email.isEmpty()) {
                    System.out.println("Email cannot be empty. Please enter a valid email.");
                }
            }

            User user = userService.getUserByEmail(email);
            user.getCart().displayCart();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
