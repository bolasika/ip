package guma.exception;

/**
 * Thrown when the user enters an unknown command or invalid command syntax.
 */
public class GumaInvalidCommand extends GumaException {
    /**
     * Initializes the exception with a human-readable message.
     *
     * @param msg The error message to display to the user.
     */
    public GumaInvalidCommand(String msg) {
        super(msg);
    }

    /**
     * Creates an exception for invalid command syntax with a hint.
     *
     * @param syntaxMsg The expected command syntax to show to the user.
     * @return A {@link GumaInvalidCommand} containing the syntax guidance.
     */
    public static GumaException invalidSyntax(String syntaxMsg) {
        String errorMsg = ">> You sure you type correctly ah?\nThe syntax for this command should be:\n" + syntaxMsg;
        return new GumaInvalidCommand(errorMsg);
    }

    /**
     * Creates an exception for unknown commands.
     *
     * @return A {@link GumaInvalidCommand} with the standard unknown-command message.
     */
    public static GumaException unknownCommand() {
        return new GumaInvalidCommand(">> Wah lao, what you mean?\n"
                    +
                    "Run help command if you don't know lei...");
    }
}
