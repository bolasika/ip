package guma;

import guma.task.Task;
import guma.task.ToDoTask;
import guma.task.DeadlineTask;
import guma.task.EventTask;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * Handles loading and storing task data from text file on disk
 * Each line in the text file represent a single task.
 * Each task parameters are separated using underscore ("_") separators
 */
public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the given file path and return them as ArrayList
     * @return An {@link ArrayList} of loaded {@link Task} objects, or null if the file is not found.
     */
    public ArrayList<Task> loadTask() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            File f = new File(this.filePath);

            // If the file do not exist, create the file and return empty ArrayList
            if (!f.exists()) {
                File parent = f.getParentFile();
                // If the path include parent, but parent do not exist:
                if (parent != null && !parent.exists()) {
                    parent.mkdirs();
                }
                f.createNewFile();
                return tasks;
            }

            Scanner sc = new Scanner(f);
            Task t;
            while (sc.hasNext()) {
                String a = sc.nextLine();
                String[] b = a.split("_");
                switch (b[0]){
                    case "T":
                        t = new ToDoTask(b[2]);
                        if ("1".equals(b[1])) {
                            t.complete();
                        }
                        tasks.add(t);
                        break;
                    case "D":
                        t = new DeadlineTask(b[2], b[3]);
                        if ("1".equals(b[1])) {
                            t.complete();
                        }
                        tasks.add(t);
                        break;
                    case "E":
                        t = new EventTask(b[2], b[3], b[4]);
                        if ("1".equals(b[1])) {
                            t.complete();
                        }
                        tasks.add(t);
                        break;
                    default:
                        break;
                }
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("Unable in reading file");
            return null;
        } catch (IOException e) {
            System.out.println("Unable to read/create data file");
            return null;
        }
    }

    /**
     * Saves the given list of tasks to the data file at the specified file path.
     * Each line in the text file represent a single task.
     * Each task parameters are separated using underscore ("_") separators
     * @param tasks The {@link ArrayList} of task to be saved
     * @return {@code true} if the tasks were successfully written to disk, otherwise {@code false}.
     */
    public boolean saveTask(ArrayList<Task> tasks) {
        try {
            StringBuilder sb = new StringBuilder();
            FileWriter fw = new FileWriter(this.filePath);
            for (Task t : tasks) {
                sb.append(String.format("%s\n", t.toFileString()));
            }
            fw.write(sb.toString());
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Unable to read/create data file");
            return false;
        }
    }

}
