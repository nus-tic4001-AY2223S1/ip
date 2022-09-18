package task;
import java.util.ArrayList;

public class TaskList {
    protected static int count;
    protected static ArrayList<Task> list;
    public TaskList(ArrayList<Task> list, int count) {
        TaskList.count = count;
        TaskList.list = list;
    }
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

    public static void Bye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

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
            System.out.println(list.get(n-1) + "\n");
        }
    }

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

    public static void Deadline(String line) throws DukeException {
        if (line.trim().length() < 9) {
            throw new DukeException("Error: Description of task cannot be empty.\n");
        }
        if (!line.contains("/")) {
            throw new DukeException("Error: Please specify time.\n");
        }
        int m = line.toLowerCase().indexOf("deadline");
        int n = line.indexOf('/');
        String description = line.substring(m + 8, n).trim();
        String by = line.substring(n + 3).trim();
        for (Task l : list) {
            if (l.description.equals(description)) {
                throw new DukeException("Error: Task has already been added previously\n");
            }
        }
        list.add(new Deadline(description, by));
        UpdateStatus();
    }

    public static void Event(String line) throws DukeException {
        if (line.trim().length() < 6) {
            throw new DukeException("Error: Description of task cannot be empty.\n");
        }
        if (!line.contains("/")) {
            throw new DukeException("Error: Please specify time.\n");
        }
        int m = line.toLowerCase().indexOf("event");
        int n = line.indexOf('/');
        String description = line.substring(m + 5, n).trim();
        String at = line.substring(n + 3).trim();
        for (Task l : list) {
            if (l.description.equals(description)) {
                throw new DukeException("Error: Task has already been added previously\n");
            }
        }
        list.add(new Event(description, at));
        UpdateStatus();
    }

    public static void UpdateStatus() {
        System.out.println("Got it. I've added this task:");
        System.out.println(list.get(count));
        count++;
        System.out.println("Now you have " + count + " tasks in the list.");
    }
}

