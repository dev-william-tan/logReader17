package service;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

public class FilePathProvider {

    private static final Logger logger = LogManager.getLogger(FilePathProvider.class);
    public Path getFilePath(String logFile) throws IOException {
        File tempFile = File.createTempFile("temp", ".log");

        try (InputStream is = ClassLoader.getSystemResourceAsStream(logFile)) {
            assert is != null;
            FileUtils.copyInputStreamToFile(is, tempFile);
            return tempFile.toPath();
        } catch (Exception e) {
            logger.error("Error converting URL to file path: " + e.getMessage());
            return null;
        }
    }

}
