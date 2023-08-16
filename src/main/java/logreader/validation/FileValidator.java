package logreader.validation;

public class FileValidator {

    public static boolean isLogFile(String filePath) {
        return filePath.endsWith(".log");
    }
}
