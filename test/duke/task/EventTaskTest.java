package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTaskTest {
    Task eventTask = new EventTask("event project meeting", "at Mon 2-4pm");

    @Test
    void getStatusIcon() {
        assertEquals(" ", eventTask.getStatusIcon());
    }

    @Test
    void getDescription() {
        assertEquals("event project meeting", eventTask.getDescription());
    }

    @Test
    void getDetails() {
        assertEquals("at Mon 2-4pm", eventTask.getDetails());
    }
}