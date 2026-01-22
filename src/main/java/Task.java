public abstract class Task {
    /** Name of the Task */
    private String taskName;

    /** Boolean status of the task */
    private boolean status;

    /**
     * Constructor for the task
     *
     * @param taskName Name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }

    /**
     * Mark the task as complete
     */
    public void complete() {
        this.status = true;
    }

    /**
     * Mark the task as incomplete
     */
    public void undo() {
        this.status = false;
    }

    /**
     * String representation of the task
     *
     * @return A string representation of the Task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.status ? "X" : " ", taskName);
    }
}
