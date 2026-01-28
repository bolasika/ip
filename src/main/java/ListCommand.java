public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GumaException {
        try {
            ui.getListing(tasks);
        } catch (Exception e) {
            throw new GumaException(">> ERR: ListCommand::Unable to list tasks!");
        }
    }
}
