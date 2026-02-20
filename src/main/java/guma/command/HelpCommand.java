package guma.command;

import guma.Storage;
import guma.Ui;
import guma.task.TaskList;

/**
 * Command to add a task to the task list.
 */
public class HelpCommand extends Command {
    /**
     * Executes the help listing command.
     *
     * @param tasks   The task list (unused in this command).
     * @param ui      The user interface to show help details.
     * @param storage The storage (unused in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getHelpListing();
    }
}
