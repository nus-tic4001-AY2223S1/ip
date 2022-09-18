package duke.workingfile;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkingFile {
    private Path myDirectory;
    private Path myPathFile;
    private ArrayList<Task> myTaskList;

    public WorkingFile(Path directory, Path pathFile, ArrayList<Task> taskList) {
        myDirectory = directory;
        myPathFile = pathFile;
        myTaskList = taskList;

        try {
            File dir = new File(String.valueOf(directory));
            if (!dir.exists()) dir.mkdirs();
            Files.createFile(pathFile);
        } catch (IOException e) {
            System.out.println("\n    File already exists at " + e.getMessage() + ". I will proceed to use this as the working file and update accordingly.\n");
        }
    }

    public void loadTaskFromFile() {
        try {
            File f = myPathFile.toFile();
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String line = s.nextLine();
                boolean taskStatus = false;

                if (line.charAt(4) == 'X') {
                    taskStatus = true;
                }

                if (line.startsWith("[T]")) {
                    loadTask("todo " + line.substring(7), taskStatus);
                } else if (line.startsWith("[D]")) {
                    loadTask("deadline " + line.substring(7, line.indexOf(" (")) + " /by " + line.substring(line.indexOf(" (") + 6), taskStatus);
                } else if (line.startsWith("[E]")) {
                    loadTask("event " + line.substring(7, line.indexOf(" (")) + " /at " + line.substring(line.indexOf(" (") + 6), taskStatus);
                }
            }
            System.out.println("    Currently, you have " + Task.getTotalTask() + " task(s) in the list.\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public void loadTask(String s, boolean status) {
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
}
