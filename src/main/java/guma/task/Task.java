package guma.task;

import java.time.LocalDate;


/**
 * Represents a generic task with a name and completion status.
 * Concrete subclasses provide scheduling details and storage formats.
 */
public abstract class Task {
    /** Name of the Task */
    private String taskName;

    /** Boolean status of the task */
    private boolean isDone;

    /**
     * Constructs a task with the given name.
     *
     * @param taskName Name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Marks the task as complete.
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void undo() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is marked as complete.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return The task name.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s",
                this.isDone ? "X" : " ", taskName);
    }

    /**
     * Returns a single-line string representation of this task for saving to the data file.
     *
     * @return A single-line string representation suitable for file storage.
     */
    public abstract String toFileString();

    /**
     * Checks whether the given date is within this task's schedule.
     *
     * @param date The date to check against this task's schedule.
     * @return {@code true} if the date falls within this task's schedule, else {@code false}.
     */
    public abstract boolean insideSchedule(LocalDate date);

}
