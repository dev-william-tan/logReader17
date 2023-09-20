package service;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StringCounter {

    private final Map<String, Integer> stringCounts = new HashMap<>();

    public void add (String element) {
        stringCounts.put(element, stringCounts.getOrDefault(element, 0) +1);
    }

    public SortedMap<String, Integer> topCounts (int size) {
        //Tree map with custom comparator
        SortedMap<String,Integer> sortedMap = new TreeMap<>(
                (key1, key2) -> {
                    int valueComparison = stringCounts.get(key2).compareTo(stringCounts.get(key1));

                    return valueComparison == 0 ? key2.compareTo(key1) : valueComparison;
                }
        );

        sortedMap.putAll(stringCounts);

        return sortedMap.entrySet().stream()
                .limit(size)
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (e1, e2) -> e1, // Merge function, keep existing value
                    TreeMap::new
                ));
    }

    public void addCounters (StringCounter counter) {
        counter.stringCounts.forEach((key, value) ->
                stringCounts.put(key, stringCounts.getOrDefault(key, 0) + value));
    }

    public int uniqueCounts() {
        return stringCounts.size();
    }

    public int getCountsByKey(String key) {
        return stringCounts.get(key);
    }

}
