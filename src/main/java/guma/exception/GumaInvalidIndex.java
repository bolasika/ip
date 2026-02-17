package guma.exception;

/**
 * Thrown when a task index is missing or out of bounds.
 */
public class GumaInvalidIndex extends GumaException {
    /**
     * Initializes the exception with a human-readable message.
     *
     * @param msg The error message to display to the user.
     */
    public GumaInvalidIndex(String msg) {
        super(msg);
    }

    /**
     * Creates an exception for invalid task indices.
     *
     * @return A {@link GumaInvalidIndex} with the standard index error message.
     */
    public static GumaException invalidIndex() {
        return new GumaInvalidIndex(">> Eh you make sure your chosen index is correct anot.\n"
                +
                "Check the index of your task with list command");
    }
}
