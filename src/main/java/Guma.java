import java.util.Scanner;
public class Guma {

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
        return "____________________________________________________________\n" +
                "Hello! I'm Guma\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";
    }

    /**
     * A farewell message displayed when the chatbot ends
     *
     * @return A string representation for farewell
     */
    private String End() {
        return "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
    }

    /**
     * Run the chatbot session
     * Print the Greeting message, then
     * Print the farewell message
     */
    public void run() {
        System.out.println(this.Start());
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

