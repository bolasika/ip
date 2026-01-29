package guma.task;

import java.util.ArrayList;

import guma.Storage;
/**
 * Manages a list of tasks.
 * Provides methods to add, delete, complete, and undo tasks.
 */
public class TaskList {
    /** Array to store the Task */
    private ArrayList<Task> tasks;

    /** Separator line for formatting chatbot */
    private static final String SEPARATOR = "\t____________________________________________________________";

    /**
     * Initializes the task list with existing tasks.
     *
     * @param tasks The initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Mark a task as complete
     *
     * @param idx The index that the Task is located in Tasklist
     * @return A string representation of the task that was marked completed
     */
    public String completeTask(int idx) {
        this.tasks.get(idx - 1).complete();
        return this.tasks.get(idx - 1).toString();
    }

    /**
     * Mark a task as incomplete
     *
     * @param idx The index that the Task is located in Tasklist
     * @return A string representation to inform that the task has been undo
     */
    public String undoTask(int idx) {
        this.tasks.get(idx - 1).undo();
        return this.tasks.get(idx - 1).toString();
    }

    /**
     * Delete a task in the tasklist
     *
     * @param idx The idx of the Task that should be deleted in the tasklist
     * @return A string representation to inform user that the task has been removed
     */
    public String deleteTask(int idx) {
        String taskToRemove = this.tasks.get(idx - 1).toString();
        this.tasks.remove(idx - 1);
        return taskToRemove;
    }

    /**
     * Return the size of the ArrayList tasks
     *
     * @return an Integer representing the size of the ArrayList Tasks
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Append tasks into tasklist then
     *
     * @param task Task to be added into the ArrayList task
     * @return A string representation to show that the task has been added
     */
    public String addTask(Task task) {
        tasks.add(task);
        return task.toString();
    }

    /**
     * Output a formatted list of task stored in ArrayList tasks
     *
     * @return A string representation of the tasks
     */
    public String getTaskListing() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("\t%s. %s\n", i + 1, tasks.get(i)));
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
        return storage.saveTask(this.tasks);
    }

    /**
     * Find and return the formatted list of tasks that contains the user input
     *
     * @param taskName  The keyword to be searched in the tasklist
     * @return A formatted string output for the tasks; if no result, empty string
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

}
