package guma.command;

import guma.Storage;
import guma.Ui;

import guma.Storage;
import guma.Ui;
import guma.exception.GumaException;
import guma.task.TaskList;

/**
 * Command to mark a task as completed.
 */
public class CompleteCommand extends Command {
    /** Index of the task to be marked complete in the tasks */
    private int taskIdx;

    /**
     * Initializes the command with the index of the task to be marked.
     *
     * @param idx The 1-based index of the task.
     */
    public CompleteCommand(int idx) {
        this.taskIdx = idx;
    }

    /**
     * Executes the task completion and updates storage.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface to show messages.
     * @param storage The storage to save/load data.
     * @throws GumaException If the task index is invalid.
     */    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GumaException {
        try {
            ui.getCompletion(tasks.completeTask(this.taskIdx));
        } catch (Exception e) {
            throw new GumaException(">> ERR: CompleteCommand::Unable to mark task as complete");
        }
    }
}
