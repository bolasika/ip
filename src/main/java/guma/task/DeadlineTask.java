package guma.task;
import java.time.LocalDateTime;

import guma.Parser;



/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    /** String representation on the due date */
    private LocalDateTime date;

    /**
     * Constructor of Deadline Task
     *
     * @param taskName    name of the task
     * @param date The specific date / time that is due on
     */
    public DeadlineTask(String taskName, LocalDateTime date) {
        super(taskName);
        this.date = date;
    }

    /**
     * String representation of the Deadline Task
     *
     * @return A string representation of Deadline Task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), Parser.dateToString(this.date));
    }

    /**
     * Convert DEADLINE task into a single-line format for saving to disk
     *
     * @return A single-line string encoding this DEADLINE task
     */
    @Override
    public String toFileString() {
        return String.format("D_%s_%s_%s", getStatus() ? "1" : "0",
                getTaskName(), Parser.dateToSave(this.date));
    }
}
