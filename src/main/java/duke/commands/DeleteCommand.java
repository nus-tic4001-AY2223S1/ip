package duke.commands;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import java.io.File;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    @Override
    public void execute(String userInput, ArrayList<Task> taskList, Storage storage, File file) throws DukeException {
        try {
            String[] firstWord = userInput.split(" ", 2);
            int taskIndex = Integer.parseInt(firstWord[1]);

            if (taskIndex < 1 || taskIndex > taskList.size()) {
                throw new DukeException("The task item passed is not within the current task list range.");
            } else {
                System.out.println("     Noted. I've removed this task:");
                System.out.println("       " + (taskList.get(Integer.parseInt(Integer.toString(taskIndex)) - 1)));
                System.out.println("     Now you have " + (taskList.size() - 1) + " task(s) in the list.");

                taskList.remove(taskIndex - 1);
                Task.setTotalTask();
                storage.updateTaskFile(taskList, file);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("\u2639 " + "Check the Duke basic input commands!!! Correct input format for 'unmark' keyword must be provided.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\u2639 " + "OOPS!!! It's either the task's list is empty or the index entered is out of bound.");
        } catch (NumberFormatException e) {
            throw new DukeException("\u2639 " + "Check the Duke basic input commands!!! 'delete' keyword must be followed by a positive integer.");
        }
    }
    @Override
    public boolean setIsExit() {
        return isExit = false;
    }
}