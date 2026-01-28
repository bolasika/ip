package guma;

import guma.command.AddCommand;
import guma.command.Command;
import guma.command.DeleteCommand;
import guma.command.ExitCommand;
import guma.command.ListCommand;
import guma.command.UndoCommand;
import guma.command.CompleteCommand;
import guma.exception.GumaException;
import guma.task.ToDoTask;
import guma.task.DeadlineTask;
import guma.task.EventTask;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Utility class for validating user inputs and outputs
 */
public class Parser {
    /* Regex pattern for date-time in DD/MM/YYYY with 24-hour time */
    private static final String REGEX_SLASH_DMY = "\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{4}";

    /* Regex pattern for date-time in YY-MM-DD with 24-hour time */
    private static final String REGEX_DASH_YMD = "\\d{4}-\\d{2}-\\d{1,2}\\s\\d{4}";

    /**
     * Check whether the provided input matches a supported date-time format
     * If matches, it would be parsed as {@link LocalDateTime} and
     * return a display format: {@code "MMM dd uuuu h.mma"}.
     * If the input does not match any support format, return the original input
     *
     * @param input The user input data that was stored in the Task
     * @return A normalized date-time string if matches the regex, else original input.
     */
    public static String dateChecker(String input) {
        if (input.matches(REGEX_SLASH_DMY) || input.matches(REGEX_DASH_YMD)) {
            DateTimeFormatter dateFmter;
            if (input.matches(REGEX_SLASH_DMY)) {
                dateFmter = DateTimeFormatter.ofPattern("d/M/uuuu HHmm");
            } else {
                dateFmter = DateTimeFormatter.ofPattern("uuuu-MM-d HHmm");
            }
            LocalDateTime date = LocalDateTime.parse(input, dateFmter);
            return date.format(DateTimeFormatter.ofPattern("MMM dd uuuu h.mma", Locale.ENGLISH));

        }
        return input;
    }

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
            try {
                return new AddCommand(new ToDoTask(fullCommand.split("todo ")[1]));
            } catch (Exception e) {
                throw new GumaException(">> ERR: Ensure your Syntax: todo <taskname>");
            }
        case "deadline":
            try {
                taskName = fullCommand.split("deadline ")[1].split(" /by")[0];
                String description = fullCommand.split("/by ")[1];
                return new AddCommand(new DeadlineTask(taskName, description));
            } catch (Exception e) {
                throw new GumaException(">> ERR: Ensure your Syntax: deadline <taskname> /by <description>");
            }
        case "event":
            try {
                taskName = fullCommand.split("event ")[1].split(" /from")[0];
                String fromTime = fullCommand.split(" /from ")[1].split(" /to ")[0];
                String toTime = fullCommand.split("/to ")[1];
                return new AddCommand(new EventTask(taskName, fromTime, toTime));
            } catch (Exception e) {
                throw new GumaException(">> ERR: Ensure your Syntax: event <taskname> /from <Start time> /to <End time>");
            }
        default:
            throw new GumaException("\n\t Sorry, I do not recognize the command :-(\n");
        }
    }


}
