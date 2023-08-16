package logreader.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Class that finds the Top 3 most repeated entries
public class Top3Finder {
    private static final Logger logger = LogManager.getLogger(Top3Finder.class);

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
