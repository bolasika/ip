package guma.command;

import guma.Storage;
import guma.Ui;
import guma.exception.GumaException;
import guma.task.TaskList;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the listing of tasks.
     *
     * @param tasks   The task list to be displayed.
     * @param ui      The user interface to show the list.
     * @param storage The storage (unused in this command).
     * @throws GumaException If there is an error listing tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GumaException {
        try {
            return ui.getListing(tasks);
        } catch (Exception e) {
            throw new GumaException(">> Weird sia, cannot list tasks.\n"
                    +
                    "You sure you type correctly ah?");
        }
    }
}
