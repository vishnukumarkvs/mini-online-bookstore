package src.main.java.com.bookstore.exceptions;

public class BookException extends RuntimeException {
    public BookException() {
        super();
    }

    public BookException(String message) {
        super(message);
    }

    public BookException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookException(Throwable cause) {
        super(cause);
    }
}
