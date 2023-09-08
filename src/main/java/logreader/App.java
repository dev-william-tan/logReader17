package logreader;

import logreader.lineParser.ApacheLineParser;
import logreader.log.LogParser;
import logreader.log.ResultOutput;
import logreader.service.FilePathProvider;
import logreader.service.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.stream.Stream;

public class App {

    private static final Logger logger = LogManager.getLogger(App.class);
     public static void main(String[] args) {
        FilePathProvider filePathProvider = new FilePathProvider();
        FileReader fileReader = new FileReader();
        ApacheLineParser apacheLineParser = new ApacheLineParser();
        LogParser logParser = new LogParser(apacheLineParser::parseLogEntry);
        ResultOutput resultOutput = new ResultOutput();

        Path filePath = filePathProvider.getFilePath("programming-task-example-data.log");
        Stream<String> fileStream = fileReader.fileReader(filePath);

        var logMetrics = logParser.logParser(fileStream);

        logger.info("Log Parser app results");
        resultOutput.printResults(logMetrics);
    }
}

