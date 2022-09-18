package command;
import task.DukeException;
import task.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class is to represent the user-interface of the program. It displays an introductory message and provides a list of possible commands that the user can input.
 * <br> Once the input is registered, it refers the input to the Parser Class to parse and understand the command that the user wants.
 */
public class Ui {
    public static void command() throws IOException, DukeException {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        Parser.parse(line);
        Storage.writeToFile();
        System.out.println("\n");
    }

    /**
     * This method starts the interaction with the user and asks to provide for a user-input so that it can scan the input and provide it to another class for execution purposes.
     * @throws DukeException Error that is thrown if user inputs an incompatible command.
     */
    public static void main() throws DukeException {
        System.out.println("Hello! I'm Duke\n" + "Let me load the existing data for you (if any)\n");
        if (TaskList.UpdatedList().size() == 0) {
            System.out.println("No existing data is found");
        }
        else {
            Parser.parse("list");
        }
        System.out.println("What would you like to do ?\n");
        while (true) {
            try {
                command();
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
