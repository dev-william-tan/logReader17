package logreader.log;

import logreader.records.LogEntry;
import logreader.records.LogMetrics;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

//Class processes logs and pass them to relevant entries
public class LogParser {

    private final Function<String, LogEntry> lineParser;

    public LogParser(Function<String, LogEntry> lineParser) {
        this.lineParser = lineParser;
    }

    //Parses the log line and processes into LogEntries
    public LogMetrics logParser (Stream<String> lines)  {
            return lines
                    .map(lineParser)
                    .filter(Objects::nonNull)
                    .collect(LogMetrics::new,
                            LogMetrics::addLogEntry,
                            LogMetrics::addLogMetrics);
    }

}
