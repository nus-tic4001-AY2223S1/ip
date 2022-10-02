package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTaskTest {
    Task deadlineTask = new DeadlineTask("deadline return book", "by Sunday");

    @Test
    void getStatusIcon() {
        assertEquals(" ", deadlineTask.getStatusIcon());
    }

    @Test
    void getDescription() {
        assertEquals("deadline return book", deadlineTask.getDescription());
    }

    @Test
    void getDetails() {
        assertEquals("by Sunday", deadlineTask.getDetails());
    }
}