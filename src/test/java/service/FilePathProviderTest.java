package service;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FilePathProviderTest {

    private final FilePathProvider filePathProvider = new FilePathProvider();
    @Test
    void testGetFilePath() throws IOException {
        Path filePath = filePathProvider.getFilePath("logFileSmall.log");
        assertNotNull(filePath);
    }
}
