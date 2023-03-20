package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;
import java.io.File;
import java.util.ArrayList;

public class AddCommand extends Command {
    @Override
    public String execute(String userInput, ArrayList<Task> taskList, Storage storage, File file) throws DukeException {
        try {
            String newLine = System.getProperty("line.separator");
            String description;
            boolean isIncluded = false;
            String[] firstWord = userInput.split(" ", 2);

            if (firstWord[1].isBlank()) {
                return "\u2639 " + firstWord[0] + " keyword must not be empty!";
            }

            for (Task task : taskList) {
                if (task.getDescription().equals((firstWord[1].split(" /", 2))[0])) {
                    isIncluded = true;
                    break;
                }
            }

            if (isIncluded) {
                return "The current list contains this task.";
            } else {
                switch (firstWord[0]) {
                    case "todo": {
                        description = userInput.substring(5);

                        taskList.add(new TodoTask(description));
                        break;
                    }
                    case "deadline": {
                        description = userInput.substring(9, userInput.indexOf(" /by"));
                        String by = userInput.substring(userInput.indexOf("/by") + 4);

                        taskList.add(new DeadlineTask(description, by));
                        break;
                    }
                    case "event": {
                        description = userInput.substring(6, userInput.indexOf(" /at"));
                        String at = userInput.substring(userInput.indexOf("/at") + 4);

                        taskList.add(new EventTask(description, at));
                        break;
                    }
                }

                for (int i = 0; i < taskList.size(); i++) {
                    storage.saveTaskToFile(taskList.get(i), file);
                }

                return "     Got it. I've added this task:"
                        .concat(newLine)
                        .concat("       " + (taskList.get(taskList.size() - 1)))
                        .concat(newLine)
                        .concat("     Now you have " + Task.getTotalTask() + " task(s) in the list.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "\u2639 " + userInput + " keyword must be followed by a new valid task";
        } catch (StringIndexOutOfBoundsException e) {
            return "\u2639 " + "Check the Duke basic input commands!!! Correct input format must be provided.";
        }
    }
}
