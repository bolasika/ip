package guma.task;
import java.time.LocalDate;
import java.time.LocalDateTime;

import guma.Parser;

/**
 * Represents a task that occurs during a specific time period.
 */
public class EventTask extends Task {
    /** String representation of the Start date / time */
    private LocalDateTime startTime;
    /**
     * String representation of the End date / time */
    private LocalDateTime endTime;

    /**
     * Constructs an event task.
     *
     * @param taskName Name of the task.
     * @param from     The start date/time of the event.
     * @param to       The end date/time of the event.
     */
    public EventTask(String taskName, LocalDateTime from, LocalDateTime to) {
        super(taskName);
        this.startTime = from;
        this.endTime = to;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                Parser.dateToString(this.startTime), Parser.dateToString(this.endTime));
    }

    /**
     * Converts the event task into a single-line format for saving to disk.
     *
     * @return A single-line string encoding this event task.
     */
    @Override
    public String toFileString() {
        return String.format("E_%s_%s_%s_%s", getStatus() ? "1" : "0",
                getTaskName(), Parser.dateToSave(this.startTime), Parser.dateToSave(this.endTime));
    }

    /**
     * Returns true when the query date falls within the event's start and end dates.
     *
     * @param queryDate The date to check against.
     * @return {@code true} if the query date is within the event date range, else {@code false}.
     */
    @Override
    public boolean insideSchedule(LocalDate queryDate) {
        return !queryDate.isBefore(this.startTime.toLocalDate()) && !queryDate.isAfter(this.endTime.toLocalDate());
    }
}
