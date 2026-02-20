package guma.task;

import java.time.LocalDate;
import java.util.ArrayList;

import guma.Storage;
import guma.exception.GumaException;

/**
 * Manages a list of tasks.
 * Provides methods to add, delete, complete, and undo tasks.
 */
public class TaskList {
    /** Separator line for formatting chatbot */
    private static final String SEPARATOR = "\t____________________________________________________________";
    /** Array to store the Task */
    private ArrayList<Task> tasks;

    /**
     * Initializes the task list with existing tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Marks a task as complete.
     *
     * @param idx The 1-based index of the task in the task list.
     * @return A string representation of the task that was marked completed.
     */
    public String completeTask(int idx) {
        this.tasks.get(idx - 1).complete();
        return this.tasks.get(idx - 1).toString();
    }

    /**
     * Marks a task as incomplete.
     *
     * @param idx The 1-based index of the task in the task list.
     * @return A string representation to confirm the task was unmarked.
     */
    public String undoTask(int idx) {
        this.tasks.get(idx - 1).undo();
        return this.tasks.get(idx - 1).toString();
    }

    /**
     * Deletes a task from the task list.
     *
     * @param idx The 1-based index of the task to delete.
     * @return A string representation of the deleted task.
     */
    public String deleteTask(int idx) {
        String taskToRemove = this.tasks.get(idx - 1).toString();
        this.tasks.remove(idx - 1);
        return taskToRemove;
    }

    /**
     * Returns the size of the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added into the list.
     * @return A string representation of the added task.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return task.toString();
    }

    /**
     * Returns a formatted list of all tasks.
     *
     * @return A string representation of the tasks.
     */
    public String getTaskListing() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("%s. %s\n", i + 1, tasks.get(i)));
        }
        return sb.toString();
    }

    /**
     * Saves the current task list to the provided storage.
     *
     * @param storage The storage to save to.
     * @return true if save was successful, false otherwise.
     */
    public boolean saveTo(Storage storage) {
        if (!storage.saveTask(this.tasks)) {
            throw new GumaException("Cannot save task");
        }
        return storage.saveTask(this.tasks);
    }

    /**
     * Finds and returns a formatted list of tasks that contain the given keyword.
     *
     * @param taskName The keyword to search for in task names.
     * @return A formatted string output for the tasks; if no result, empty string.
     */
    public String getFindTaskListing(String taskName) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, c = 1; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskName().contains(taskName)) {
                sb.append(String.format("\t%s. %s\n", c, tasks.get(i)));
                c += 1;
            }
        }
        return sb.toString();
    }

    /**
     * Returns tasks that are scheduled on or within the target date.
     *
     * @param target The date to check against each task's schedule.
     * @return A list of tasks that match the target date.
     */
    public ArrayList<Task> getScheduleListing(LocalDate target) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> scheduledTasks = new ArrayList<>();
        for (Task eachTask : this.tasks) {
            if (eachTask.insideSchedule(target)) {
                scheduledTasks.add(eachTask);
            }
        }
        return scheduledTasks;
    }

}
