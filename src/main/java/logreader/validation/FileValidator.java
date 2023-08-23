package logreader.validation;

import java.nio.file.Path;

public class FileValidator {

    public static boolean isLogFile(Path filePath) {
        return filePath.endsWith(".log");
    }
}
