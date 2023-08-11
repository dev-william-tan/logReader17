package logreader.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Class that finds the Top 3 most repeated entries
public class Top3Finder {

    public List<Map.Entry<String, Integer>> findTop3(List<String> elements) {

        if (elements == null || elements.isEmpty()) throw new IllegalArgumentException("Input list is null or empty");

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
