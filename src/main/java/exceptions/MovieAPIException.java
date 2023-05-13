package exceptions;

public class MovieAPIException extends RuntimeException {
    public MovieAPIException(String message) {
        super(message);
    }

    public MovieAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}
