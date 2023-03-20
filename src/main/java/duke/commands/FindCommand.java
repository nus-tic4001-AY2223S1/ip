package duke.commands;
import duke.task.Task;
import duke.storage.Storage;
import duke.exception.DukeException;
import java.io.File;
import java.util.ArrayList;

public class FindCommand extends Command {
    @Override
    public String execute(String userInput, ArrayList<Task> taskList, Storage storage, File file) throws DukeException {
        try {
            String keyword = userInput.substring(5);
            ArrayList<Task> matchingTaskList = new ArrayList<>();

            if (keyword.equals("")) {
                return "\u2639 you forgot the keyword!";
            } else if (taskList.isEmpty()) {
                return "    The list is empty!";
            } else {
                for (int i = 0; i < taskList.size(); i++) {
                    if (taskList.get(i).toString().contains(keyword)) {
                        matchingTaskList.add(taskList.get(i));
                    }
                }

                if (matchingTaskList.isEmpty()) {
                    return "    There are no matching tasks in your list!";
                } else {
                    System.out.println("    Here are the matching tasks in your list:");
                    String s = "    Here are the matching tasks in your list:";

                    for (int j = 0; j < matchingTaskList.size(); j++) {
                        s += "\n" + "      " + (j + 1) + "." + (matchingTaskList.get(j));
                    }

                    return s;
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
            return "\u2639 " + "Check the Duke basic input commands!!! Correct input format must be provided.";
        }
    }
}