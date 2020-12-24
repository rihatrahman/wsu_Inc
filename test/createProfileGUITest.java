import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * test for createProfileGUI class
 * @author rihat rahman
 */
class createProfileGUITest {

    @Test
    void checkDuplicateUsername() throws FileNotFoundException {

        createProfileGUI test = new createProfileGUI();

        assertTrue(test.checkDuplicateUsername("user"));
        assertTrue(test.checkDuplicateUsername("testID"));
        assertTrue(test.checkDuplicateUsername("raj"));

        assertFalse(test.checkDuplicateUsername("mmm"));
        assertFalse(test.checkDuplicateUsername("users"));
        assertFalse(test.checkDuplicateUsername("test"));
    }
}

