package logreader.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

//Class processes logs and pass them to relevant entries
public class LogParser {

    private static final Logger logger = LogManager.getLogger(LogParser.class);

    //Parses the log line and processes into LogEntries
    public List<LogEntry> logParser (String filePath) {
        try(Stream<String> lines = Files.lines(Path.of(filePath))) {

            return lines
                    .map(LogParser::parseLogEntry)
                    .filter(Objects::nonNull)
                    .toList();

        } catch (Exception e) {
            logger.error("Error occurred during parsing: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    //Sets the extracted IP and URL into logEntries from line
    public static LogEntry parseLogEntry(String line) {
        String[] logLine = line.split(" ");

        if (logLine.length >= 9) {
            LogEntry logEntry = new LogEntry();
            logEntry.setIpAddress(logLine[0]);
            logEntry.setUrl(logLine[6]);

            return logEntry;
        } else {
            logger.warn("Skipping invalid log entry: " + line);
            return null;
        }
    }

}
