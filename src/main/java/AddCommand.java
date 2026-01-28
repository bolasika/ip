public class AddCommand extends Command {
    /** Task to be added into the tasklist */
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GumaException {
        try {
            ui.getAdd(tasks.addTask(this.task), tasks.getSize());
        } catch (Exception e) {
            System.out.println(">> ERR: Unable to add task into the TaskList!");
        }
    }
}
