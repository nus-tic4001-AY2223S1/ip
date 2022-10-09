import org.junit.jupiter.api.Test;
import task.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void stringType(){
        var response = new Event("awards ceremony", LocalDate.parse("2022-09-25"));
        assertEquals("[E]" + "[" + response.getStatusIcon() + "] " + "awards ceremony" + " (at: " + "Sep 25 2022" + ")", String.valueOf(response));
    }
}