import duke.helper.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void echo() {
        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            System.out.println();
            if (line.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                break;
            } else if (line.equals("list")) {
                if (taskList.isEmpty()) {
                    System.out.println("    The list is empty!");
                } else {
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("      " + (i+1) + "." + (taskList.get(i)));
                    }
                }
            } else if (line.startsWith("mark")) {
                int taskIndex = Integer.parseInt(line.substring(5)) - 1;

                taskList.get(taskIndex).setStatusIcon("mark");

                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      " + taskList.get(taskIndex));
            } else if (line.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(line.substring(7)) - 1;

                taskList.get(taskIndex).setStatusIcon("unmark");

                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("      " + taskList.get(taskIndex));
            } else {
                boolean isIncluded = false;

                for (Task task : taskList) {
                    if (task.getDescription().equals(line)) {
                        isIncluded = true;
                        break;
                    }
                }

                if (isIncluded) {
                    System.out.println("The current list contains this item.");
                } else {
                    System.out.println("     Got it. I've added this task:");
                    if (line.contains("todo")) {
                        String description = line.substring(5);

                        taskList.add(new TodoTask(description));
                        System.out.println("       " + (taskList.get(taskList.size() - 1)));
                    } else if (line.contains("deadline")) {
                        String description = line.substring(9, line.indexOf(" /by"));
                        String by = line.substring(line.indexOf("/by") + 4);

                        taskList.add(new DeadlineTask(description, by));
                        System.out.println("       " + (taskList.get(taskList.size() - 1)));
                    } else if (line.contains("event")) {
                        String description = line.substring(6, line.indexOf(" /at"));
                        String at = line.substring(line.indexOf("/at") + 4);

                        taskList.add(new EventTask(description, at));
                        System.out.println("       " + (taskList.get(taskList.size() - 1)));
                    } else {
                        taskList.add(new Task(line));
                        System.out.println("       added: " + (taskList.get(taskList.size() - 1)));
                    }
                    System.out.println("     Now you have " + Task.getTotalTask() + " task(s) in the list.");
                }
            }
            System.out.println();
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

        echo();
    }
}

