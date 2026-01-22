import java.util.Scanner;
public class Guma {
    /** Separator line for formatting chatbot */
    private static final String SEPARATOR = "\t____________________________________________________________";
    /** Scanner function to read the user input */
    private final Scanner sc = new Scanner(System.in);

    /** Storing the user input */
    private String inp;

    /**
     * The Constructor for Guma chatbot
     */
    public Guma() {}

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
     * Run the chatbot session
     * Print the Greeting message, reads and echos back to the user, until user press "bye"
     * Print the farewell message
     */
    public void run() {
        System.out.println(this.Start());
        while (true){
            inp = sc.nextLine();
            if (inp.toLowerCase().equals("bye")) {
                break;
            }
            System.out.println(this.Echo(inp));
        }
        System.out.println(this.End());
    }

    /**
     * Creates Guma chatbot instance and starts the session.
     */
    public static void main(String... args) {
        Guma bot = new Guma();
        bot.run();
    }
}