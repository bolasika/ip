public class EventTask extends Task {
    /** String representation of the Start date / time */
    private String startTime;
    private String endTime;

    /**
     * Constructor of EventTask
     * @param taskName name of the task
     * @param from The start date / time of the event
     * @param to The end date / time of the event
     */
    public EventTask(String taskName, String from, String to) {
        super(taskName);
        this.startTime = from;
        this.endTime = to;
    }

    /**
     * String representation of the Event Task
     *
     * @return A string representation of Event Task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.startTime, this.endTime);
    }

    /**
     * Convert Event task into a single-line format for saving to disk
     * @return A single-line string encoding this Event task
     */
    @Override
    public String toFileString() {
        return String.format("E_%s_%s_%s_%s", getStatus() ? "1" : "0",
                getTaskName(), this.startTime, this.endTime);
    }
}
