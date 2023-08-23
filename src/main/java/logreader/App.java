package logreader;

import logreader.log.LogParser;
import logreader.service.FileReader;
import logreader.service.Top3Finder;
import logreader.service.FilePathProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.stream.Stream;


public class App {

    private static final Logger logger = LogManager.getLogger(App.class);
    private static final FilePathProvider filePathProvider = new FilePathProvider();

    private static final FileReader fileReader = new FileReader();
    private static final LogParser logParser = new LogParser();
    private static final Top3Finder top3Finder = new Top3Finder();
    public static void main(String[] args) {
        Path filePath = filePathProvider.getFilePath("programming-task-example-data.log");
        Stream<String> fileStream = fileReader.fileReader(filePath);

        if (filePath == null) {
            logger.error("Provided file is invalid");
            return;
        }

        //Parse log file into a list of entries
        var logEntries = logParser.logParser(fileStream);
        var top3Url = top3Finder.findTop3Urls(logEntries);
        var top3Ip = top3Finder.findTop3IP(logEntries);
        var uniqueIps = top3Finder.findUniqueIps(logEntries);

        //Logging the results
        logger.info("Here are the results of the provided log file: ");
        //Number of Unique IP addresses
        logger.info("Number of unique IP addresses: " + uniqueIps.size());
        //Top 3 IP addresses visited
        logger.info("Top 3 IP addresses visited:");
        top3Ip.forEach(ip -> logger.info(ip.getKey() + " - " + ip.getValue()));
        //Top 3 URL visited
        logger.info("Top 3 URLs visited:");
        top3Url.forEach(url -> logger.info(url.getKey() + " - " + url.getValue()));
    }
}

