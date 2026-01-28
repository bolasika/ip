package guma;
import guma.task.TaskList;

import java.util.Scanner;

public class Ui {
    /** Separator line for formatting chatbot */
    private static final String SEPARATOR = "\t____________________________________________________________\n";

    /** Scanner function to read the user input */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Show Separator line
     */
    public void showLine() {
        System.out.print(SEPARATOR);
    }

    /**
     * Read user input
     */
    public String readCommand() {
        return sc.nextLine();
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
    public void getFarewell() {
        System.out.println("\t Bye. Hope to see you again soon!");
    }

    /**
     * Output a formatted list of task stored in Tasklist
     */
    public void getListing(TaskList tasks) {
        System.out.println(String.format("\n\tHere are the tasks in your list:\n%s",
                tasks.getTaskListing()));
    }

    /**
     * Completion Task Message
     */
    public void getCompletion(String taskName) {
        System.out.println(String.format("\tNice! I've marked this task as done:\n\t%s", taskName));
    }

    /**
     * Undo Task Message
     */
    public void getUndo(String taskName) {
        System.out.println(String.format("\tOk, I've marked this task as not done yet:\n\t%s", taskName));
    }

    /**
     * Deletion of Task message
     */
    public void getDeletion(String taskName, int size) {
        System.out.println(String.format("\tNoted, I've removed this task:\n\t %s" +
                        "\n\t Now you have %s tasks in the list",
                taskName, size));
    }

    /**
     * Added of task message
     */
    public void getAdd(String taskName, int size) {
        System.out.println(String.format("\tGot it. I've added this task:\n" + "\t %s\n" +
                        "\tNow you have %s tasks in the list", taskName, size));
    }

    /**
     * Error Message
     */
    public void showError(String message) {
        System.out.println("\t " + message);
    }

    /**
     * Unknown message
     */
    public void getUnknown() {
        System.out.println(SEPARATOR + "\n\t Sorry, I do not recognize the command :-(\n"+SEPARATOR);
    }
}
