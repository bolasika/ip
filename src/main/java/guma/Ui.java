package guma;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import guma.command.CommandList;
import guma.task.Task;
import guma.task.TaskList;



/**
 * Handles communication with the user.
 * Provides methods to show messages and read user input.
 */
public class Ui {
    /** Separator line for formatting chatbot (for visual in command-line mode) */
    private static final String SEPARATOR = "\t____________________________________________________________\n";

    /** Scanner function to read the user input */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Show Separator line (for visual in command-line mode)
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
     * A greeting message displayed when the chatbot starts (in command-line mode)
     *
     */
    public void getGreeting() {
        System.out.println("\t Hello! I'm Guma\n\t What can I do for you?");
    }

    /**
     * A farewell message displayed when the chatbot ends (in command-line mode)
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
        String result = tasks.getTaskListing();
        if (result.equals("")) {
            return "SHIOK! NO TASK!";
        }
        return String.format("Wah Sian, tasks again... Here ur tasks: \n%s",
                tasks.getTaskListing());
    }

    /**
     * Notifies the user that a task has been marked as completed.
     * @param taskName  The description of the completed task.
     */
    public String getCompletion(String taskName) {
        return String.format("SHIOK! This task finish liao:\n%s", taskName);
    }

    /**
     * Notifies the user that a task has been marked as not completed.
     * @param taskName  The description of the task.
     */
    public String getUndo(String taskName) {
        return String.format("Wah lao... I cannot believe you UNDO this task:\n%s", taskName);
    }

    /**
     * Notifies the user that a task has been deleted and shows the updated list size.
     * @param taskName  The description of the deleted task.
     * @param size      The current number of tasks in the list.
     */
    public String getDeletion(String taskName, int size) {
        return String.format("Ok delete this task liao:\n %s\n Now you have %s tasks in the list.",
                taskName, size);
    }

    /**
     * Notifies the user that a task has been added and shows the updated list size.
     * @param taskName  The description of the added task.
     * @param size      The current number of tasks in the list.
     */
    public String getAdd(String taskName, int size) {
        return String.format("\tJia lat, got one more task added: \n%s\nNow you have %s tasks in the list liao",
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
     * Notifies the user that the command was not recognized. (in command-line mode)
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
            return "Cannot find la your task. You sure you type correctly ah?";
        } else {
            return String.format("\nAll these are your matching task for:\n%s",
                    message);
        }
    }

    public String getHelpListing() {
        StringBuilder sb = new StringBuilder();
        for (CommandList e : CommandList.values()) {
            sb.append(e.toString() + "\n");
        }
        sb.append("========================\n"
                +
                "Remember my <DateTime> format ah:\n1. d/M/uuuu HHmm\n 2. uuuu-MM-d HHmm\n"
                +
                "========================");
        return sb.toString();
    }

    public String getScheduleListing(TaskList tasks, LocalDate target) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> result = tasks.getScheduleListing(target);
        if (result.isEmpty()) {
            return String.format("SHIOK! Nothing is schedule on: %s\nCAN LEPAK!", target);
        }
        for (int i = 0; i < result.size(); i++) {
            sb.append(String.format("%s. %s\n", i++, result.get(i).toString()));
        }
        return sb.toString();
    }
}
