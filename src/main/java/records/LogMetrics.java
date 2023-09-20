package records;

import service.StringCounter;

public class LogMetrics {

    private final StringCounter ipCounter = new StringCounter();

    private final StringCounter urlCounter = new StringCounter();

    public StringCounter getIpCounts () {
        return ipCounter;
    }

    public StringCounter getUrlCounts (){
        return urlCounter;
    }

    public void addLogEntry (LogEntry logEntry) {
        ipCounter.add(logEntry.ipAddress());
        urlCounter.add(logEntry.url());
    }

    public void addLogMetrics (LogMetrics logMetrics) {
        ipCounter.addCounters(logMetrics.ipCounter);
        urlCounter.addCounters(logMetrics.urlCounter);
    }

}
