package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import java.io.File;
import java.util.ArrayList;

public class ExitCommand extends Command{
    @Override
    public void execute(String userInput, ArrayList<Task> taskList, Storage storage, File file) {
        System.out.println("    Bye. Hope to see you again soon!");
//        System.exit(0); // This is a good alternative for setIsExit() method below.
    }

    @Override
    public boolean setIsExit() {
        return isExit = true;
    }
}
