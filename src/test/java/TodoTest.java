import org.junit.jupiter.api.Test;
import task.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void stringType(){
        var response = new Todo("read");
        assertEquals("[T]" + "[" + response.getStatusIcon() + "] " + "read", String.valueOf(response));
    }
}