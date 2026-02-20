package guma.command;

import guma.Storage;
import guma.Ui;
import guma.task.TaskList;

/**
 * Command to add a task to the task list.
 */
public class HelpCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getHelpListing();
    }
}
