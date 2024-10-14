package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileReader {
    private static final Logger logger = LogManager.getLogger(FileReader.class);
    public Stream<String> fileReader(Path filePath) {
        try {
            return Files.lines(filePath);
        } catch (Exception e) {
            logger.error("Error occurred during parsing: " + e.getMessage());
            return Stream.empty();
        }
    }
}

