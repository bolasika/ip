package guma.task;
import java.time.LocalDate;
import java.time.LocalDateTime;

import guma.Parser;

/**
 * Represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    /** String representation on the due date */
    private LocalDateTime date;

    /**
     * Constructs a deadline task.
     *
     * @param taskName Name of the task.
     * @param date     The date/time the task is due.
     */
    public DeadlineTask(String taskName, LocalDateTime date) {
        super(taskName);
        this.date = date;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), Parser.dateToString(this.date));
    }

    /**
     * Converts the deadline task into a single-line format for saving to disk.
     *
     * @return A single-line string encoding this deadline task.
     */
    @Override
    public String toFileString() {
        return String.format("D_%s_%s_%s", getStatus() ? "1" : "0",
                getTaskName(), Parser.dateToSave(this.date));
    }

    /**
     * Returns true when the query date matches the deadline date.
     *
     * @param queryDate The date to check against.
     * @return {@code true} if the deadline is on the query date, else {@code false}.
     */
    @Override
    public boolean insideSchedule(LocalDate queryDate) {
        return queryDate.equals(this.date.toLocalDate());
    }
}
