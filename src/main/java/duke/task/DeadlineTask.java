package duke.task;

public class DeadlineTask extends Task {
    public String by;

    public DeadlineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    public DeadlineTask(String description, boolean status, String by) {
        super(description, status);
        this.by = by;
    }

    @Override
    public String getDetails() {
        return by;
    }

    @Override
//    public String toString() { return "[D]" + super.toString() + " (by: " + by + ")"; }
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription() + " (by: " + by + ")";
    }
}
