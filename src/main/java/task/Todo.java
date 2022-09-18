package task;

/**
 * This program is a child of the Task program. It helps add an additional classification to the Task program.
 * This form of program includes a task that includes only a description but has no time set for its completion.
 */

import task.Task;

public class Todo extends Task {

    /**
     * This method provides a description for the Todo task.
     * @param description Provides a description for the Todo task.
     */

    public Todo(String description) {
        super(description);
    }

    /**
     * This method provides a String format return for the Todo task.
     * @return Returns a String in the unique format specified in the method.
     */

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}

