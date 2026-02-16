package guma;
import java.util.Scanner;

import guma.task.TaskList;



/**
 * Handles communication with the user.
 * Provides methods to show messages and read user input.
 */
public class Ui {
    /** Separator line for formatting chatbot */
    private static final String SEPARATOR = "\t____________________________________________________________\n";

    /** Scanner function to read the user input */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Show Separator line
     */
    public void showLine() {
        System.out.print(SEPARATOR);
    }

    /**
     * Reads the input from the user.
     * @return The string entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
    /**
     * A greeting message displayed when the chatbot starts
     *
     */
    public void getGreeting() {
        System.out.println("\t Hello! I'm Guma\n\t What can I do for you?");
    }

    /**
     * A farewell message displayed when the chatbot ends
     *
     */
    public String getFarewell() {
        return "\t Bye. Hope to see you again soon!";
    }

    /**
     * Prints the list of tasks currently in the task list.
     * @param tasks     The task list containing tasks to be displayed.
     */
    public String getListing(TaskList tasks) {
        return String.format("\n\tHere are the tasks in your list:\n%s",
                tasks.getTaskListing());
    }

    /**
     * Notifies the user that a task has been marked as completed.
     * @param taskName  The description of the completed task.
     */
    public String getCompletion(String taskName) {
        return String.format("\tNice! I've marked this task as done:\n\t%s", taskName);
    }

    /**
     * Notifies the user that a task has been marked as not completed.
     * @param taskName  The description of the task.
     */
    public String getUndo(String taskName) {
        return String.format("\tOk, I've marked this task as not done yet:\n\t%s", taskName);
    }

    /**
     * Notifies the user that a task has been deleted and shows the updated list size.
     * @param taskName  The description of the deleted task.
     * @param size      The current number of tasks in the list.
     */
    public String getDeletion(String taskName, int size) {
        return String.format("\tNoted, I've removed this task:\n\t %s\n\t Now you have %s tasks in the list",
                taskName, size);
    }

    /**
     * Notifies the user that a task has been added and shows the updated list size.
     * @param taskName  The description of the added task.
     * @param size      The current number of tasks in the list.
     */
    public String getAdd(String taskName, int size) {
        return String.format("\tGot it. I've added this task:\n\t %s\n\tNow you have %s tasks in the list",
                taskName, size);
    }

    /**
     * Displays an error message to the user.
     * @param message   The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("\t " + message);
    }

    /**
     * Notifies the user that the command was not recognized.
     */
    public void getUnknown() {
        System.out.println(SEPARATOR + "\n\t Sorry, I do not recognize the command :-(\n" + SEPARATOR);
    }

    /**
     * Prints the list of tasks that matches the user input
     * @param message   The formatted String representation of the tasks to be displayed
     */
    public String getFindListing(String message) {
        if (message.isEmpty()) {
            return "\t Unable to find any matching tasks in your list.";
        } else {
            return String.format("\n\tHere are the matching tasks in your list:\n%s",
                    message);
        }
    }
}
