package records;

import logreader.records.LogEntry;
import logreader.records.LogMetrics;
import logreader.service.StringCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogMetricsTest {

    @Test
    public void testAddLogEntries_ReturnsCorrectEntries() {
        LogMetrics logMetrics = new LogMetrics();
        logMetrics.addLogEntry(new LogEntry("ip1", "url1"));
        logMetrics.addLogEntry(new LogEntry("ip2", "url2"));
        logMetrics.addLogEntry(new LogEntry("ip1", "url3"));

        StringCounter ipCounts = logMetrics.getIpCounts();
        StringCounter urlCounts = logMetrics.getUrlCounts();

        assertEquals(2, ipCounts.getCountsByKey("ip1"));
        assertEquals(1, ipCounts.getCountsByKey("ip2"));
        assertEquals(1, urlCounts.getCountsByKey("url1"));
        assertEquals(1, urlCounts.getCountsByKey("url2"));
        assertEquals(1, urlCounts.getCountsByKey("url3"));
    }

    @Test
    public void testAddLogMetrics_ReturnsCombinedMetrics() {
        LogMetrics logMetrics1 = new LogMetrics();
        logMetrics1.addLogEntry(new LogEntry("ip1", "url1"));
        logMetrics1.addLogEntry(new LogEntry("ip2", "url2"));

        LogMetrics logMetrics2 = new LogMetrics();
        logMetrics2.addLogEntry(new LogEntry("ip1", "url1"));
        logMetrics2.addLogEntry(new LogEntry("ip3", "url3"));

        logMetrics1.addLogMetrics(logMetrics2);

        StringCounter ipCounts = logMetrics1.getIpCounts();
        StringCounter urlCounts = logMetrics1.getUrlCounts();

        assertEquals(2, ipCounts.getCountsByKey("ip1"));
        assertEquals(1, ipCounts.getCountsByKey("ip3"));
        assertEquals(2, urlCounts.getCountsByKey("url1"));
        assertEquals(1, urlCounts.getCountsByKey("url3"));

    }


}
