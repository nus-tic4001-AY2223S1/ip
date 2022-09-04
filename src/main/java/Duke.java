import duke.helper.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void filterInput() throws DukeException {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        System.out.println();

        if ((line.equals("bye")) || (line.equals("list")) || (line.startsWith("mark")) || line.startsWith("unmark")) {
            operateTasks(line);
        } else if ((line.startsWith("todo")) || (line.startsWith("deadline")) || (line.startsWith("event"))) {
            addTask(line);
            printTask();
        } else {
            throw new DukeException("\u2639" + " OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.println();
    }

    public static void operateTasks(String s) throws DukeException {
        String firstWord[] = s.split(" ", 2);

        switch (firstWord[0]) {
            case "bye":
                System.out.println("    Bye. Hope to see you again soon!");
                System.exit(0);
                break;
            case "list":
                if (taskList.isEmpty()) {
                    System.out.println("    The list is empty!");
                } else {
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("      " + (i + 1) + "." + (taskList.get(i)));
                    }
                }
                break;
            case "mark":
                int markTaskIndex = Integer.parseInt(s.substring(5)) - 1;

                taskList.get(markTaskIndex).setStatusIcon("mark");

                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      " + taskList.get(markTaskIndex));
                break;
            case "unmark":
                int unmarkTaskIndex = Integer.parseInt(s.substring(7)) - 1;

                taskList.get(unmarkTaskIndex).setStatusIcon("unmark");

                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("      " + taskList.get(unmarkTaskIndex));
                break;
        }
    }

    public static void addTask(String s) throws DukeException {
        String description;
        String firstWord[] = s.split(" ", 2);

        System.out.println("     Got it. I've added this task:");

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
    }

    public static void printTask() {
        System.out.println("       " + (taskList.get(taskList.size() - 1)));
        System.out.println("     Now you have " + Task.getTotalTask() + " task(s) in the list.");
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

