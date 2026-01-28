public class DeadlineTask extends Task {
    /** String representation on the due date */
    private String description;

    /**
     * Constructor of Deadline Task
     * @param taskName name of the task
     * @param description The specific date / time that is due on
     */
    public DeadlineTask(String taskName, String description) {
        super(taskName);
        this.description = description;
    }

    /**
     * String representation of the Deadline Task
     *
     * @return A string representation of Deadline Task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), Parser.dateChecker(this.description));
    }

    /**
     * Convert DEADLINE task into a single-line format for saving to disk
     * @return A single-line string encoding this DEADLINE task
     */
    @Override
    public String toFileString() {
        return String.format("D_%s_%s_%s", getStatus() ? "1" : "0",
                getTaskName(), this.description);
    }
}
