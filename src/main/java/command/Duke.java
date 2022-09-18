package command;

import java.util.ArrayList;
import java.util.Scanner;
import task.*;

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
     *<br>  2. Lets you add various task types accordingly.
     *
     * @see DukeException
     */
    public static int count = 0;
    public static int seq = 1;
    public static ArrayList<Task> list = new ArrayList<>();

    /**
     * This class registers the user-inputs and sorts what the program supposed to execute and adds the task into the list.
     * <br> It contains the following approved actions:
     * <ol>
     *     <li>Todo</li>
     *     <li>Event</li>
     *     <li>Deadline</li>
     * </ol>
     * <p>
     * Once it understand what the user-input is referring to, it adds the task into the list.
     */

    public static void sort(String t) throws DukeException { //classify tasks between todo / deadline / event
        if (t.contains("todo")) {
            if (t.trim().length() < 5) {
                throw new DukeException("Error: Description of task cannot be empty.\n");
            }
            String description = t.substring(5);
            for (Task l : list) {
                if (l.description.equals(description)) {
                    throw new DukeException("Error: task.Task has already been added previously\n");
                }
            }
            list.add(new Todo(description));
        }
        else if (t.contains("deadline")) {
            if (t.trim().length() < 9) {
                throw new DukeException("Error: Description of task cannot be empty.\n");
            }
            if (!t.contains("/")) {
                throw new DukeException("Error: Please specify time.\n");
            }
            int n = t.indexOf('/');
            String description = t.substring(9, n-1);
            String by = t.substring(n+4);
            for (Task l : list) {
                if (l.description.equals(description)) {
                    throw new DukeException("Error: task.Task has already been added previously\n");
                }
            }
            list.add(new Deadline(description, by));
        }
        else if (t.contains("event")) {
            if (t.trim().length() < 6) {
                throw new DukeException("Error: Description of task cannot be empty.\n");
            }
            if (!t.contains("/")) {
                throw new DukeException("Error: Please specify time.\n");
            }
            int n = t.indexOf('/');
            String description = t.substring(6, n-1);
            String at = t.substring(n+4);
            for (Task l : list) {
                if (l.description.equals(description)) {
                    throw new DukeException("Error: task.Task has already been added previously\n");
                }
            }
            list.add(new Event(description, at));
        }
        else {
            throw new DukeException("Error: task.Task specified must be within the category of 'todo' / 'event' / 'deadline' only \n");
        }
    }

    /**
     * This class registers the user-inputs and makes sense of the command that the program is supposed to execute.
     * <br> It contains the following approved actions:
     * <ol>
     *     <li>Bye: Exits the Task Manager</li>
     *     <li>List: Allows to list all the tasks inside TaskList</li>
     *     <li>Unmark: Marks a task as not completed</li>
     *     <li>Mark: Marks a task as completed</li>
     *     <li>Delete: Deletes a task from the TaskList</li>
     * </ol>
     * <p>
     * Once it understand what the user-input is referring to, it passes the appropriate command to the TaskList for execution.
     */

    public static void echo() throws DukeException { //classify commands such as bye / list / unmark / mark / add tasks
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        if (line.equals("bye")) {                                                //Switch Case - Bye
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        }
        else if (line.equals("list")) {                                          //Switch Case - List
                System.out.println("Here are the tasks in your list:\n");
            for (Task l : list) {
                System.out.println(seq + ". " + l);
                    seq++;
                }
                System.out.println("\n");
                seq = 1;
        }
        else if (line.contains("unmark")) {                                      //Switch Case - Unmark
            String[] words = line.split(" ");
            if (words.length < 2 || words[1].trim().equals("")) {
                throw new DukeException("Error: Please enter which task to unmark\n");
            }
            int n = Integer.parseInt(words[1]);
            if (n > count) throw new DukeException("Error: Please enter a valid task number\n");
            if (list.get(n-1).getStatusIcon().equals(" ")) {
                throw new DukeException("Error: task.Task has already been unmarked\n");
            }
            else {
                System.out.println("OK, I've marked this task as not done yet:");
                list.get(n-1).setStatus(false);
            }
            System.out.println(list.get(n-1) + "\n");
            echo();
        }
        else if (line.contains("mark")) {                                       //Switch Case - Mark
            String[] words = line.split(" ");
            if (words.length < 2 || words[1].trim().equals("")) {
                throw new DukeException("Error: Please enter which task is done\n");
            }
            int n = Integer.parseInt(words[1]);
            if (n > count) {
                throw new DukeException("Error: Please enter a valid task number\n");
            }
            if (list.get(n-1).getStatusIcon().equals("X")) {
                throw new DukeException("Error: task.Task has already been marked\n");
            }
            else {
                System.out.println("Nice! I've marked this task as done:");
                list.get(n-1).setStatus(true);
            }
            System.out.println(list.get(n-1) + "\n");
            echo();
        }
        else if (line.contains("delete")) {
            String[] words = line.split(" ");
            if (words.length < 2 || words[1].trim().equals("")) {
                throw new DukeException("Error: Please enter which task to be deleted\n");
            }
            int n = Integer.parseInt(words[1]);
            if (n > count) {
                throw new DukeException("Error: Please enter a valid task number\n");
            }
            else {
                System.out.println("Noted. I've removed this task:");
                System.out.println(list.get(n-1));
                list.remove(n-1);
                count--;
                System.out.println("Now you have " + count + " tasks in the list.\n");
            }
        }
        else {                                                                  //Switch Case - Add task
            sort(line);
            System.out.println("Got it. I've added this task:");
            System.out.println(list.get(count));
            count++;
            System.out.println("Now you have " + count + " tasks in the list.\n");
        }
    }

    /**
     * This method starts the interaction with the user and asks to provide for a user-input so that it can scan the input and provide it to another class for execution purposes.
     * @throws DukeException Error that is thrown if user inputs an incompatible command.
     */
    public static void main(String[] args) throws DukeException {
        String greet = "Hello! I'm command.Duke\n" + "What can I do for you?\n";
        System.out.println(greet);
        while(true) {
            try {
                echo(); //push into recursive loop echo()
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
