public class Guma {

    // Constructor
    public Guma() {}

    public String Start() {
        return "____________________________________________________________\n" +
                "Hello! I'm Guma\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";
    }

    public String End() {
        return "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
    }

    public static void main(String... args) {
        Guma bot = new Guma();
        System.out.println(bot.Start());
        System.out.println(bot.End());
    }
}

