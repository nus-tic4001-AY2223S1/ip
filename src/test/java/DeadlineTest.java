import org.junit.jupiter.api.Test;
import task.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void stringType(){
        var response = new Deadline("study", LocalDate.parse("2022-11-24"));
        assertEquals("[D]" + "[" + response.getStatusIcon() + "] " + "study" + " (by: " + "Nov 24 2022" + ")", String.valueOf(response));
    }
}