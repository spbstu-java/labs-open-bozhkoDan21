package lab4.exception;

public class EmptyCollectionException extends RuntimeException {
    public EmptyCollectionException(String message) {
        super(message);
    }

    public EmptyCollectionException(String message, Throwable cause) {
        super(message, cause);
    }
}