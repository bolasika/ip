package guma.command;

import guma.Storage;
import guma.Ui;
import guma.task.TaskList;

public abstract class Command {
    private boolean isExit = false;

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
}
