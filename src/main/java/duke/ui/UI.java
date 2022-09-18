package duke.ui;
import java.util.Scanner;

public class UI {
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println();
//        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    public String readUserInput() {
        Scanner in = new Scanner(System.in);

        return in.nextLine();
    }

    public void showLine() {
        System.out.println("__________________________________________________");
    }
}
