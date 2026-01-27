public class ToDoTask extends Task {

    /**
     * Constructor of ToDo Task
     * @param taskName Name of the task
     */
    public ToDoTask(String taskName) {
        super(taskName);
    }

    /**
     * String representation of the ToDo Task
     *
     * @return A string representation of ToDo Task
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Convert ToDo task into a single-line format for saving to disk
     * @return A single-line string encoding Todo task
     */
    @Override
    public String toFileString() {
        return "T_" + (getStatus() ? "1" : "0") + "_" + getTaskName();
    }
}
