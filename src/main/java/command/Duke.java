package command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public static boolean trigger = true;
    public static ArrayList<Task> list = new ArrayList<>();

    private static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task l : list) {
            fw.write(l + System.lineSeparator());
        }
        fw.close();
    }

    private static void printFileContents(String filePath) throws DukeException , IOException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        if (!s.hasNext()) {
            System.out.println("No existing data is found");
        }
        while (s.hasNext()) {
            String current = s.nextLine();
            System.out.println(current);
            if (current.contains("[T]")) {
                String description = current.substring(7);
                list.add(new Todo(description));
                if (current.contains("\u2713")) {
                    list.get(count).setStatus(true);
                }
            }
            else if (current.contains("[D]")) {
                int m = current.indexOf("(");
                int n = current.indexOf(")");
                String description = current.substring(7,m-1);
                String by = current.substring(m+5,n);
                list.add(new Deadline(description,by));
                if (current.contains("\u2713")) {
                    list.get(count).setStatus(true);
                }
            }
            else if (current.contains("[E]")) {
                int m = current.indexOf("(");
                int n = current.indexOf(")");
                String description = current.substring(7,m-1);
                String at = current.substring(m+5,n);
                list.add(new Event(description,at));
                if (current.contains("\u2713")) {
                    list.get(count).setStatus(true);
                }
            }
            else {
                throw new DukeException("Error: Task in existing data is incompatible\n");
            }
            count++;
        }
    }

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
        if (t.toLowerCase().contains("todo")) {
            if (t.trim().length() < 5) {
                throw new DukeException("Error: Description of task cannot be empty.\n");
            }
            int m = t.toLowerCase().indexOf("todo");
            String description = t.substring(m+4).trim();
            for (Task l : list) {
                if (l.description.equals(description)) {
                    throw new DukeException("Error: task.Task has already been added previously\n");
                }
            }
            list.add(new Todo(description));
        }
        else if (t.toLowerCase().contains("deadline")) {
            if (t.trim().length() < 9) {
                throw new DukeException("Error: Description of task cannot be empty.\n");
            }
            if (!t.contains("/")) {
                throw new DukeException("Error: Please specify time.\n");
            }
            int m = t.toLowerCase().indexOf("deadline");
            int n = t.indexOf('/');
            String description = t.substring(m+8,n).trim();
            String by = t.substring(n+3).trim();
            for (Task l : list) {
                if (l.description.equals(description)) {
                    throw new DukeException("Error: task.Task has already been added previously\n");
                }
            }
            list.add(new Deadline(description, by));
        }
        else if (t.toLowerCase().contains("event")) {
            if (t.trim().length() < 6) {
                throw new DukeException("Error: Description of task cannot be empty.\n");
            }
            if (!t.contains("/")) {
                throw new DukeException("Error: Please specify time.\n");
            }
            int m = t.toLowerCase().indexOf("event");
            int n = t.indexOf('/');
            String description = t.substring(m+5,n).trim();
            String at = t.substring(n+3).trim();
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
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        if (line.trim().equalsIgnoreCase("bye")) {                                              //Switch Case - Bye
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        }
        else if (line.trim().equalsIgnoreCase("list")) {
            if (count == 0) {
                throw new DukeException("There are no items currently in the list\n");
            }                                          //Switch Case - List
            System.out.println("Here are the tasks in your list:\n");
            int seq = 1;
            for (Task l : list) {
                System.out.println(seq + ". " + l);
                seq++;
            }
            trigger = false;
        }
        else if (line.toLowerCase().contains("unmark")) {                                        //Switch Case - Unmark
            int m = line.toLowerCase().indexOf("unmark");
            String num = line.substring(m+4).trim();
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
            }
            System.out.println(list.get(n-1) + "\n");
            echo();
        }
        else if (line.toLowerCase().contains("mark")) {                                      //Switch Case - Mark
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
            }
            System.out.println(list.get(n-1) + "\n");
            echo();
        }
        else if (line.toLowerCase().contains("delete")) {
            int m = line.toLowerCase().indexOf("delete");
            String num = line.substring(m+6).trim();
            if (num.length() < 1) {
                throw new DukeException("Error: Please enter which task to be deleted\n");
            }
            int n = Integer.parseInt(num);
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
        public static void main(String[] args) {
            System.out.println("Hello! I'm Duke\n" + "Let me load the existing data for you (if any)\n");
            String FileLocation = "data/duke.txt";
            String Directory = "./data/";
            try {
                Path path = Paths.get(Directory);
                Files.createDirectories(path);
                printFileContents(FileLocation);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                trigger = true;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("What would you like to do ?");

    /**
     * This method starts the interaction with the user and asks to provide for a user-input so that it can scan the input and provide it to another class for execution purposes.
     * @throws DukeException Error that is thrown if user inputs an incompatible command.
     */
    public static void main(String[] args) throws DukeException {
        String greet = "Hello! I'm command.Duke\n" + "What can I do for you?\n";
        System.out.println(greet);
        while(true) {
            try {
                echo(); //push into loop echo()
                if (trigger) {
                    writeToFile(FileLocation); //only make changes when task list changes
                }
                trigger = true;
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                trigger = true;
            }catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }
}
