package logreader.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

//Class that finds the Top 3 most repeated entries
public class Top3Finder {
    private static final Logger logger = LogManager.getLogger(Top3Finder.class);

    //Find the Top 3 for URLs
    public List<Map.Entry<String, Integer>> findTop3Urls(List<LogEntry> logEntries) {

        try {
            List<String> urls = logEntries.stream()
                .map(LogEntry::getUrl)
                .toList();

            return findTop3(urls);
        } catch (Exception e) {
            logger.warn("Error finding top 3 Urls: " + e.getMessage());
            return List.of();
        }
    }

    //Find Top 3 for IP Addresses
    public List<Map.Entry<String, Integer>> findTop3IP(List<LogEntry> logEntries) {
        try {
            List<String> ipAddresses = logEntries.stream()
                    .map(LogEntry::getIpAddress)
                    .toList();
            return findTop3(ipAddresses);
        } catch (Exception e) {
            logger.warn("Error finding top 3 IP addresses: " + e.getMessage());
            return List.of();
        }
    }

    //Finding the number of uniquue Ips
    public Set<String> findUniqueIps(List<LogEntry> logEntries) {
        return logEntries.stream()
                .map(LogEntry::getIpAddress)
                .collect(Collectors.toSet());
    }


    //Find the top 3 from the provided list
    public List<Map.Entry<String, Integer>> findTop3(List<String> elements) {

        if (elements == null || elements.isEmpty()) {
            logger.error("Input list is null or Empty");
            return Collections.emptyList();
        }

        Map<String, Integer> occurrenceMap = countOccurrences(elements);

         //find the top 3 entries with the highest occurrences
        try {
            return occurrenceMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error finding top 3 entries: " + e.getMessage());
        }

    }

    private static Map<String, Integer> countOccurrences(List<String> elements) {
        return elements.stream().collect(
                Collectors.groupingBy(e ->
                        e, Collectors.summingInt(e -> 1)));
    }
}
