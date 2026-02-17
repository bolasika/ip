package guma.command;

import guma.Storage;
import guma.Ui;
import guma.exception.GumaException;
import guma.exception.GumaInvalidIndex;
import guma.task.TaskList;

/**
 * Command to mark a task as completed.
 */
public class CompleteCommand extends Command {
    /** Index of the task to be marked complete in the tasks */
    private int taskIndex;

    /**
     * Initializes the command with the index of the task to be marked.
     *
     * @param idx The 1-based index of the task.
     */
    public CompleteCommand(int idx) {
        this.taskIndex = idx;
    }

    /**
     * Executes the task completion and updates storage.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface to show messages.
     * @param storage The storage to save/load data.
     * @throws GumaException If the task index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GumaException {
        try {
            assert (this.taskIndex <= tasks.getSize() && this.taskIndex > 0)
                    : "Select item should be within the task list";
            return ui.getCompletion(tasks.completeTask(this.taskIndex));
        } catch (AssertionError | IndexOutOfBoundsException e) {
            throw GumaInvalidIndex.invalidIndex();
        } catch (Exception e) {
            throw new GumaException(">> Weird sia, cannot mark this task.\n"
                    +
                    "You sure you type correctly ah?\nLike dis: mark <index>");
        }
    }
}
