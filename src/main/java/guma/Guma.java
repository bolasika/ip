package guma;

import guma.command.Command;
import guma.exception.GumaException;
import guma.task.TaskList;

/**
 * Main class for the Guma chatbot application. Test
 */
public class Guma {
    /** Storage to deal with loading tasks from file and saving tasks in the file */
    private Storage storage;

    /** TaskList to deal with operations with the tasklist */
    private TaskList tasks;

    /** User Interface to deals with interactions with the user */
    private Ui ui;

    /**
     * Constructs a Guma chatbot instance with the given storage file path.
     * @param filePath Path to the data file used for loading and saving tasks.
     */
    public Guma(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.loadTask());
    }

    /**
     * Runs the chatbot session.
     * Prints the greeting, reads and executes user commands, and ends when "bye" is issued.
     */
    public void run() {
        this.ui.showLine();
        this.ui.getGreeting();
        this.ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            isExit = parsingCommands(isExit);
        }
    }

    /**
     * Reads one command, executes it, and returns whether the session should exit.
     * @param isExit Current exit flag before processing this command.
     * @return {@code true} if the last command requests exit, otherwise {@code false}.
     */
    private boolean parsingCommands(boolean isExit) {
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
        return isExit;
    }

    /**
     * Creates Guma chatbot instance and starts the session.
     * @param args Command-line arguments (not used).
     */
    public static void main(String... args) {
        Guma bot = new Guma("src/main/data/guma.txt");
        bot.run();
    }

    /**
     * Generates a response for the given user input in GUI
     * @param input The user input string.
     * @return The chatbot response text.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (GumaException e) {
            return e.getMessage();
        }
    }
}
