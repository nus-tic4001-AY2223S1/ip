package duke.helper;

import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

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
}
