package logreader;

import logreader.validation.FileValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileValidatorTest {

    @Test
    void testIsLogFile_ValidLogFile() {
        String filePath = "example.log";
        assertTrue(FileValidator.isLogFile(filePath));
    }

    @Test
    void testIsLogFile_InvalidLogFile() {
        String filePath = "data.txt";
        assertFalse(FileValidator.isLogFile(filePath));
    }
}
