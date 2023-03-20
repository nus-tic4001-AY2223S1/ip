package duke.commands;

import duke.task.Task;
import duke.storage.Storage;
import duke.exception.DukeException;
import java.io.File;
import java.util.ArrayList;

public class MarkCommand extends Command{
    @Override
    public String execute(String userInput, ArrayList<Task> taskList, Storage storage, File file) throws DukeException {
        try {
            String newLine = System.getProperty("line.separator");
            int markTaskIndex = Integer.parseInt(userInput.substring(5)) - 1;

            taskList.get(markTaskIndex).setStatusIcon("mark");
            storage.updateTaskFile(taskList, file);

            return "    Nice! I've marked this task as done:"
                    .concat(newLine)
                    .concat("      " + taskList.get(markTaskIndex));
        } catch (StringIndexOutOfBoundsException e) {
            return "\u2639 " + "Check the Duke basic input commands!!! Correct input format for 'mark' keyword must be provided.";
        } catch (IndexOutOfBoundsException e) {
            return "\u2639 " + "OOPS!!! It's either the task's list is empty or the index entered is out of bound.";
        } catch (NumberFormatException e) {
            return "\u2639 " + "Check the Duke basic input commands!!! 'mark' keyword must be followed by a positive integer.";
        } catch (DukeException e) {
            return "Something went wrong: " + e.getMessage();
        }
    }
}