package duke.commands;
import duke.task.Task;
import duke.storage.Storage;
import duke.exception.DukeException;
import java.io.File;
import java.util.ArrayList;

public class MarkCommand extends Command{
    @Override
    public void execute(String userInput, ArrayList<Task> taskList, Storage storage, File file) throws DukeException {
        try {
            int markTaskIndex = Integer.parseInt(userInput.substring(5)) - 1;

            taskList.get(markTaskIndex).setStatusIcon("mark");
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + taskList.get(markTaskIndex));
            storage.updateTaskFile(taskList, file);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("\u2639 " + "Check the Duke basic input commands!!! Correct input format for 'mark' keyword must be provided.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\u2639 " + "OOPS!!! It's either the task's list is empty or the index entered is out of bound.");
        } catch (NumberFormatException e) {
            throw new DukeException("\u2639 " + "Check the Duke basic input commands!!! 'mark' keyword must be followed by a positive integer.");
        } catch (DukeException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }
    @Override
    public boolean setIsExit() {
        return isExit = false;
    }
}