package duke.task;

/**
 * This is the abstract class of different tasks. A <code>Task</code> object may be
 * be substituted by <code>Todo</code>, <code>Deadline</code> or <code>Event</code> class.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected static int totalTask;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.totalTask++;
    }

    /**
     * Returns either "X" or " ".
     *
     * @param "X" means that the task has been completed.
     * @param " " means that the task has not been done yet.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setStatusIcon(String instruction) {
        if (instruction == "mark") {
            isDone = true;
        }
        if (instruction == "unmark") {
            isDone = false;
        }
    }

    public String getDescription() {
        return description;
    }

    public static int getTotalTask() {
        return totalTask;
    }

    @Override
    public abstract String toString();
}