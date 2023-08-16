package logreader;

import logreader.service.FilePathProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FilePathProviderTest {

    private final FilePathProvider filePathProvider = new FilePathProvider();
    @Test
    void testGetFilePath() {
        String filePath = filePathProvider.getFilePath("programming-task-example-data.log");
        assertNotNull(filePath);
    }

    @Test
    void testResourceNotFound() {
        String filePath = filePathProvider.getFilePath("mock-nonexistent-file.log");
        assertNull(filePath);
    }
}
