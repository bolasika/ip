import java.util.*;
public class Guma {
    /** Array to store the Task */
    private ArrayList<Task> tasklist;

    /** Separator line for formatting chatbot */
    private static final String SEPARATOR = "\t____________________________________________________________";

    /** Scanner function to read the user input */
    private final Scanner sc = new Scanner(System.in);

    /**
     * The Constructor to initialize Guma chatbot instance with empty tasklist
     */
    public Guma() {
        this.tasklist = new ArrayList<Task>();
    }

    /**
     * A greeting message displayed when the chatbot starts
     *
     * @return A string representation for introduction
     */
    private String Start() {
        return SEPARATOR + "\n" +
                "\tHello! I'm Guma\n" +
                "\tWhat can I do for you?\n" +
                SEPARATOR;
    }

    /**
     * A farewell message displayed when the chatbot ends
     *
     * @return A string representation for farewell
     */
    private String End() {
        return SEPARATOR + "\n" +
                "\tBye. Hope to see you again soon!\n" +
                SEPARATOR;
    }

    /**
     * Output the commands entered by the user
     *
     * @param userInp The input that user entered from run() function
     * @return A string representation for user's task list
     */
    private String Echo(String userInp) {
        return String.format("%s\n" +
                "\t%s\n" +
                "%s", SEPARATOR, userInp, SEPARATOR);
    }


    /**
     * Append tasks into tasklist then
     *
     * @param taskName The name of the task to be appended into tasklist
     * @return A string representation to show that the task has been added
     */
    private String addTask(String inp) {
        String[] taskInp = inp.split(" ");
        String taskName = "";
        switch (taskInp[0]) {
            case "todo":
                taskName = inp.split("todo ")[1];
                this.tasklist.add(new ToDoTask(taskName));
                break;
            case "deadline":
                taskName = inp.split("deadline ")[1].split(" /by")[0];
                String description = inp.split("/by ")[1];
                this.tasklist.add(new DeadlineTask(taskName, description));
                break;
            case "event":
                taskName = inp.split("event ")[1].split(" /from")[0];
                String fromTime = inp.split(" /from ")[1].split(" /to ")[0];
                String toTime = inp.split("/to ")[1];
                this.tasklist.add(new EventTask(taskName, fromTime, toTime));

        }

        return String.format("%s\n" +
                "\tGot it. I've added this task:\n" +
                "\t %s\n" +
                "\tNow you have %s tasks in the list\n" +
                "%s", SEPARATOR, this.tasklist.get(this.tasklist.size()-1),
                this.tasklist.size(), SEPARATOR);
    }

    /**
     * Output a formatted list of task stored in Tasklist
     *
     * @return A string representation of the tasks
     */
    private String list() {
        String tmp = SEPARATOR + "\n" + "\tHere are the tasks in your list:\n";
        for (int i = 0; i < tasklist.size(); i++) {
            tmp += String.format("\t%s. %s\n", i + 1, tasklist.get(i));
        }
        tmp += SEPARATOR;
        return tmp;
    }

    /**
     * Mark a task as complete
     *
     * @param idx The index that the Task is located in Tasklist
     * @return A string representation to inform that the task has been completed
     */
    private String completeTask(int idx) {
        this.tasklist.get(idx - 1).complete();
        return String.format("%s\n\tNice! I've marked this task as done:\n\t%s\n%s",SEPARATOR,
                this.tasklist.get(idx - 1).toString(), SEPARATOR);
    }

    /**
     * Mark a task as incomplete
     *
     * @param idx The index that the Task is located in Tasklist
     * @return A string representation to inform that the task has been undo
     */
    private String undoTask(int idx) {
        this.tasklist.get(idx - 1).undo();
        return String.format("%s\n\tOk, I've marked this task as not done yet:\n\t%s\n%s",SEPARATOR,
                this.tasklist.get(idx - 1).toString(), SEPARATOR);
    }

    /**
     * Run the chatbot session
     * Print the Greeting message, reads and echos back to the user, until user press "bye"
     * Print the farewell message
     */
    public void run() {
        System.out.println(this.Start());
        String action = "";
        do {
            String inp = sc.nextLine();
            action = inp.split(" ")[0].toLowerCase();
            switch (action) {
                case "bye":
                    System.out.println(this.End());
                    break;
                case "list":
                    System.out.println(list());
                    break;
                case "mark":
                    System.out.println(completeTask(Integer.parseInt(inp.split(" ")[1])));
                    break;
                case "unmark":
                    System.out.println(undoTask(Integer.parseInt(inp.split(" ")[1])));
                    break;
                default:
                    System.out.println(addTask(inp));
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
