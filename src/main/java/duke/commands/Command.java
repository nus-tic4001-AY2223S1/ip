package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.exception.DukeException;
import java.io.File;
import java.util.ArrayList;

public abstract class Command {
    public static boolean isExit = false;

    public abstract void execute(String userInput, ArrayList<Task> taskList, Storage storage, File file) throws DukeException;

    public abstract boolean setIsExit();
}
