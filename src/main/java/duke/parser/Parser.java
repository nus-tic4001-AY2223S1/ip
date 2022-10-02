package duke.parser;

import duke.storage.Storage;
import duke.task.Task;
import duke.commands.*;
import duke.exception.DukeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class Parser {
    public static void  parseTaskFromFile(Path pathFile, Storage storage) {
        try {
            File f = pathFile.toFile();
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String line = s.nextLine();
                boolean taskStatus = false;

                if (line.charAt(4) == 'X') {
                    taskStatus = true;
                }

                if (line.startsWith("[T]")) {
                    storage.loadTaskFromFile("todo " + line.substring(7), taskStatus);
                } else if (line.startsWith("[D]")) {
                    storage.loadTaskFromFile("deadline " + line.substring(7, line.indexOf(" (")) + " /by " + line.substring(line.indexOf(" (") + 6, line.indexOf(")")), taskStatus);
                } else if (line.startsWith("[E]")) {
                    storage.loadTaskFromFile("event " + line.substring(7, line.indexOf(" (")) + " /at " + line.substring(line.indexOf(" (") + 6, line.indexOf(")")), taskStatus);
                }
            }
            System.out.println("    Currently, you have " + Task.getTotalTask() + " task(s) in the list.\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static Command parseUserInput(String userInput) throws DukeException {
        String[] firstWord = userInput.split(" ", 2);

        switch (firstWord[0]) {
            case "bye": return new ExitCommand();
            case "list": return new ListCommand();
            case "mark": return new MarkCommand();
            case "unmark": return new UnmarkCommand();
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand();
            case "delete": return new DeleteCommand();
            case "find": return new FindCommand();
            default: throw new DukeException("\u2639 " + "OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
