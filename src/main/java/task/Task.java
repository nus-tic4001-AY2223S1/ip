package task;

public abstract class Task {
    public String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); //return X or blank symbols
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public abstract String toString();
}
