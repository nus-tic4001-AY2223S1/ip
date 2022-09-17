package duke.task;

/**
 * This <code>Todo</code> class extends the abstract <code>Todo</code> class.
 * Object instantiated by this class returns task that does not require specific date of completion.
 */
public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }

    @Override
//    public String toString() { return "[T]" + super.toString(); }
    public String toString() { return "[T]" + "[" + getStatusIcon() + "] " + getDescription(); }
}