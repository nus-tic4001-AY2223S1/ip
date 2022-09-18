package command;

public class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }
    boolean trigger = false;
}
