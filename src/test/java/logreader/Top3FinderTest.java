package logreader;

import logreader.log.Top3Finder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Top3FinderTest {

    private final Top3Finder top3Finder = new Top3Finder();

    //Test when a valid input is passed, the method should return the top 3 occurrences of provided inputs
    @Test
    public void testFindTop3WithValidInput() {
        List<String> elements = Arrays.asList("A", "B", "C", "A", "A", "B", "D");
        List<Map.Entry<String, Integer>> top3Entries = top3Finder.findTop3(elements);


        assertEquals(3, top3Entries.size());

        assertTrue(top3Entries.contains(Map.entry("A", 3)));
        assertTrue(top3Entries.contains(Map.entry("B", 2)));
        assertTrue(top3Entries.contains(Map.entry("C", 1)));
    }

    //Test FindTop3 when an emptyList is provided it should throw an Exception
    @Test
    public void testFindTop3WithEmptyList() {
        List<Map.Entry<String, Integer>> result = top3Finder.findTop3(Collections.emptyList());
        assertEquals(Collections.emptyList(), result);
    }
    //Test FindTop3 when a null is provided it should throw an Exception
    @Test
    public void testFindTop3WhenListIsNull() {
        List<Map.Entry<String, Integer>> result = top3Finder.findTop3(null);
        assertEquals(Collections.emptyList(), result);
    }

}