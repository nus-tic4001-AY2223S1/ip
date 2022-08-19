package duke.helper;
import java.util.ArrayList;

public class Task {
    static ArrayList<String> tasks = new ArrayList<>();

    public static void add(String line) {
        int tasks_size = tasks.size() + 1;
        String item_number = String.valueOf(tasks_size);
        tasks.add( item_number + ". " + line);
    }
    public static void list() {
        if (tasks.isEmpty()) {
            System.out.println("The list is empty!");
        } else {
            for (String task : tasks) {
                System.out.println(task);
            }
        }
    }
}
