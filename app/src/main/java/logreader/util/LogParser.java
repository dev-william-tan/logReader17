package logreader.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LogEntry> batchParsing(String filePath, int batchSize) {
    List<LogEntry> logEntries = new ArrayList<>();

    try (Stream<String> lines = Files.lines(Path.of(filePath))) {
        Iterator<String> lineIterator = lines.iterator();
        List<String> batchLines = new ArrayList<>(batchSize);

        while (lineIterator.hasNext()) {
            String line = lineIterator.next();
            batchLines.add(line);

            if (batchLines.size() >= batchSize || !lineIterator.hasNext()) {
                List<LogEntry> batchLogEntries = batchLines.stream()
                    .map(LogParser::parseLogEntry)
                    .filter(Objects::nonNull)
                    .toList();

                logEntries.addAll(batchLogEntries);
                batchLines.clear();
            }
        }
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

    return logEntries;
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
