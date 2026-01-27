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
     * String representation of the task
     *
     * @return A string representation of the Task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.isDone ? "X" : " ", taskName);
    }
}
