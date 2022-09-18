package task;

/**
 * This Task program defines the basic structure for a Task class. It contains the following key components:
 *  1. Description
 *  2. isDone Status (Whether task is completed / not completed)
 * It also contains the basic constructor, a getter and a setter method.
 */

public abstract class Task {
    public String description;
    protected boolean isDone;

    /**
     * This method is used to initialize the Task Class using a String description as a variable.
     * @param description Registers the description of the Task.
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method is used to retrieve the status of a task (Completed / Not completed).
     * @return Returns either an X or blank symbol representing the task completion status.
     */

    public String getStatusIcon() {

        return (isDone ? "X" : " "); //return X or blank symbol
    }
    /**
     * This method is used to get the description of a task
     * * @return Returns description of task
     */

    public String getDescription() {
        return description;
    }

    /**
     * This method is used to set the status of a task (Completed / Not completed).
     */
    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * This abstract method is used to return the String output of the Task is a specified manner.
     */
    public abstract String toString();
}
