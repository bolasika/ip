package guma.exception;

/**
 * Thrown when a date-time string does not match any supported format.
 */
public class GumaInvalidDateTimeException extends GumaException {
    /**
     * Initializes the exception with a human-readable message.
     *
     * @param msg The error message to display to the user.
     */
    public GumaInvalidDateTimeException(String msg) {
        super(msg);
    }

    /**
     * Creates an exception for unsupported date-time formats.
     *
     * @return A {@link GumaInvalidDateTimeException} with the standard format message.
     */
    public static GumaException invalidDateTimeFormat() {
        return new GumaInvalidDateTimeException(">> You sure your date pattern got follow mine anot.\n"
                +
                "I only accept these 2 formats:\n1) dd/MM/yyyy HHmm\n2) yyyy-MM-dd HHmm");
    }

    /**
     * Creates an exception for unsupported date-time formats.
     *
     * @return A {@link GumaInvalidDateTimeException} with the standard format message.
     */
    public static GumaException invalidDateFormat() {
        return new GumaInvalidDateTimeException(">> You sure your date pattern got follow mine anot:\ndd/MM/yyyy");
    }

}
