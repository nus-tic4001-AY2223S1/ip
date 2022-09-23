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
    }

    /**
     * This method starts the interaction with the user and asks to provide for a user-input so that it can scan the input and provide it to another class for execution purposes.
     * @throws DukeException Error that is thrown if user inputs an incompatible command.
     */
    public static void main() throws DukeException, IOException {
        System.out.println("Hello! I'm Duke\n");
        FilePath.main(); //Confirm current directory / file or choose a different directory / file.
        if (TaskList.UpdatedList().size() == 0) {
            System.out.println("No existing data is found");
        }
        else {
            Parser.parse("list"); //Load initial list onto screen
        }
        System.out.println("\nWhat would you like to do ?");
        System.out.println("List of valid entries include the following:\n\n" +
                "Bye\n"     +
                "List\n"    +
                "Find\n"    +
                "Mark         'X'\n" +
                "Unmark       'X'\n" +
                "Delete       'X'\n" +
                "Todo         'Y'\n" +
                "Event        'Y' /at 'Z'\n" +
                "Deadline     'Y' /by 'Z'\n" +
                "Where 'X' refers to the task number, 'Y' refers to the task description and 'Z' refers to the date using the format YYYY-MM-DD.\n");
        while (true) {
            try {
                command();
                System.out.print("\n");
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
