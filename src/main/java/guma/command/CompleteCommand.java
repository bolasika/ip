package guma.command;

import guma.Storage;
import guma.Ui;

import guma.Storage;
import guma.Ui;
import guma.exception.GumaException;
import guma.task.TaskList;

public class CompleteCommand extends Command {
    /** Index of the task to be marked complete in the tasks */
    private int taskIdx;

    public CompleteCommand(int idx) {
        this.taskIdx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GumaException {
        try {
            ui.getCompletion(tasks.completeTask(this.taskIdx));
        } catch (Exception e) {
            throw new GumaException(">> ERR: CompleteCommand::Unable to mark task as complete");
        }
    }
}
