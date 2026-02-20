package guma.command;

import java.time.LocalDate;

import guma.Storage;
import guma.Ui;
import guma.task.TaskList;

/**
 * Command to add a task to the task list.
 */
public class ScheduleCommand extends Command {
    /** Task to be added into the tasklist */
    private final LocalDate targetDate;

    /**
     * Initializes the command with the task to be added.
     *
     * @param targetDate The task to add.
     */
    public ScheduleCommand(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    /**
     * Executes the schedule listing for the target date.
     *
     * @param tasks   The task list to be queried.
     * @param ui      The user interface to show the schedule listing.
     * @param storage The storage (unused in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getScheduleListing(tasks, this.targetDate);
    }
}
