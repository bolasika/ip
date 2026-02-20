package guma.task;

import java.time.LocalDate;

/**
 * Represents a basic task without any specific dates or times.
 */
public class ToDoTask extends Task {

    /**
     * Constructs a todo task.
     *
     * @param taskName Name of the task.
     */
    public ToDoTask(String taskName) {
        super(taskName);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Converts the todo task into a single-line format for saving to disk.
     *
     * @return A single-line string encoding this todo task.
     */
    @Override
    public String toFileString() {
        return "T_" + (getStatus() ? "1" : "0") + "_" + getTaskName();
    }

    /**
     * Returns false because a todo task has no scheduled date.
     *
     * @param queryDate The date to check against.
     * @return {@code false} always.
     */
    @Override
    public boolean insideSchedule(LocalDate queryDate) {
        return false;
    }
}
