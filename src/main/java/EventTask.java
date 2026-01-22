public class EventTask extends Task {
    /** String representation of the Start date / time */
    private String from;
    private String to;

    /**
     * Constructor of EventTask
     * @param taskName name of the task
     * @param from The start date / time of the event
     * @param to The end date / time of the event
     */
    public EventTask(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * String representation of the Event Task
     *
     * @return A string representation of Event Task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
