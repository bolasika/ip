public class ToDoTask extends Task {

    /**
     * Constructor of ToDo Task
     * @param taskName Name of the task
     */
    public ToDoTask(String taskName) {
        super(TaskType.TODO, taskName);
    }

    /**
     * String representation of the ToDo Task
     *
     * @return A string representation of ToDo Task
     */
    @Override
    public String toString() {
        return String.format("%s", super.toString());
    }
}
