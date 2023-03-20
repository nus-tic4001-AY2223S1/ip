package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import java.io.File;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    @Override
    public String execute(String userInput, ArrayList<Task> taskList, Storage storage, File file) throws DukeException {
        try {
            String newLine = System.getProperty("line.separator");
            String[] firstWord = userInput.split(" ", 2);
            int taskIndex = Integer.parseInt(firstWord[1]);

            if (taskIndex < 1 || taskIndex > taskList.size()) {
                return "The task item passed is not within the current task list range.";
            } else {
                String removeTask = String.valueOf(taskList.get(Integer.parseInt(Integer.toString(taskIndex)) - 1));

                taskList.remove(taskIndex - 1);
                Task.setTotalTask();
                storage.updateTaskFile(taskList, file);

                return "     Noted. I've removed this task:"
                        .concat(newLine)
                        .concat("       " + removeTask)
                        .concat(newLine)
                        .concat("     Now you have " + Task.getTotalTask() + " task(s) in the list.");
            }
        } catch (StringIndexOutOfBoundsException e) {
            return "\u2639 " + "Check the Duke basic input commands!!! Correct input format for 'unmark' keyword must be provided.";
        } catch (IndexOutOfBoundsException e) {
            return "\u2639 " + "OOPS!!! It's either the task's list is empty or the index entered is out of bound.";
        } catch (NumberFormatException e) {
            return "\u2639 " + "Check the Duke basic input commands!!! 'delete' keyword must be followed by a positive integer.";
        }
    }
}