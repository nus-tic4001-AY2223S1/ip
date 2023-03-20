import duke.storage.Storage;
import duke.parser.Parser;
import duke.task.*;
import duke.commands.Command;
import duke.exception.DukeException;
import java.io.*;
import java.util.ArrayList;

/**
 * This <code>Duke</code> class is the main class of the Duke application.
 * This includes the main method and other methods that taps into abstracted
 * classes.
 */
public class Duke {
    private static File file;
    private static ArrayList<Task> taskList;
    private Storage storage;

    public String setWorkingFile(String filename) {
        String home = System.getProperty("user.home");
        java.nio.file.Path directory = java.nio.file.Paths.get(home, "Duke");
        java.nio.file.Path pathFile = java.nio.file.Paths.get(home, "Duke", filename + ".txt");
        file = pathFile.toFile();

        taskList = new ArrayList<>();
        storage = new Storage(directory, pathFile, taskList);

        return Parser.parseTaskFromFile(pathFile, storage);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        try {
            if (input.startsWith("sf")) {
                return setWorkingFile(input.substring(3));
            } else if (file == null) {
                return "Please set the working file first!";
            } else {
                Command c = Parser.parseUserInput(input);
                return c.execute(input, taskList, storage, file);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}