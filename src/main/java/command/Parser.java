package command;

import task.TaskList;
import task.DukeException;

/**
 * This class registers the user-inputs and makes sense of the command that the program is supposed to execute.
 * <br> It contains the following approved actions:
 * <ol>
 *     <li>List: Allows to list all the tasks inside TaskList</li>
 *     <li>Bye: Exits the Task Manager</li>
 *     <li>Done: Marks a task as completed</li>
 *     <li>Delete: Deletes a task from the TaskList</li>
 *     <li>Todo / Event / Deadline: Adds one of the following tasks into the TaskList</li>
 *     <li>Occurrence: Lists which tasks fall on a specified date</li>
 * </ol>
 * <p>
 * Once it understand what the user-input is referring to, it passes the appropriate command to the TaskList for execution.
 */
public class Parser {
    protected static String input;

    /**
     * This method is a constructor the Parser Class.
     * @param input Provides the user-input field to be parsed.
     */

    public Parser(String input) {
        Parser.input = input;
    }

    /**
     * This method takes in a user-input as a String, makes sense of the command and returns a command to the TaskList for execution
     * @param input Provides the user-input field to be parsed.
     * @throws DukeException Error if user-input is in an incompatible format.
     */
    public static void parse(String input) throws DukeException {
        if (input.trim().equalsIgnoreCase("bye")) {
            TaskList.Bye();
        } else if (input.trim().equalsIgnoreCase("list")) {
            TaskList.List();
        } else if (input.toLowerCase().contains("unmark")) {
            TaskList.Unmark(input);
        } else if (input.toLowerCase().contains("mark")) {
            TaskList.Mark(input);
        } else if (input.toLowerCase().contains("delete")) {
            TaskList.Delete(input);
        } else if (input.toLowerCase().contains("todo")) {
            TaskList.Todo(input);
        } else if (input.toLowerCase().contains("deadline")) {
            TaskList.Deadline(input);
        } else if (input.toLowerCase().contains("event")) {
            TaskList.Event(input);
        } else {
            throw new DukeException("Error: Please enter a valid task such as bye / list / unmark / mark / delete / todo / deadline / event\n");
        }
    }
}
