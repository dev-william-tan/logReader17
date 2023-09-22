package lineParser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ApacheLineParserTest {
    ApacheLineParser apacheLineParser = new ApacheLineParser();

    @Test
    void returnLogEntryWhenValid() {
        String logLine = "ip dash space admin date GET url HTTP/1.1\" 200 3574 \"-\" browser OS AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1092.0 Safari/536.6\"\n";
        String expectedIp = "ip";
        String expectedUrl = "url";
        var result = apacheLineParser.parseLogEntry(logLine);

        assertEquals(result.ipAddress(), expectedIp);
        assertEquals(result.url(), expectedUrl);
    }

    @Test
    void returnNullWhenEntryIsTooShort() {
        String logLine = "0 1 2 3 12";
        var result = apacheLineParser.parseLogEntry(logLine);

        assertNull(result);
    }
}
