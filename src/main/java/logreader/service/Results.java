package logreader.service;

import logreader.log.LogEntry;
import logreader.log.Top3Finder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

//A class that returns the result of the requested tasks
public class Results {

    private static final Logger logger = LogManager.getLogger(Results.class);
    private final Top3Finder top3Finder;

    public Results(Top3Finder top3Finder) {
        this.top3Finder = top3Finder;
    }

    public void printResults(List<LogEntry> logEntries) {
        //Process the log entries for desired result
        Set<String> uniqueIPs = findUniqueIps(logEntries);
        List<Map.Entry<String, Integer>> top3Url = findTop3Urls(logEntries);
        List<Map.Entry<String, Integer>> top3Ip = findTop3IP(logEntries);

        //Logging for results
        logger.info("Number of unique IP addresses: " + uniqueIPs.size());

        logger.info("Top 3 IP addresses visited:");
        top3Ip.forEach(ip -> logger.info(ip.getKey() + " - " + ip.getValue()));

        logger.info("Top 3 URLs visited:");
        top3Url.forEach(url -> logger.info(url.getKey() + " - " + url.getValue()));
    }

    //Passing log entries to be processed to find top 3 url
    private List<Map.Entry<String, Integer>> findTop3Urls(List<LogEntry> logEntries) {

        try {
            List<String> urls = logEntries.stream()
                .map(LogEntry::getUrl)
                .toList();

            return top3Finder.findTop3(urls);
        } catch (Exception e) {
            logger.warn("Error finding top 3 Urls: " + e.getMessage());
            return List.of();
        }
    }

    //Passing log entries to be processed to find top 3 ip
    private List<Map.Entry<String, Integer>> findTop3IP(List<LogEntry> logEntries) {
        try {
            List<String> ipAddresses = logEntries.stream()
                    .map(LogEntry::getIpAddress)
                    .toList();
            return top3Finder.findTop3(ipAddresses);
        } catch (Exception e) {
            logger.warn("Error finding top 3 IP addresses: " + e.getMessage());
            return List.of();
        }
    }

    //Passing log entries to a Set to find unique IP addresses
    private Set<String> findUniqueIps(List<LogEntry> logEntries) {
        return logEntries.stream()
                .map(LogEntry::getIpAddress)
                .collect(Collectors.toSet());
    }

}
