package task;

/**
 * This program is a child of the Task program. It helps add a classification to the Task program.
 * <br>This form of program includes a task that includes both a description and its associated completion date.
 */

public class Event extends Task {
    protected String at;

    /**
     * This method initialized an event task that takes in 2 parameters and creates the task.
     * @param description Description of task
     */

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * This method provides a String format return for the event task.
     * @return Returns a String in the unique format specified in the method.
     */

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription() + " (at: " + at + ")";
    }
}