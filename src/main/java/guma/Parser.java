package guma;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import guma.command.AddCommand;
import guma.command.Command;
import guma.command.CompleteCommand;
import guma.command.DeleteCommand;
import guma.command.ExitCommand;
import guma.command.FindCommand;
import guma.command.HelpCommand;
import guma.command.ListCommand;
import guma.command.ScheduleCommand;
import guma.command.UndoCommand;
import guma.exception.GumaException;
import guma.exception.GumaInvalidCommand;
import guma.exception.GumaInvalidDateTimeException;
import guma.exception.GumaInvalidDateTimeRangeException;
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
    public static LocalDateTime dateTimeChecker(String input) throws GumaException {
        if (input.matches(REGEX_SLASH_DMY)) {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("d/M/uuuu HHmm"));
        } else if (input.matches(REGEX_DASH_YMD)) {
            return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("uuuu-MM-d HHmm"));
        } else {
            throw GumaInvalidDateTimeException.invalidDateTimeFormat();
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
        fullCommand = toLowerCaseFirstWord(fullCommand);
        switch (action) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "mark":
            return parseMarkCommand(fullCommand);
        case "unmark":
            return parseUnmarkCommand(fullCommand);
        case "delete":
            return parseDeleteCommand(fullCommand);
        case "todo":
            return parseToDoCommand(fullCommand);
        case "deadline":
            return parseDeadlineCommand(fullCommand);
        case "event":
            return parseEventCommand(fullCommand);
        case "find":
            return parseFindCommand(fullCommand);
        case "help":
            return new HelpCommand();
        case "schedule":
            return parseScheduleCommand(fullCommand);
        default:
            throw GumaInvalidCommand.unknownCommand();
        }
    }

    private static String toLowerCaseFirstWord(String fullCommand) {
        String[] fullCommandArr = fullCommand.split(" ");
        fullCommandArr[0] = fullCommandArr[0].toLowerCase();
        return String.join(" ", fullCommandArr);
    }

    private static UndoCommand parseUnmarkCommand(String fullCommand) {
        try {
            int index = Integer.parseInt(fullCommand.split(" ")[1]);
            return new UndoCommand(index);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw GumaInvalidCommand.invalidSyntax("unmark <task_index>");
        } catch (Exception e) {
            throw new GumaException(">> An unexpected error occurred while parsing the unmark command.");
        }
    }

    private static CompleteCommand parseMarkCommand(String fullCommand) {
        try {
            int index = Integer.parseInt(fullCommand.split(" ")[1]);
            return new CompleteCommand(index);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw GumaInvalidCommand.invalidSyntax("mark <task_index>");
        } catch (Exception e) {
            throw new GumaException(">> An unexpected error occurred while parsing the mark command.");
        }
    }

    private static DeleteCommand parseDeleteCommand(String fullCommand) {
        try {
            int index = Integer.parseInt(fullCommand.split(" ")[1]);
            return new DeleteCommand(index);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw GumaInvalidCommand.invalidSyntax("delete <task_index>");
        } catch (Exception e) {
            throw new GumaException(">> An unexpected error occurred while parsing the delete command.");
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
        } catch (ArrayIndexOutOfBoundsException e) {
            throw GumaInvalidCommand.invalidSyntax("todo <taskname>");
        } catch (Exception e) {
            throw new GumaException(">> An unexpected error occurred while parsing the todo command.");
        }
    }

    /**
     * Parses a {@code deadline} command for input validation
     * @param fullCommand The full input string from the user.
     * @return An {@link AddCommand} that adds a {@link DeadlineTask}.
     * @throws GumaException If the command format is invalid.
     */
    private static AddCommand parseDeadlineCommand(String fullCommand) {
        try {
            String taskName = fullCommand.split("deadline ")[1].split(" /by")[0];
            String description = fullCommand.split("/by ")[1];
            return new AddCommand(new DeadlineTask(taskName, Parser.dateTimeChecker(description)));
        } catch (GumaInvalidDateTimeException e) {
            throw GumaInvalidDateTimeException.invalidDateTimeFormat();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw GumaInvalidCommand.invalidSyntax("deadline <taskname> /by <DateTime>\n"
                    +
                    "<DateTime> got format one ah: dd/MM/yyyy HHmm or yyyy-MM-dd HHmm");
        } catch (Exception e) {
            throw new GumaException(">> An unexpected error occurred while parsing the deadline command.");
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
            LocalDateTime fromDateTime = Parser.dateTimeChecker(fromTime);
            LocalDateTime toDateTime = Parser.dateTimeChecker(toTime);
            if (toDateTime.isBefore(fromDateTime)) {
                throw GumaInvalidDateTimeRangeException.endBeforeStart();
            }
            return new AddCommand(new EventTask(taskName, fromDateTime, toDateTime));
        } catch (GumaInvalidDateTimeException e) {
            throw GumaInvalidDateTimeException.invalidDateTimeFormat();
        } catch (GumaInvalidDateTimeRangeException e) {
            throw GumaInvalidDateTimeRangeException.endBeforeStart();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw GumaInvalidCommand.invalidSyntax("event <taskname> /from <DateTime> /to <DateTime>\n"
                    +
                    "<DateTime> got format one ah: dd/MM/yyyy HHmm or yyyy-MM-dd HHmm");
        } catch (Exception e) {
            throw new GumaException(">> An unexpected error occurred while parsing the event command.");
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
        } catch (ArrayIndexOutOfBoundsException e) {
            throw GumaInvalidCommand.invalidSyntax("find <keyword>");
        } catch (Exception e) {
            throw new GumaException(">> An unexpected error occurred while parsing the find command.");
        }
    }


    private static ScheduleCommand parseScheduleCommand(String fullCommand) {
        try {
            String date = fullCommand.split("/on ")[1];
            return new ScheduleCommand(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw GumaInvalidCommand.invalidSyntax("schedule /on dd/MM/yyyy");
        } catch (DateTimeParseException e) {
            throw GumaInvalidDateTimeException.invalidDateFormat();
        } catch (Exception e) {
            throw new GumaException(">> An unexpected error occurred while parsing the schedule command.");
        }
    }


}
