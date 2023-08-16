package logreader;


import logreader.log.LogParser;
import logreader.log.Top3Finder;
import logreader.service.FilePathProvider;
import logreader.service.Results;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class App {

    private static final Logger logger = LogManager.getLogger(App.class);
    private static final FilePathProvider filePathProvider= new FilePathProvider();
    private static final LogParser logParser = new LogParser();
    private static final Top3Finder top3Finder = new Top3Finder();
    private static final Results results = new Results(top3Finder);
    public static void main(String[] args) {
        String filePath = filePathProvider.getFilePath("programming-task-example-data.log");

        if (filePath == null) {
            logger.error("Provided file is invalid");
            return;
        }

        //Parse log file into a list of entries
        var logEntries = logParser.logParser(filePath);

        //Returns results of the required tasks
        results.printResults(logEntries);
    }
}

