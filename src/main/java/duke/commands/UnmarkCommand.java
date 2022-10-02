package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import java.io.File;
import java.util.ArrayList;

public class UnmarkCommand extends Command{
    @Override
    public void execute(String userInput, ArrayList<Task> taskList, Storage storage, File file) throws DukeException {
        try {
            int unmarkTaskIndex = Integer.parseInt(userInput.substring(7)) - 1;

            taskList.get(unmarkTaskIndex).setStatusIcon("unmark");
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + taskList.get(unmarkTaskIndex));
            storage.updateTaskFile(taskList, file);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("\u2639 " + "Check the Duke basic input commands!!! Correct input format for 'unmark' keyword must be provided.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\u2639 " + "OOPS!!! It's either the task's list is empty or the index entered is out of bound.");
        } catch (NumberFormatException e) {
            throw new DukeException("\u2639 " + "Check the Duke basic input commands!!! 'unmark' keyword must be followed by a positive integer.");
        } catch (DukeException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }
    @Override
    public boolean setIsExit() {
        return isExit = false;
    }
}