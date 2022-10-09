package task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This program is a child of the Task program. It helps add a classification to the Task program.
 * <br>This form of program includes a task that includes both a description and its associated completion date.
 */

public class Event extends Task {
    protected String formattedDateTime;
    protected LocalDate localDate;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd y");

    /**
     * This method initialized an event task that takes in 2 parameters and creates the task.
     * @param description Description of task
     * @param localDate Scheduled Date of task completion
     */

    public Event(String description, LocalDate localDate) {
        super(description);
        this.localDate = localDate;
        this.formattedDateTime = localDate.format(formatter);
    }

    /**
     * This method provides a String format return for the event task.
     * @return Returns a String in the unique format specified in the method.
     */

    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription() + " (at: " + formattedDateTime + ")";
    }
}