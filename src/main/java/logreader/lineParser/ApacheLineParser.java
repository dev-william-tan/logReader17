package logreader.lineParser;

import logreader.records.LogEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApacheLineParser{

    private static final Logger logger = LogManager.getLogger(ApacheLineParser.class);

    //Parse logLine[0] = ip logLine[6] = url
    public LogEntry parseLogEntry(String line) {
        String[] logLine = line.split(" ");
        if (logLine.length >= 9) {
            return new LogEntry(logLine[0], logLine[6]);
        } else {
            logger.warn("Skipping invalid log entry: " + line);
            return null;
        }
    }
}
