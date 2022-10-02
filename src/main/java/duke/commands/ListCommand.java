package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import java.io.File;
import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public void execute(String userInput, ArrayList<Task> taskList, Storage storage, File file) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException("    The list is empty!");
        } else {
            System.out.println("    Here are the task(s) in your file:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("      " + (i + 1) + "." + (taskList.get(i)));
            }
        }
    }

    @Override
    public boolean setIsExit() {
        return isExit = false;
    }
}