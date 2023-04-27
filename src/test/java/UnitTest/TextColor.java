package UnitTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextColor {

    @Test
    @DisplayName("this is a test for the JUnit dependency")
    void textColor(){
        String test = "this is a test";
        assertEquals("this is a test", test);
    }

}
