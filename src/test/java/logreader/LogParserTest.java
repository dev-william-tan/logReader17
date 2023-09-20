package logreader;

import log.LogParser;
import records.LogEntry;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogParserTest {

    @Test
    public void testSingleLine() {

        var logParser = new LogParser(line -> {
            assertEquals("myLogLine", line);
            return new LogEntry("myIp", "myUrl");
        });

        var logMetrics = logParser.logParser(
                Stream.of("myLogLine")
        );

        assertEquals(1, logMetrics.getIpCounts().getCountsByKey("myIp"));
        assertEquals(1, logMetrics.getUrlCounts().getCountsByKey("myUrl"));

    }

    @Test
    public void testMultiLine() {

        var logParser = new LogParser(line -> {
            String[] subString = line.split(",");
            return new LogEntry(subString[0], subString[1]);
        });

        var logMetrics = logParser.logParser(
                Stream.of("ip1,url1", "ip2,url2", "ip1,url3")
        );

        assertEquals(2, logMetrics.getIpCounts().uniqueCounts());
        assertEquals(3, logMetrics.getUrlCounts().uniqueCounts());
        assertEquals(2, logMetrics.getIpCounts().getCountsByKey("ip1"));
        assertEquals(1, logMetrics.getUrlCounts().getCountsByKey("url2"));
    }

    @Test
    public void testNullLine() {

        var logParser = new LogParser(line -> null);
        var logMetrics = logParser.logParser(
                Stream.of("myLogLine")
        );

        assertEquals(0, logMetrics.getUrlCounts().uniqueCounts());
        assertEquals(0, logMetrics.getIpCounts().uniqueCounts());
    }

    @Test
    public void testEmptyStream() {
        var logParser = new LogParser(line -> new LogEntry("myIp", "myUrl"));

        var logMetrics = logParser.logParser(
                Stream.empty()
        );

        assertEquals(0, logMetrics.getIpCounts().uniqueCounts());
        assertEquals(0, logMetrics.getUrlCounts().uniqueCounts());
    }


}
