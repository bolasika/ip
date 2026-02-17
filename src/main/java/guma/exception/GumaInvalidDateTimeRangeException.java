package guma.exception;

/**
 * Thrown when a date-time range is invalid (e.g., end is before start).
 */
public class GumaInvalidDateTimeRangeException extends GumaException {

    /**
     * Initializes the exception with a human-readable message.
     *
     * @param msg The error message to display to the user.
     */
    public GumaInvalidDateTimeRangeException(String msg) {
        super(msg);
    }

    /**
     * Creates an exception for end date-time earlier than start date-time.
     *
     * @return A {@link GumaInvalidDateTimeRangeException} with the standard range error message.
     */
    public static GumaException endBeforeStart() {
        return new GumaInvalidDateTimeRangeException(">> The end date-time cannot be before the start date-time.");
    }
}
