package guma.command;

import guma.Storage;
import guma.Ui;
import guma.exception.GumaException;
import guma.exception.GumaInvalidIndex;
import guma.task.TaskList;

/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    /** Index of the task to be unmarked in the tasks */
    private int taskIndex;

    /**
     * Initializes the command with the index of the task to be deleted.
     *
     * @param idx The 1-based index of the task.
     */
    public DeleteCommand(int idx) {
        this.taskIndex = idx;
    }

    /**
     * Executes the task deletion and updates storage.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface to show messages.
     * @param storage The storage to save/load data.
     * @throws GumaException If the task index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            assert (this.taskIndex <= tasks.getSize() && this.taskIndex > 0)
                    : "Select item should be within the task list";
            return ui.getDeletion(tasks.deleteTask(this.taskIndex), tasks.getSize());
        } catch (AssertionError | IndexOutOfBoundsException e) {
            throw GumaInvalidIndex.invalidIndex();
        }
    }
}
