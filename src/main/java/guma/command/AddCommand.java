package guma.command;

import guma.Storage;
import guma.Ui;
import guma.exception.GumaException;
import guma.task.Task;
import guma.task.TaskList;

/**
 * Command to add a task to the task list.
 */
public class AddCommand extends Command {
    /** Task to be added into the tasklist */
    private Task task;

    /**
     * Initializes the command with the task to be added.
     *
     * @param task The task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GumaException {
        try {
            return ui.getAdd(tasks.addTask(this.task), tasks.getSize());
        } catch (Exception e) {
            throw new GumaException(">> ERR: AddCommand::Unable to mark task as complete");
        }
    }
}
