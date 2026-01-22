public class Task {
    /** Name of the Task */
    private String taskName;

    /**
     * Constructor for the task
     *
     * @param taskName Name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * String representation of the task
     *
     * @return A string representation of the Task
     */
    @Override
    public String toString() {
        return taskName;
    }
}
