package duke.task;

public class EventTask extends Task {
    protected String at;

    public EventTask(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
//    public String toString() { return "[E]" + super.toString() + " (at: " + at + ")"; }
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription() + " (at: " + at + ")";
    }
}