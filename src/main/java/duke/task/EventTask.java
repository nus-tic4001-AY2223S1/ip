package duke.task;

/**
 * This <code>Event</code> class extends the abstract <code>Todo</code> class.
 * Object instantiated by this class returns task with specific date of completion.
 */
public class EventTask extends Task {
    protected String at;

    public EventTask(String description, String at) {
        super(description);
        this.at = at;
    }

    public EventTask(String description, boolean status, String at) {
        super(description, status);
        this.at = at;
    }

    @Override
    public String getDetails() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription() + " (at: " + at + ")";
    }
}