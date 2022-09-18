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

public class Duke {
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
        else if (line.contains("delete")) {
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
