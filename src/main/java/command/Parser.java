package command;

import task.TaskList;
import task.DukeException;

import java.io.IOException;
import java.time.LocalDate;

/**
 * This class registers the user-inputs and makes sense of the command that the program is supposed to execute.
 * <br> It contains the following approved actions:
 * <ol>
 *     <li>List: Allows to list all the tasks inside TaskList</li>
 *     <li>Bye: Exits the Task Manager</li>
 *     <li>Done: Marks a task as completed</li>
 *     <li>Delete: Deletes a task from the TaskList</li>
 *     <li>Todo / Event / Deadline: Adds one of the following tasks into the TaskList</li>
 * </ol>
 * <p>
 * Once it understand what the user-input is referring to, it passes the appropriate command to the TaskList for execution.
 */
public class Parser {
    protected static String by;
    protected static LocalDate localDate;

    /**
     * This method takes in a user-input as a String, makes sense of the command and returns a command to the TaskList for execution or
     * returns a relevant Date.
     * @param input Provides the user-input field to be parsed.
     * @throws DukeException Error if user-input is in an incompatible format.
     */
    public static LocalDate parse(String input) throws DukeException, IOException {
        boolean change = false;
        if (input.trim().equalsIgnoreCase("bye")) {
            TaskList.Bye();
        } else if (input.trim().equalsIgnoreCase("list")) {
            TaskList.List();
        } else if (input.toLowerCase().contains("unmark")) {
            TaskList.Unmark(input);
            change = true;
        } else if (input.toLowerCase().contains("mark")) {
            TaskList.Mark(input);
            change = true;
        } else if (input.toLowerCase().contains("find")) {
            TaskList.Find(input.substring(5));
        } else if (input.toLowerCase().contains("delete")) {
            TaskList.Delete(input);
            change = true;
        } else if (input.toLowerCase().contains("todo")) {
            TaskList.Todo(input);
            change = true;
        } else if ((input.toLowerCase().contains("deadline")) || (input.toLowerCase().contains("event")) ||
                (input.contains("[D]")) || (input.contains("[E]"))) {

            int n = 0;

            if (input.contains("(")) {n = input.indexOf('('); }
            else if (input.contains("/")) {n = input.indexOf('/'); }

            by = input.substring(n + 4).trim();
            localDate = LocalDate.parse(by);

            if (localDate == null) {
                throw new DukeException("Either incorrect format of input or no known date format found: " + by);
            }

            if (input.toLowerCase().contains("deadline"))           { TaskList.Deadline(input, localDate); change = true;}
            else if (input.toLowerCase().contains("event"))         { TaskList.Event(input,localDate); change = true;}
            else return localDate;
        }
        else throw new DukeException("Error: Please enter a valid task description\n");
        if (change) { Storage.writeToFile();} //Write to file and add change to History
        return null;
    }
}
