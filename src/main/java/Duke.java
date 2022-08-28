import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    public static int count = 0;
    public static int seq = 1;
    public static Task[] list = new Task[100];

    public static void sort(String t) {
        if (t.contains("todo")) {                                               //Switch Case - td
            String description = t.substring(5);
            Task[] print = Arrays.copyOf(list,count);   //To prompt an error if task is added previously
            for (Task p : print) {
                if (p.description.equals(description)) {
                    System.out.println("Error: Task has already been added previously\n");
                    echo();
                }
            }
            list[count] = new Todo(description);
        }
        else if (t.contains("deadline")) {                                      //Switch Case - Deadline
            int n = t.indexOf('/');
            String description = t.substring(9, n-1);
            String by = t.substring(n+4);
            Task[] print = Arrays.copyOf(list,count);   //To prompt an error if task is added previously
            for (Task p : print) {
                if (p.description.equals(description)) {
                    System.out.println("Error: Task has already been added previously\n");
                    echo();
                }
            }
            list[count] = new Deadline(description, by);
        }
        else if (t.contains("event")) {                                        //Switch Case - Event
            int n = t.indexOf('/');
            String description = t.substring(6, n-1);
            String at = t.substring(n+4);
            Task[] print = Arrays.copyOf(list,count);   //To prompt an error if task is added previously
            for (Task p : print) {
                if (p.description.equals(description)) {
                    System.out.println("Error: Task has already been added previously\n");
                    echo();
                }
            }
            list[count] = new Event(description, at);
        }
    }
    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        if (line.equals("bye")) {                                                //Switch Case - Bye
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);
        }
        else if (line.equals("list")) {                                          //Switch Case - List
                System.out.println("Here are the tasks in your list:\n");
                Task[] print = Arrays.copyOf(list,count);
                for (Task p : print) {
                    System.out.println(seq + ". " + p);
                    seq++;
                }
                System.out.println("\n");
                seq = 1;
                echo();
        }
        else if (line.contains("unmark")) {                                      //Switch Case - Unmark
            String[] words = line.split(" ");
            if (words.length < 2) {
                System.out.println("Error: Please enter which task is unmarked\n");
                echo();
            }
            int n = Integer.parseInt(words[1]);
            if (n > count) {
                System.out.println("Error: Please enter a valid task number\n");
                echo();
            }
            if (list[n-1].getStatusIcon().equals(" ")) {
                System.out.println("Error: Task has already been unmarked");
            }
            else {
                System.out.println("OK, I've marked this task as not done yet:");
                list[n-1].setStatus(false);
            }
            System.out.println(list[n-1] + "\n");
            echo();
        }
        else if (line.contains("mark")) {                                       //Switch Case - Mark
            String[] words = line.split(" ");
            if (words.length < 2) {
                System.out.println("Error: Please enter which task is marked\n");
                echo();
            }
            int n = Integer.parseInt(words[1]);
            if (n > count) {
                System.out.println("Error: Please enter a valid task number\n");
                echo();
            }
            if (list[n-1].getStatusIcon().equals("X")) {
                System.out.println("Error: Task has already been marked");
            }
            else {
                System.out.println("Nice! I've marked this task as done:");
                list[n-1].setStatus(true);
            }
            System.out.println(list[n-1] + "\n");
            echo();
        }
        else {                                                                  //Switch Case - Add task
            sort(line);
            System.out.println("Got it. I've added this task:");
            System.out.println(list[count]);
            count++;
            System.out.println("Now you have " + count + " tasks in the list.\n");
            echo();
        }
    }

    public static void main(String[] args) {
        String greet = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(greet);
        echo();
    }
}
