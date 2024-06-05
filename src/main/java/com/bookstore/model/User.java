package src.main.java.com.bookstore.model;

public class User {
    private String name;
    private String email;
    private transient String password;
    private ShoppingCart cart;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cart = new ShoppingCart();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public void clearCart() {
        cart.clearCart();
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email;
    }
}
