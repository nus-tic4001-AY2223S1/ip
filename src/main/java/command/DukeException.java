package command;

/**
 * This class is to provide for a customized Error message that is usually thrown when an incompatible user-input is provided.
 */

public class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }
}
