package logreader;

import logreader.log.LogEntry;
import logreader.log.Top3Finder;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Top3FinderTest {

    private final Top3Finder top3Finder = new Top3Finder();

    //Test when any valid collection return a Top 3 counter
    @Test
    public void testFindTop3WithValidInput() {
        List<String> elements = Arrays.asList("A", "B", "C", "A", "A", "B", "D");
        List<Map.Entry<String, Integer>> top3Entries = top3Finder.findTop3(elements);


        assertEquals(3, top3Entries.size());

        assertTrue(top3Entries.contains(Map.entry("A", 3)));
        assertTrue(top3Entries.contains(Map.entry("B", 2)));
        assertTrue(top3Entries.contains(Map.entry("C", 1)));
    }

    //Test FindTop3 when an emptyList is provided it should throw an Exception
    @Test
    public void testFindTop3WithEmptyList() {
        List<Map.Entry<String, Integer>> result = top3Finder.findTop3(Collections.emptyList());
        assertEquals(Collections.emptyList(), result);
    }

    //Test FindTop3 when a null is provided it should throw an Exception
    @Test
    public void testFindTop3WhenListIsNull() {
        List<Map.Entry<String, Integer>> result = top3Finder.findTop3(null);
        assertEquals(Collections.emptyList(), result);
    }

    //Test FindUniqueIp when a valid list is provided
    @Test
    public void testFindUniqueIp() {
        var result = top3Finder.findUniqueIps(mockLogEntries());

        assertEquals(3, result.size());
    }

    //Test FindUniqueIp when an empty list is provided
    @Test
    public void testFindUniqueIpWhenEmpty() {
        var result = top3Finder.findUniqueIps(Collections.emptyList());

        assertEquals(0, result.size());
    }

    //Test the top 3 URL when a collection of log entries is provided
    @Test
    public void testTop3URL() {
        List<Map.Entry<String, Integer>> expected = List.of(
            new AbstractMap.SimpleEntry<>("url1", 3),
            new AbstractMap.SimpleEntry<>("url3", 2),
            new AbstractMap.SimpleEntry<>("url2", 1)
        );

        var result = top3Finder.findTop3Urls(mockLogEntries());

        assertEquals(expected, result);
    }

    //Testing the top 3 with IP present
    @Test
    public void testTop3IP() {
        List<Map.Entry<String, Integer>> expected = List.of(
            new AbstractMap.SimpleEntry<>("ip3", 3),
            new AbstractMap.SimpleEntry<>("ip1", 2),
            new AbstractMap.SimpleEntry<>("ip2", 1)
        );

        var result = top3Finder.findTop3IP(mockLogEntries());

        assertEquals(expected, result);
    }

    //Mock Entries
    private List<LogEntry> mockLogEntries() {
        return List.of(
            createLogEntry("ip1", "url1"),
            createLogEntry("ip2", "url1"),
            createLogEntry("ip1", "url2"),
            createLogEntry("ip3", "url3"),
            createLogEntry("ip3", "url3"),
            createLogEntry("ip3", "url1")
        );
    }

    private LogEntry createLogEntry(String ipAddress, String url) {
        LogEntry logEntry = new LogEntry();
        logEntry.setIpAddress(ipAddress);
        logEntry.setUrl(url);
        // Set other properties as needed
        return logEntry;
    }

}
