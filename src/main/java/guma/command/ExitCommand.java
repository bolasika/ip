package guma.command;

import guma.Storage;
import guma.Ui;
import guma.exception.GumaException;
import guma.task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GumaException {
        try {
            tasks.saveTo(storage);
            ui.getFarewell();
        } catch (Exception e) {
            throw new GumaException(">> ERR: ExitCommand::Error in saving files!");
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
