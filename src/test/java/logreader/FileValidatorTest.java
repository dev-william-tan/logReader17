package logreader;

import logreader.validation.FileValidator;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileValidatorTest {

    @Test
    void testIsLogFile_ValidLogFile() {
        Path filePath = Path.of("example.log");
        assertTrue(FileValidator.isLogFile(filePath));
    }

    @Test
    void testIsLogFile_InvalidLogFile() {
        Path filePath = Path.of("data.txt");
        assertFalse(FileValidator.isLogFile(filePath));
    }
}
