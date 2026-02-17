package guma.command;

import guma.Storage;
import guma.Ui;
import guma.exception.GumaException;
import guma.task.TaskList;

/**
 * Command to add a task to the task list.
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GumaException {
        try {
            return ui.getHelpListing();
        } catch (Exception e) {
            throw new GumaException(">> Weird sia, cannot show help listing.\n"
                    +
                    "You sure you type correctly ah?");
        }
    }
}
