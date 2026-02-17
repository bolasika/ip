package guma.exception;

/**
 * Custom exception class for Guma application.
 */
public class GumaException extends RuntimeException {
    /**
     * Initializes the exception with a message.
     *
     * @param msg The error message.
     */
    public GumaException(String msg) {
        super(msg);
    }
}
