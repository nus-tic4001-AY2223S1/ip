import duke.helper.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println();

        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (line.equals("list")) {
                if (taskList.isEmpty()) {
                    System.out.println("The list is empty!");
                } else {
                    System.out.println("Here are the tasks in your list:lis");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i+1) + ".[" + (taskList.get(i)).getStatusIcon() + "] " + (taskList.get(i)).getDescription());
                    }
                }
            } else if (line.startsWith("mark")) {
                int taskIndex = Integer.parseInt(line.substring(5)) - 1;

                taskList.get(taskIndex).setStatusIcon("mark");

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" [" + (taskList.get(taskIndex)).getStatusIcon() + "] " + (taskList.get(taskIndex)).getDescription());
            } else if (line.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(line.substring(7)) - 1;

                taskList.get(taskIndex).setStatusIcon("unmark");

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" [" + (taskList.get(taskIndex)).getStatusIcon() + "] " + (taskList.get(taskIndex)).getDescription());
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
                    taskList.add(new Task(line));
                    System.out.println("added: " + line);
                }
            }
        }
    }
}

