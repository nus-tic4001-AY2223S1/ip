package task;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class is used for evaluating the actions of the multiple user commands such as add / delete tasks etc.
 * <br> It contains the following approved actions:
 * <ol>
 *     <li>List: Allows to list all the tasks inside TaskList</li>
 *     <li>Bye: Exits the Task Manager</li>
 *     <li>Unmark: Marks a task as not completed</li>
 *     <li>Mark: Marks a task as completed</li>
 *     <li>Delete: Deletes a task from the TaskList</li>
 *     <li>Todo / Event / Deadline: Adds one of the following tasks into the TaskList</li>
 * </ol>
 */
public class TaskList {
    protected static int count;
    protected static ArrayList<Task> list;

    /**
     * This method initializes the TaskList ArrayList.
     * @param list Provides the list of tasks
     * @param count Provides how many tasks have been added
     */
    public TaskList(ArrayList<Task> list, int count) {
        TaskList.count = count;
        TaskList.list = list;
    }

    /**
     * This method is to retrieve the updated TaskList.
     * @return Returns the latest updated TaskList.
     */
    public static ArrayList<Task> UpdatedList() {
        return list;
    }
    public static void List() throws DukeException {
        if (count == 0) {
            throw new DukeException("There are no items currently in the list\n");
        }
        System.out.println("Here are the tasks in your list:\n");
        int seq = 1;
        for (Task l : list) {
            System.out.println(seq + ". " + l);
            seq++;
        }
    }

    /**
     * This method helps to take a find keyword and displays any tasks in the TaskList that contain that task description.
     * @param line Contains the user-input for the tasks description that he wants to find.
     */

    public static void Find(String line) {
        int seq = 1;
        for (Task l : list) {
            if(l.description.toLowerCase().contains(line.toLowerCase())) {
                System.out.println(seq + "." + l);
                seq++;
            }
        }
    }

    /**
     * This method helps to exit from the Task Manager.
     */

    public static void Bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    /**
     * This task helps to mark a task as completed.
     * @param line Provide details into which task is to be marked as completed.
     * @throws DukeException Throws various exceptions if the user-input is invalid.
     */

    public static void Mark(String line) throws DukeException {
        int m = line.toLowerCase().indexOf("mark");
        String num = line.substring(m+4).trim();
        if (num.length() < 1) {
            throw new DukeException("Error: Please enter which task is done\n");
        }
        int n = Integer.parseInt(num);
        if (n > count) {
            throw new DukeException("Error: Please enter a valid task number\n");
        }
        if (list.get(n-1).getStatusIcon().equals("X")) {
            throw new DukeException("Error: task.Task has already been marked\n");
        }
        else {
            System.out.println("Nice! I've marked this task as done:");
            list.get(n-1).setStatus(true);
            System.out.println(list.get(n-1) + "\n");
        }
    }

    public static void Unmark(String line) throws DukeException {
        int m = line.toLowerCase().indexOf("unmark");
        String num = line.substring(m+6).trim();
        if (num.length() < 1) {
            throw new DukeException("Error: Please enter which task to unmark\n");
        }
        int n = Integer.parseInt(num);
        if (n > count) throw new DukeException("Error: Please enter a valid task number\n");
        if (list.get(n-1).getStatusIcon().equals(" ")) {
            throw new DukeException("Error: task.Task has already been unmarked\n");
        }
        else {
            System.out.println("OK, I've marked this task as not done yet:");
            list.get(n-1).setStatus(false);
            System.out.println(list.get(n-1) + "\n");
        }
    }

    /**
     * This task helps to delete a task.
     * @param line Provide details into which task is to be deleted.
     * @throws DukeException Throws various exceptions if the user-input is invalid.
     */

    public static void Delete(String line) throws DukeException {
        int m = line.toLowerCase().indexOf("delete");
        String num = line.substring(m + 6).trim();
        if (num.length() < 1) {
            throw new DukeException("Error: Please enter which task to be deleted\n");
        }
        int n = Integer.parseInt(num);
        if (n > count) {
            throw new DukeException("Error: Please enter a valid task number\n");
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(list.get(n - 1));
            list.remove(n - 1);
            count--;
            System.out.println("Now you have " + count + " tasks in the list.");
        }
    }

    /**
     * This method is to add a Todo task.
     * @param line Provides the description of the task.
     * @throws DukeException Throws various exceptions if the user-input is invalid.
     */

    public static void Todo(String line) throws DukeException {
        if (line.trim().length() < 5) {
            throw new DukeException("Error: Description of task cannot be empty.\n");
        }
        int m = line.toLowerCase().indexOf("todo");
        String description = line.substring(m + 4).trim();
        for (Task l : list) {
            if (l.description.equals(description)) {
                throw new DukeException("Error: Task has already been added previously\n");
            }
        }
        list.add(new Todo(description));
        UpdateStatus();
    }

    /**
     * This method is to add a Deadline task.
     * @param line Provides the description of the task.
     * @param localDate Provides the scheduled Date of the completion of the task.
     * @throws DukeException Throws various exceptions if the user-input is invalid.
     */

    public static void Deadline(String line, LocalDate localDate) throws DukeException {
        if (line.trim().length() < 9) {
            throw new DukeException("Error: Description of task cannot be empty.\n");
        }
        if (!line.contains("/")) {
            throw new DukeException("Error: Please specify time.\n");
        }
        int m = line.toLowerCase().indexOf("deadline");
        int n = line.indexOf('/');
        String description = line.substring(m + 8, n).trim();
        for (Task l : list) {
            if (l.description.equals(description)) {
                throw new DukeException("Error: Task has already been added previously\n");
            }
        }
        list.add(new Deadline(description, localDate));
        UpdateStatus();
    }

    /**
     * This method is to add an Event task.
     * @param line Provides the description of the task.
     * @param localDate Provides the scheduled Date of the completion of the task.
     * @throws DukeException Throws various exceptions if the user-input is invalid.
     */

    public static void Event(String line, LocalDate localDate) throws DukeException {
        if (line.trim().length() < 6) {
            throw new DukeException("Error: Description of task cannot be empty.\n");
        }
        if (!line.contains("/")) {
            throw new DukeException("Error: Please specify time.\n");
        }
        int m = line.toLowerCase().indexOf("event");
        int n = line.indexOf('/');
        String description = line.substring(m + 5, n).trim();
        for (Task l : list) {
            if (l.description.equals(description)) {
                throw new DukeException("Error: Task has already been added previously\n");
            }
        }
        list.add(new Event(description, localDate));
        UpdateStatus();
    }

    public static void UpdateStatus() {
        System.out.println("Got it. I've added this task:");
        System.out.println(list.get(count));
        count++;
        System.out.println("Now you have " + count + " tasks in the list.");
    }
}

