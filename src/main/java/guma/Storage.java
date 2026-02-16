package guma;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import guma.task.DeadlineTask;
import guma.task.EventTask;
import guma.task.Task;
import guma.task.ToDoTask;

/**
 * Handles loading and storing task data from text file on disk
 * Each line in the text file represent a single task.
 * Each task parameters are separated using underscore ("_") separators
 */
public class Storage {
    /** The path to the file where tasks are stored. */
    private String filePath;

    /**
     * Constructor for Storage:
     * Initializes the storage with the specified file path.
     * @param filePath Path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the given file path and return them as ArrayList
     * @return An {@link ArrayList} of loaded {@link Task} objects, or null if the file is not found.
     */
    public ArrayList<Task> loadTask() {
        try {
            File f = new File(this.filePath);
            return getTasksFromLocal(f);
        } catch (FileNotFoundException e) {
            System.out.println("Unable in reading file");
            return null;
        } catch (IOException e) {
            System.out.println("Unable to read/create data file");
            return null;
        }
    }


    /**
     * Returns tasks loaded from the given file, creating the file (and parent directories) if needed.
     * @param f The data file to read from.
     * @return An {@link ArrayList} of tasks, empty if the file did not previously exist.
     * @throws IOException If the file cannot be created or read.
     */
    private static ArrayList<Task> getTasksFromLocal(File f) throws IOException {
        if (!f.exists()) {
            createParentFiles(f);
            return new ArrayList<Task>();
        }
        return getTaskArrayList(f);
    }

    /**
     * Ensures the parent directory exists (if any) and creates the data file.
     * @param f The data file to create.
     * @throws IOException If the directory or file cannot be created.
     */
    private static void createParentFiles(File f) throws IOException {
        File parent = f.getParentFile();
        // If the path include parent, but parent do not exist:
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        f.createNewFile();
    }

    /**
     * Reads all tasks from the given file into a list.
     * @param f The data file to read from.
     * @return An {@link ArrayList} of parsed tasks.
     * @throws FileNotFoundException If the file does not exist.
     */
    private static ArrayList<Task> getTaskArrayList(File f) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(f);
        Task t;
        while (sc.hasNext()) {
            loadEachTask(sc, tasks);
        }
        sc.close();
        return tasks;
    }

    /**
     * Parses one line from the scanner and adds the corresponding task to the list.
     * @param sc The scanner positioned at the next task line.
     * @param tasks The list to append the parsed task to.
     */
    private static void loadEachTask(Scanner sc, ArrayList<Task> tasks) {
        Task t;
        String a = sc.nextLine();
        String[] b = a.split("_");
        switch (b[0]) {
        case "T":
            t = new ToDoTask(b[2]);
            if ("1".equals(b[1])) {
                t.complete();
            }
            tasks.add(t);
            break;
        case "D":
            t = new DeadlineTask(b[2], Parser.dateChecker(b[3]));
            if ("1".equals(b[1])) {
                t.complete();
            }
            tasks.add(t);
            break;
        case "E":
            t = new EventTask(b[2], Parser.dateChecker(b[3]), Parser.dateChecker(b[4]));
            if ("1".equals(b[1])) {
                t.complete();
            }
            tasks.add(t);
            break;
        default:
            break;
        }
    }

    /**
     * Saves the given list of tasks to the data file at the specified file path.
     * Each line in the text file represent a single task.
     * Each task parameters are separated using underscore ("_") separators
     * @param tasks The {@link ArrayList} of task to be saved.
     * @return {@code true} if the tasks were successfully written to disk, otherwise {@code false}.
     */
    public boolean saveTask(ArrayList<Task> tasks) {
        try {
            return savingTasksToLocal(tasks);
        } catch (IOException e) {
            System.out.println("Unable to read/create data file");
            return false;
        }
    }

    /**
     * Writes all tasks to the data file in the storage path.
     * @param tasks The list of tasks to write.
     * @return {@code true} if the write succeeds.
     * @throws IOException If the file cannot be created or written.
     */
    private boolean savingTasksToLocal(ArrayList<Task> tasks) throws IOException {
        StringBuilder sb = new StringBuilder();
        FileWriter fw = new FileWriter(this.filePath);
        tasks.stream()
             .forEach(eachTask -> sb.append(String.format("%s\n", eachTask.toFileString())));
        fw.write(sb.toString());
        fw.close();
        return true;
    }

}
