package guma.command;

import guma.Storage;
import guma.Ui;
import guma.exception.GumaException;
import guma.task.TaskList;

/**
 * Command to unmark a task (mark as not done).
 */
public class UndoCommand extends Command {
    /** Index of the task to be unmarked in the tasks */
    private int taskIndex;

    /**
     * Initializes the command with the index of the task to be unmarked.
     *
     * @param idx The 1-based index of the task.
     */
    public UndoCommand(int idx) {
        this.taskIndex = idx;
    }

    /**
     * Executes the task unmarking and updates storage.
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
            return ui.getUndo(tasks.undoTask(this.taskIndex));
        } catch (Exception e) {
            throw new GumaException(">> weird sia, cannot unmark this task.\n"
                    +
                    "You sure you type correctly ah?\nLike dis: unmark <index>");
        }
    }
}
