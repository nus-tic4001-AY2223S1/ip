import java.util.Scanner;
import java.util.Arrays;
public class Duke {
    public static int count = 0;
    public static int seq = 1;
    public static String[] list = new String[100];

    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        if (line.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
        else if (line.equals("list")) {
            String[] print = Arrays.copyOf(list,count);
            for (String p : print) {
                System.out.println(seq + ". " + p);
                seq++;
            }
            System.out.println("\n");
            seq = 1;
            echo();
        }
        else {
            System.out.println("added: " + line + "\n");
            list[count] = line;
            count++;
            echo();
        }
    }

    public static void main(String[] args) {
        String greet = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(greet);
        echo();
    }
}
