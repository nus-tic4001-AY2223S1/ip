package command;
import task.DukeException;
import task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class Ui {
    public static void command() throws IOException, DukeException {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        Parser.parse(line);
        Storage.writeToFile();
        System.out.println("\n");
    }
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
