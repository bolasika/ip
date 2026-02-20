package guma.command;

import guma.Storage;
import guma.Ui;
import guma.task.TaskList;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Checks if the application should exit after this command. (for CLI mode)
     *
     * @return true if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface to show messages.
     * @param storage The storage to save/load data.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
