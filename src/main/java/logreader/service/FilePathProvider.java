package logreader.service;

import logreader.validation.FileValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePathProvider {

    private static final Logger logger = LogManager.getLogger(FilePathProvider.class);
    public Path getFilePath(String logFile) {
        //Referencing log file in resources package
        URL resourceUrl = ClassLoader.getSystemResource(logFile);
        //Making sure file exists
        if (resourceUrl == null) {
            logger.error("Resource not found");
            return null ;
        }

        try {
            Path filePath = Paths.get(resourceUrl.toURI());
            if (!FileValidator.isLogFile(filePath)) {
                logger.error("Invalid log file" + filePath);
                return null;
            }
            return filePath;
        } catch (Exception e) {
            logger.error("Error converting URL to file path: " + e.getMessage());
            return null;
        }
    }

}
