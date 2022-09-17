package duke.task;

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    public TodoTask(String description, boolean status) {super(description, status);}

    @Override
//    public String toString() { return "[T]" + super.toString(); }
    public String toString() { return "[T]" + "[" + getStatusIcon() + "] " + getDescription(); }
}