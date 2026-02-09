package guma.command;

import guma.Storage;
import guma.Ui;
import guma.exception.GumaException;
import guma.task.TaskList;

/**
 * Command to find all tasks that contains the user input
 */
public class FindCommand extends Command {
    private String taskName;

    public FindCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Executes and list the information of the tasks with name contains the user input
     *
     * @param tasks   The task list to be displayed.
     * @param ui      The user interface to show the list.
     * @param storage The storage (unused in this command).
     * @throws GumaException If there is an error listing tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GumaException {
        try {
            return ui.getFindListing(tasks.getFindTaskListing(this.taskName));
        } catch (Exception e) {
            throw new GumaException(">> ERR: FindTask::Unable to find and list tasks!");
        }
    }
}
