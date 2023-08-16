package logreader;

import logreader.log.LogParser;
import logreader.log.LogEntry;
import org.junit.jupiter.api.Test;



import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogParserTest {

    private final LogParser logParser = new LogParser();



    @Test
    public void testLogParser() {
        List<String> mockLines = List.of(
                "50.112.00.11 - admin [11/Jul/2018:17:33:01 +0200] \"GET /asset.css HTTP/1.1\" 200 3574",
                "168.41.191.40 - - [09/Jul/2018:10:11:56 +0200] \"GET /blog/category/community/ HTTP/1.1\" 200 3574"
        );
        String filePath = String.valueOf(ClassLoader.getSystemResource("logFileSmall.log").getPath());
        List<LogEntry> logEntries = logParser.logParser(filePath);
        assertEquals(mockLines.size(), logEntries.size());
    }

    @Test
    public void testParseLogEntry() {
        // Mock log entry line
        String mockLogLine = "50.112.00.11 - admin [11/Jul/2018:17:33:01 +0200] \"GET /asset.css HTTP/1.1\" 200 3574";

        LogEntry logEntry = LogParser.parseLogEntry(mockLogLine);

        assertEquals("50.112.00.11", logEntry.getIpAddress());
        assertEquals("/asset.css", logEntry.getUrl());
    }

}
