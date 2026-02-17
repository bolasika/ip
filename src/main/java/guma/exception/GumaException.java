package guma.exception;

/**
 * Base exception type for Guma application errors.
 * Use subclasses for specific error categories when possible.
 */
public class GumaException extends RuntimeException {
    /**
     * Initializes the exception with a human-readable message.
     *
     * @param msg The error message to display to the user.
     */
    public GumaException(String msg) {
        super(msg);
    }
}
