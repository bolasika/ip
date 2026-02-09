package guma.command;

import guma.Storage;
import guma.Ui;
import guma.exception.GumaException;
import guma.task.TaskList;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit procedure, saving tasks and showing farewell message.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface to show messages.
     * @param storage The storage to save data.
     * @throws GumaException If there is an error saving tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GumaException {
        try {
            tasks.saveTo(storage);
            return ui.getFarewell();
        } catch (Exception e) {
            throw new GumaException(">> ERR: ExitCommand::Error in saving files!");
        }
    }

    /**
     * Returns true to indicate that the application should exit.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
