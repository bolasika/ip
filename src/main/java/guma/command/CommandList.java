package guma.command;

/**
 * Holds the list of supported commands and their display details.
 * Used by the help feature to show available commands and usage.
 */
public enum CommandList {
    HELP("help", "Shows all commands"),
    TODO("todo <description>", "Adds a todo task"),
    DEADLINE("deadline <description> /by <DateTime>", "Adds a deadline task"),
    EVENT("event <description> /from <DateTime> /to <DateTime>", "Adds an event task"),
    LIST("list", "Lists all tasks with index"),
    MARK("mark <index>", "Marks a task as done"),
    UNMARK("unmark <index>", "Marks a task as not done"),
    DELETE("delete <index>", "Deletes a task"),
    SCHEDULE("schedule /on DD/MM/YYYY", "Lists tasks that are either on or within the date itself"),
    BYE("bye", "Exits the program");


    private final String syntax;
    private final String description;

    CommandList(String syntax, String description) {
        this.syntax = syntax;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("`%s`: %s\n", this.syntax, this.description);
    }
}
