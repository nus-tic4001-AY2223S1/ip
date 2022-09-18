package task;

/**
 * This program is a child of the Task program. It helps add a classification to the Task program.
 * <br>This form of program includes a task that includes both a description and its associated completion date.
 */

public class Deadline extends Task {
    protected String by;

    /**
     * This method initialized a deadline task that takes in 2 parameters and creates the task.
     * @param description Description of task
     */

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * This method provides a String format return for the event task.
     * @return Returns a String in the unique format specified in the method.
     */

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription() + " (by: " + by + ")";
    }
}

