package duke.storage;
import duke.task.Task;
import duke.task.TodoTask;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.exception.DukeException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private Path myDirectory;
    private Path myPathFile;
    private ArrayList<Task> myTaskList;

    public Storage(Path directory, Path pathFile, ArrayList<Task> taskList) {
        myDirectory = directory;
        myPathFile = pathFile;
        myTaskList = taskList;

        try {
            File dir = new File(String.valueOf(directory));
            if (!dir.exists()) dir.mkdirs();
            Files.createFile(pathFile);
        } catch (IOException e) {
            System.out.println("    Your file already exists at " + e.getMessage() + ".\n    I will proceed to use this as the working file and update accordingly.\n");
        }
    }

    public void loadTaskFromFile(String s, boolean status) {
        String description;
        String[] firstWord = s.split(" ", 2);

        switch (firstWord[0]) {
            case "todo": {
                description = s.substring(5);

                myTaskList.add(new TodoTask(description, status));
                break;
            }
            case "deadline": {
                description = s.substring(9, s.indexOf(" /by"));
                String by = s.substring(s.indexOf("/by") + 4);

                myTaskList.add(new DeadlineTask(description, status, by));
                break;
            }
            case "event": {
                description = s.substring(6, s.indexOf(" /at"));
                String at = s.substring(s.indexOf("/at") + 4);

                myTaskList.add(new EventTask(description, status, at));
                break;
            }
        }
    }

    public void updateTaskFile(ArrayList<Task> taskList, File file) throws DukeException {
        try {
            PrintWriter pw = new PrintWriter(file);

            pw.print("");
            pw.close();

            for (int i = 0; i < taskList.size(); i++) {
                saveTaskToFile(taskList.get(i), file);
            }
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    public void saveTaskToFile(Task task, File file) throws DukeException {
        try {
            File f = file;
            Scanner s = new Scanner(f);
            FileWriter fw = new FileWriter(file, true);
            boolean taskFound = false;

            while (s.hasNext()) {
                if (s.nextLine().equals(task.toString())) {
                    taskFound = true;
                    break;
                }
            }

            if (!taskFound) {
                fw.write(task + "\n");
                fw.close();
            }
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }
}
