package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import java.io.File;
import java.util.ArrayList;

public class UnmarkCommand extends Command{
    @Override
    public String execute(String userInput, ArrayList<Task> taskList, Storage storage, File file) throws DukeException {
        try {
            String newLine = System.getProperty("line.separator");
            int unmarkTaskIndex = Integer.parseInt(userInput.substring(7)) - 1;

            taskList.get(unmarkTaskIndex).setStatusIcon("unmark");
            storage.updateTaskFile(taskList, file);

            return "    OK, I've marked this task as not done yet:"
                    .concat(newLine)
                    .concat("      " + taskList.get(unmarkTaskIndex));
        } catch (StringIndexOutOfBoundsException e) {
            return "\u2639 " + "Check the Duke basic input commands!!! Correct input format for 'unmark' keyword must be provided.";
        } catch (IndexOutOfBoundsException e) {
            return "\u2639 " + "OOPS!!! It's either the task's list is empty or the index entered is out of bound.";
        } catch (NumberFormatException e) {
            return "\u2639 " + "Check the Duke basic input commands!!! 'unmark' keyword must be followed by a positive integer.";
        } catch (DukeException e) {
            return "Something went wrong: " + e.getMessage();
        }
    }
}