public class UndoCommand extends Command {
    /** Index of the task to be unmarked in the tasks */
    private int taskIdx;

    public UndoCommand(int idx) {
        this.taskIdx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GumaException {
        try {
            ui.getUndo(tasks.undoTask(this.taskIdx));
        } catch (Exception e) {
            throw new GumaException(">> ERR: UndoCommand::Unable to unmark task");
        }
    }
}
