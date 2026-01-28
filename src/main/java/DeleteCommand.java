public class DeleteCommand extends Command {
    /** Index of the task to be unmarked in the tasks */
    private int taskIdx;

    public DeleteCommand(int idx) {
        this.taskIdx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GumaException {
        try {
            ui.getDeletion(tasks.deleteTask(this.taskIdx), tasks.getSize());
        } catch (Exception e) {
            throw new GumaException(">> ERR: DeleteCommand::Unable to delete task!");
        }
    }
}
