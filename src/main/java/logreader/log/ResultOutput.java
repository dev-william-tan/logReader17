package logreader.log;

import logreader.App;
import logreader.records.LogMetrics;
import logreader.service.FilePathProvider;
import logreader.service.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResultOutput {
    private static final Logger logger = LogManager.getLogger(App.class);

    public void printResults(LogMetrics logMetrics) {
        printTopIpAddresses(logMetrics, 3);
        printTopUrls(logMetrics, 3);
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
