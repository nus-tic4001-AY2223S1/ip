import duke.ui.UI;
import duke.storage.Storage;
import duke.parser.Parser;
import duke.task.*;
import duke.commands.Command;
import duke.exception.DukeException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This <code>Duke</code> class is the main class of the Duke application.
 * This includes the main method and other methods that taps into abstracted
 * classes.
 */
public class Duke {
    private static File file;
    private UI ui;
    private static ArrayList<Task> taskList;
    private Storage storage;

    public Duke(String filename) {
        String home = System.getProperty("user.home");
        java.nio.file.Path directory = java.nio.file.Paths.get(home, "Duke");
        java.nio.file.Path pathFile = java.nio.file.Paths.get(home, "Duke", filename + ".txt");
        file = pathFile.toFile();

        ui = new UI();
        ui.showWelcome();

        taskList = new ArrayList<>();
        storage = new Storage(directory, pathFile, taskList);

        Parser.parseTaskFromFile(pathFile, storage);
    }

    public void run() {
        while (!Command.isExit) {
            try {
                String userInput = ui.readUserInput();
                ui.showLine();
                Command c = Parser.parseUserInput(userInput);
                c.execute(userInput, taskList, storage, file);
                c.setIsExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("\nTo begin, enter your filename: \n");

        Scanner in = new Scanner(System.in);
        String filename = in.nextLine();

        System.out.println("__________________________________________________");

        new Duke(filename).run();
    }
}