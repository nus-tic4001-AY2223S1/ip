package command;
import task.DukeException;
import java.io.IOException;

/**
 * <h2> Duke, your personalized task manager </h2>
 * <br>This task manager shall assist to add / remove various kinds of tasks that might include their estimated completion dates.
 * <br>It also allows you to view your current tasks at hand, check if they have been completed and check which tasks occur on any given date.
 * <p>
 * Its error-detection and user-input flexibility makes this task manager more functional and user-friendly.
 * <br>Hope you enjoy using this task manager !
 *
 * @author Roshan Kumar
 * @version 1.0
 * @since 18/09/2022
 */

public class Duke {

    /**
     * This is the main method which does 2 key things to get the program started.
     *<br>  1. It loads any previously stored Tasks List into the current program, so you can continue from that point forward.
     *<br>  2. It loads the UI class that starts the interaction with the user.
     * @param args Unused.
     * @throws DukeException Error thrown when user input is not complete or in an unreadable format.
     * @see DukeException
     */

    public static void main(String[] args) throws DukeException, IOException {
        Ui.main();
    }
}
