package guma;

import guma.command.Command;
import guma.exception.GumaException;
import guma.task.TaskList;

public class Guma {
    /** Storage to deal with loading tasks from file and saving tasks in the file */
    private Storage storage;

    /** TaskList to deal with operations with the tasklist */
    private TaskList tasks;

    /** User Interface to deals with interactions with the user */
    private Ui ui;


    /**
     * The Constructor to initialize Guma chatbot instance
     */
    public Guma(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadTask());
    }

    /**
     * Run the chatbot session
     * Print the Greeting message, reads and echos back to the user, until user press "bye"
     * Print the farewell message
     */
    public void run() {
        this.ui.showLine();
        this.ui.getGreeting();
        this.ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                ui.showLine();
                isExit = c.isExit();
            } catch (GumaException e) {
                ui.showError(e.getMessage());
                ui.showLine();
            }
        }

    }

    /**
     * Creates Guma chatbot instance and starts the session.
     */
    public static void main(String... args) {
        Guma bot = new Guma("src/main/data/guma.txt");
        bot.run();
    }
}
