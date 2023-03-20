package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import java.io.File;
import java.util.ArrayList;

public class ListCommand extends Command {
    @Override
    public String execute(String userInput, ArrayList<Task> taskList, Storage storage, File file) throws DukeException {
        if (taskList.isEmpty()) {
            return "    The list is empty!";
        } else {
            String s = "    Here are the task(s) in your file:";

            for (int i = 0; i < taskList.size(); i++) {
                s += "\n" + "      " + (i + 1) + "." + (taskList.get(i));
            }

            return s;
        }
    }
}