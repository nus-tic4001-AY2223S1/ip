package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import java.io.File;
import java.util.ArrayList;

public class ExitCommand extends Command{
    @Override
    public String execute(String userInput, ArrayList<Task> taskList, Storage storage, File file) {
        System.exit(0);
        return "    Bye. Hope to see you again soon!";
    }
}
