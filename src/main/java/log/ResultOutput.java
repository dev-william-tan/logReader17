package log;

import records.LogMetrics;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResultOutput {
    private static final Logger logger = LogManager.getLogger(ResultOutput.class);

    public void printResults(LogMetrics logMetrics, int size) {
        printTopIpAddresses(logMetrics, size);
        printTopUrls(logMetrics, size);
        printUniqueIpCount(logMetrics);
    }

    private void printTopIpAddresses(LogMetrics logMetrics, int size) {
        logger.info("Top " + size + " IP addresses visited:");
        logMetrics.getIpCounts().topCounts(size).forEach(
                (ip, count) -> logger.info(ip + ":" + count));
    }

    private void printTopUrls(LogMetrics logMetrics, int size) {
        logger.info("Top " + size + " URLs visited:");
        logMetrics.getUrlCounts().topCounts(size).forEach(
                (url, count) -> logger.info(url + ":" + count));
    }

    private void printUniqueIpCount(LogMetrics logMetrics) {
        var uniqueIp = logMetrics.getIpCounts().uniqueCounts();
        logger.info("Number of unique IP addresses: " + uniqueIp);
    }
}
