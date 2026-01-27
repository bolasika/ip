public abstract class Task {
    /** Name of the Task */
    private String taskName;

    /** Boolean status of the task */
    private boolean isDone;

    /**
     * Constructor for the task
     *
     * @param taskName Name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Mark the task as complete
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Mark the task as incomplete
     */
    public void undo() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is marked as complete.
     *
     * @return True if the task is completed, false otherwise
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return The task name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * String representation of the task
     *
     * @return A string representation of the Task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.isDone ? "X" : " ", taskName);
    }

    /**
     * Returns a single-line string representation of this task for saving to the data file.
     *
     * @return A single-line string representation suitable for file storage
     */
    public abstract String toFileString();
}
