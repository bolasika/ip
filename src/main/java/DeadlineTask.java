public class DeadlineTask extends Task {
    /** String representation on the due date */
    private String description;

    /**
     * Constructor of Deadline Task
     * @param taskName name of the task
     * @param description The specific date / time that is due on
     */
    public DeadlineTask(String taskName, String description) {
        super(TaskType.DEADLINE, taskName);
        this.description = description;
    }

    /**
     * String representation of the Deadline Task
     *
     * @return A string representation of Deadline Task
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.description);
    }
}
