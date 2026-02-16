package guma;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import guma.command.AddCommand;
import guma.command.Command;
import guma.command.CompleteCommand;
import guma.command.DeleteCommand;
import guma.command.ExitCommand;
import guma.command.FindCommand;
import guma.command.ListCommand;
import guma.command.UndoCommand;
import guma.exception.GumaException;
import guma.task.DeadlineTask;
import guma.task.EventTask;
import guma.task.ToDoTask;

/**
 * Utility class for validating user inputs and outputs
 */
public class Parser {
    /* Regex pattern for date-time in DD/MM/YYYY with 24-hour time */
    private static final String REGEX_SLASH_DMY = "\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{4}";

    /* Regex pattern for date-time in YY-MM-DD with 24-hour time */
    private static final String REGEX_DASH_YMD = "\\d{4}-\\d{2}-\\d{1,2}\\s\\d{4}";

    /**
     * Checks whether the input matches a supported date-time format.
     * If it matches, it is parsed as a {@link LocalDateTime} and returned
     * in display format {@code "MMM dd uuuu h.mma"}.
     * If it does not match, the original input is returned unchanged.
     *
     * @param input The user input data that was stored in the Task
     * @return A normalized date-time string if matches the regex, else original input.
     */
    public static LocalDateTime dateChecker(String input) {
        if (input.matches(REGEX_SLASH_DMY)) {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("d/M/uuuu HHmm"));
        } else if (input.matches(REGEX_DASH_YMD)) {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("uuuu-MM-d HHmm"));
        } else {
            throw new GumaException("No matching pattern for Date inputs");
        }
    }

    /**
     * The String Representation of the LocalDateTime type
     * @param date The date to be changed into String representation
     * @return String Representation of the LocalDateTime type
     */
    public static String dateToString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd uuuu h.mma", Locale.ENGLISH));
    }

    /**
     * Convert LocalDateTime type to a String type so that it can be saved into a Local file
     * @param date The date to be saved into the Local File
     * @return A String with the correct syntax to be saved into the Local file
     */
    public static String dateToSave(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("d/M/uuuu HHmm", Locale.ENGLISH));
    }

    /**
     * Interprets the user input and returns the corresponding command.
     *
     * @param fullCommand The full string entered by the user.
     * @return The specific command object to be executed.
     * @throws GumaException If the command is invalid or has wrong format.
     */
    public static Command parse(String fullCommand) throws GumaException {
        String action = fullCommand.split(" ")[0].toLowerCase();
        String taskName;
        switch (action) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "mark":
            return new CompleteCommand(Integer.parseInt(fullCommand.split(" ")[1]));
        case "unmark":
            return new UndoCommand(Integer.parseInt(fullCommand.split(" ")[1]));
        case "delete":
            return new DeleteCommand(Integer.parseInt(fullCommand.split(" ")[1]));
        case "todo":
            return parseToDoCommand(fullCommand);
        case "deadline":
            return parseDeadlineCommand(fullCommand);
        case "event":
            return parseEventCommand(fullCommand);
        case "find":
            return parseFindCommand(fullCommand);
        default:
            throw new GumaException("\n\t Sorry, I do not recognize the command :-(\n");
        }
    }

    /**
     * Parses a {@code todo} command for input validation
     * @param fullCommand The full input string from the user.
     * @return An {@link AddCommand} that adds a {@link ToDoTask}.
     * @throws GumaException If the command format is invalid.
     */
    private static AddCommand parseToDoCommand(String fullCommand) {
        try {
            return new AddCommand(new ToDoTask(fullCommand.split("todo ")[1]));
        } catch (Exception e) {
            throw new GumaException(">> ERR: Ensure your Syntax: todo <taskname>");
        }
    }

    /**
     * Parses a {@code deadline} command for input validation
     * @param fullCommand The full input string from the user.
     * @return An {@link AddCommand} that adds a {@link DeadlineTask}.
     * @throws GumaException If the command format is invalid.
     */
    private static AddCommand parseDeadlineCommand(String fullCommand) {
        String taskName;
        try {
            taskName = fullCommand.split("deadline ")[1].split(" /by")[0];
            String description = fullCommand.split("/by ")[1];
            return new AddCommand(new DeadlineTask(taskName, Parser.dateChecker(description)));
        } catch (Exception e) {
            throw new GumaException(">> ERR: Ensure your Syntax: deadline <taskname> /by <description>");
        }
    }

    /**
     * Parses an {@code event} command for input validation
     * @param fullCommand The full input string from the user.
     * @return An {@link AddCommand} that adds an {@link EventTask}.
     * @throws GumaException If the command format is invalid.
     */
    private static AddCommand parseEventCommand(String fullCommand) {
        try {
            String taskName = fullCommand.split("event ")[1].split(" /from")[0];
            String fromTime = fullCommand.split(" /from ")[1].split(" /to ")[0];
            String toTime = fullCommand.split("/to ")[1];
            return new AddCommand(new EventTask(taskName, Parser.dateChecker(fromTime), Parser.dateChecker(toTime)));
        } catch (Exception e) {
            throw new GumaException(">> ERR: Ensure your Syntax: event <taskname> /from <Start time> /to <End time>");
        }
    }

    /**
     * Parses a {@code find} command for input validation
     * @param fullCommand The full input string from the user.
     * @return A {@link FindCommand} with the search keyword.
     * @throws GumaException If the command format is invalid.
     */
    private static FindCommand parseFindCommand(String fullCommand) {
        try {
            String taskName = fullCommand.split("find ")[1];
            return new FindCommand(taskName);
        } catch (Exception e) {
            throw new GumaException(">> ERR: Ensure your Syntax: find <taskname>");
        }
    }


}
