import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Guma {
    /** Array to store the Task */
    private ArrayList<Task> tasks;

    /** Separator line for formatting chatbot */
    private static final String SEPARATOR = "\t____________________________________________________________";

    /** Scanner function to read the user input */
    private final Scanner sc = new Scanner(System.in);

    /**
     * The Constructor to initialize Guma chatbot instance with empty tasklist
     */
    public Guma() {
        this.tasks = Storage.loadTask("src/main/data/guma.txt");
        this.tasks = (this.tasks == null) ? new ArrayList<>() : this.tasks;
    }

    /**
     * A greeting message displayed when the chatbot starts
     *
     * @return A string representation for introduction
     */
    private String getGreeting() {
        return SEPARATOR + "\n" +
                "\t Hello! I'm Guma\n" +
                "\t What can I do for you?\n" +
                SEPARATOR;
    }

    /**
     * A farewell message displayed when the chatbot ends
     *
     * @return A string representation for farewell
     */
    private String getFarewell() {
        return SEPARATOR + "\n" +
                "\t Bye. Hope to see you again soon!\n" +
                SEPARATOR;
    }


    /**
     * Append tasks into tasklist then
     *
     * @param inp The unformatted user input
     * @return A string representation to show that the task has been added
     */
    private String addTask(String inp) {
        String[] a = inp.split(" ");
        String taskName = "";
        switch (a[0]) {
            case "todo":
                try {
                    taskName = inp.split("todo ")[1];
                    this.tasks.add(new ToDoTask(taskName));
                }
                catch (Exception e) {
                    return SEPARATOR + "\n" +
                            "\tERROR! Syntax: todo <taskname>\n" +
                            SEPARATOR;
                }
                break;
            case "deadline":
                try {
                    taskName = inp.split("deadline ")[1].split(" /by")[0];
                    String description = inp.split("/by ")[1];
                    this.tasks.add(new DeadlineTask(taskName, description));
                } catch (Exception e) {
                    return SEPARATOR + "\n" +
                            "\tERROR! Ensure your Syntax: deadline <taskname> /by <description>\n" +
                            SEPARATOR;
                }
                break;
            case "event":
                try {
                    taskName = inp.split("event ")[1].split(" /from")[0];
                    String fromTime = inp.split(" /from ")[1].split(" /to ")[0];
                    String toTime = inp.split("/to ")[1];
                    this.tasks.add(new EventTask(taskName, fromTime, toTime));
                } catch (Exception e) {
                    return SEPARATOR + "\n" +
                            "\tERROR! Ensure your Syntax: event <taskname> /from <Start time> /to <End time>\n" +
                            SEPARATOR;
                }

        }

        return String.format("%s\n" +
                        "\tGot it. I've added this task:\n" +
                        "\t %s\n" +
                        "\tNow you have %s tasks in the list\n" +
                        "%s", SEPARATOR, this.tasks.get(this.tasks.size()-1),
                this.tasks.size(), SEPARATOR);
    }

    /**
     * Output a formatted list of task stored in Tasklist
     *
     * @return A string representation of the tasks
     */
    private String getListing() {
        String b = SEPARATOR + "\n" + "\tHere are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            b += String.format("\t%s. %s\n", i + 1, tasks.get(i));
        }
        b += SEPARATOR;
        return b;
    }

    /**
     * Mark a task as complete
     *
     * @param idx The index that the Task is located in Tasklist
     * @return A string representation to inform that the task has been completed
     */
    private String completeTask(int idx) {
        this.tasks.get(idx - 1).complete();
        return String.format("%s\n\tNice! I've marked this task as done:\n\t%s\n%s",SEPARATOR,
                this.tasks.get(idx - 1).toString(), SEPARATOR);
    }

    /**
     * Mark a task as incomplete
     *
     * @param idx The index that the Task is located in Tasklist
     * @return A string representation to inform that the task has been undo
     */
    private String undoTask(int idx) {
        this.tasks.get(idx - 1).undo();
        return String.format("%s\n\tOk, I've marked this task as not done yet:\n\t%s\n%s",SEPARATOR,
                this.tasks.get(idx - 1).toString(), SEPARATOR);
    }

    /**
     * Delete a task in the tasklist
     *
     * @param idx The idx of the Task that should be deleted in the tasklist
     * @return A string representation to inform user that the task has been removed
     */
    private String deleteTask(int idx) {
        String taskToRemove = this.tasks.get(idx - 1).toString();
        this.tasks.remove(idx - 1);
        return String.format("%s\n\tNoted, I've removed this task:\n\t %s" +
                        "\n\t Now you have %s tasks in the list\n%s",
                SEPARATOR,
                taskToRemove,
                this.tasks.size(),
                SEPARATOR);
    }

    /**
     * Run the chatbot session
     * Print the Greeting message, reads and echos back to the user, until user press "bye"
     * Print the farewell message
     */
    public void run() {
        System.out.println(this.getGreeting());
        String action = "";
        do {
            String inp = sc.nextLine();
            action = inp.split(" ")[0].toLowerCase();
            switch (action) {
                case "bye":
                    Storage.saveTask(tasks, "src/main/data/guma.txt");
                    System.out.println(this.getFarewell());
                    break;
                case "list":
                    System.out.println(getListing());
                    break;
                case "mark":
                    System.out.println(completeTask(Integer.parseInt(inp.split(" ")[1])));
                    break;
                case "unmark":
                    System.out.println(undoTask(Integer.parseInt(inp.split(" ")[1])));
                    break;
                case "todo":
                case "deadline":
                case "event":
                    System.out.println(addTask(inp));
                    break;
                case "delete":
                    System.out.println(deleteTask(Integer.parseInt(inp.split(" ")[1])));
                    break;
                default:
                    System.out.println(SEPARATOR + "\n\t Sorry, I do not recognize the command :-(\n"+SEPARATOR);
                    break;
            }
        } while (!action.equals("bye"));

    }

    /**
     * Creates Guma chatbot instance and starts the session.
     */
    public static void main(String... args) {
        Guma bot = new Guma();
        bot.run();
    }
}
