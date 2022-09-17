import duke.task.*;
import duke.exception.DukeException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This <code>Duke</code> class is the main class of the Duke application.
 * This includes the main method and other methods that taps into abstracted
 * classes.
 */
public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void filterInput() throws DukeException {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        System.out.println();

        try {
            if ((line.startsWith("bye")) || (line.startsWith("list")) || (line.startsWith("mark")) || line.startsWith("unmark") || line.startsWith("delete")) {
                operateOnTasks(line);
            } else if ((line.startsWith("todo")) || (line.startsWith("deadline")) || (line.startsWith("event"))) {
                if (line.equals("todo") || line.equals("deadline") || line.equals("event")) {
                    throw new DukeException("\u2639 " + line + " keyword must not be empty!");
                } else if (line.equals("todo ") || line.equals("deadline ") || line.equals("event ")) {
                    throw new DukeException("\u2639 " + line + "keyword must not be empty!");
                } else {
                    addTask(line);
                }
            } else {
                throw new DukeException("\u2639 " + "OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("\u2639 " + line + " keyword must be succeeded with valid, new task");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("\u2639 " + "Check the Duke basic input commands!!! Correct input format must be provided.");
        }
        System.out.println();
    }

    /**
     * <code>operateOnTasks</code> method allows the user to perform the Duke basic commands
     * for manipulating the stored data.
     *
     * @param bye terminates the Duke application from running.
     * @param list prints all the task saved from the file to the console.
     * @param mark checks the tick box.
     * @param unmark unchecked the tick box.
     * @param delete deletes selected task on the file.
     */
    public static void operateOnTasks(String s) throws DukeException {
        String firstWord[] = s.split(" ", 2);

        switch (firstWord[0]) {
            case "bye":
                System.out.println("    Bye. Hope to see you again soon!");
                System.exit(0);
                break;
            case "list":
                if (taskList.isEmpty()) {
                    throw new DukeException("    The list is empty!");
                } else {
                    System.out.println("    Here are the task(s) in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("      " + (i + 1) + "." + (taskList.get(i)));
                    }
                }
                break;
            case "mark":
                try {
                    int markTaskIndex = Integer.parseInt(s.substring(5)) - 1;

                    taskList.get(markTaskIndex).setStatusIcon("mark");

                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("      " + taskList.get(markTaskIndex));
                    break;
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("\u2639 " + "Check the Duke basic input commands!!! Correct input format for \'mark\' keyword must be provided.");
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("\u2639 " + "OOPS!!! It's either the task's list is empty or the index entered is out of bound.");
                } catch (NumberFormatException e) {
                    throw new DukeException("\u2639 " + "Check the Duke basic input commands!!! \'mark\' keyword must be succeeded with a positive integer.");
                }
            case "unmark":
                try {
                    int unmarkTaskIndex = Integer.parseInt(s.substring(7)) - 1;

                    taskList.get(unmarkTaskIndex).setStatusIcon("unmark");

                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println("      " + taskList.get(unmarkTaskIndex));
                    break;
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("\u2639 " + "Check the Duke basic input commands!!! Correct input format for \'unmark\' keyword must be provided.");
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("\u2639 " + "OOPS!!! It's either the task's list is empty or the index entered is out of bound.");
                } catch (NumberFormatException e) {
                    throw new DukeException("\u2639 " + "Check the Duke basic input commands!!! \'unmark\' keyword must be succeeded with a positive integer.");
                }
            case "delete":
                int taskIndex = Integer.parseInt(firstWord[1]);

                if (taskIndex < 1 || taskIndex > taskList.size()) {
                    throw new DukeException("The task item passed is not within the current task list range.");
                } else {
                    printTask(Integer.toString(taskIndex));
                    taskList.remove(taskIndex - 1);
                }
                break;
        }
    }

    public static void addTask(String s) throws DukeException {
        String description;
        boolean isIncluded = false;
        String firstWord[] = s.split(" ", 2);

        for (Task task : taskList) {
            if (task.getDescription().equals((firstWord[1].split(" /", 2))[0])) {
                isIncluded = true;
                break;
            }
        }

        if (isIncluded) {
            throw new DukeException("The current list contains this task.");
        } else {
            switch (firstWord[0]) {
                case "todo":
                    description = s.substring(5);

                    taskList.add(new TodoTask(description));
                    break;
                case "deadline":
                    description = s.substring(9, s.indexOf(" /by"));
                    String by = s.substring(s.indexOf("/by") + 4);

                    taskList.add(new DeadlineTask(description, by));
                    break;
                case "event":
                    description = s.substring(6, s.indexOf(" /at"));
                    String at = s.substring(s.indexOf("/at") + 4);

                    taskList.add(new EventTask(description, at));
                    break;
            }
            printTask("add");
        }
    }

    public static void printTask(String instruction) {
        if (instruction == "add") {
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + (taskList.get(taskList.size() - 1)));
            System.out.println("     Now you have " + Task.getTotalTask() + " task(s) in the list.");
        } else {
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + (taskList.get(Integer.parseInt(instruction) - 1)));
            System.out.println("     Now you have " + (taskList.size() - 1) + " task(s) in the list.");
        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println();
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
        System.out.println();

        while(true) {
            try {
                filterInput();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

