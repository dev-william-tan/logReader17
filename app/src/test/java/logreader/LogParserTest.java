package logreader;

import logreader.util.LogEntry;
import logreader.util.LogParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;



import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogParserTest {
    
    private final Logger logger = LogManager.getLogger(LogParserTest.class);

    private final LogParser logParser = new LogParser();

    @Test
    public void testLogParser() {
        List<String> mockLines = List.of(
                "50.112.00.11 - admin [11/Jul/2018:17:33:01 +0200] \"GET /asset.css HTTP/1.1\" 200 3574",
                "168.41.191.40 - - [09/Jul/2018:10:11:56 +0200] \"GET /blog/category/community/ HTTP/1.1\" 200 3574"
        );

        List<LogEntry> logEntries = logParser.logParser("/Users/william/Foxtel/logReaderJava172/src/test/resources/logFileSmall.log");
        logger.info("small log file");
        logMemory();
        assertEquals(mockLines.size(), logEntries.size());
    }

    @Test
    public void testLogParserWith500mbLogFile() {
        List<LogEntry> logEntries = logParser.logParser("/Users/william/Foxtel/logReaderJava172/src/test/resources/log500mbFile.log");
        logger.info("500mb file");
        logMemory();
        assertEquals(2614640, logEntries.size());
    }

    @Test
    public void testLogParserWith1gbLogFile() {
        List<LogEntry> logEntries = logParser.batchParsing("/Users/william/Foxtel/logReaderJava172/src/test/resources/log1gbFile.log",100);
        logMemory();
        logger.info("1gb file");
        assertEquals(2614640, logEntries.size());

    }

    @Test
    public void testParseLogEntry() {
        // Mock log entry line
        String mockLogLine = "50.112.00.11 - admin [11/Jul/2018:17:33:01 +0200] \"GET /asset.css HTTP/1.1\" 200 3574";

        LogEntry logEntry = LogParser.parseLogEntry(mockLogLine);

        assertEquals("50.112.00.11", logEntry.getIpAddress());
        assertEquals("/asset.css", logEntry.getUrl());
    }
    
   private void logMemory() {
        logger.info("Max Memory: {} Mb", Runtime.getRuntime()
            .maxMemory() / 1048576);
        logger.info("Total Memory: {} Mb", Runtime.getRuntime()
            .totalMemory() / 1048576);
        logger.info("Free Memory: {} Mb", Runtime.getRuntime()
            .freeMemory() / 1048576);
    }
}
