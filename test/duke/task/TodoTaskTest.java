package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTaskTest {
    Task todoTask = new TodoTask("borrow book");

    @Test
    void getStatusIcon() {
        assertEquals(" ", todoTask.getStatusIcon());
    }

    @Test
    void getDescription() {
        assertEquals("borrow book", todoTask.getDescription());
    }
}