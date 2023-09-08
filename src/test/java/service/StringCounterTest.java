package service;

import logreader.service.StringCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCounterTest {

    @Test
    public void testAddMethod() {

        StringCounter counter = new StringCounter();
        counter.add("element1");
        counter.add("element2");
        counter.add("element1");

        assertEquals(2, counter.getCountsByKey("element1"));
        assertEquals(1, counter.getCountsByKey("element2"));
    }

    @Test
    public void testTopCount() {

        StringCounter counter = new StringCounter();
        counter.add("element1");
        counter.add("element2");
        counter.add("element1");
        counter.add("element1");
        counter.add("element3");

        var topCounts = counter.topCounts(3);

        assertEquals(3, topCounts.size());
        assertEquals(3, topCounts.get("element1"));
        assertEquals(1, topCounts.get("element3"));
    }

    @Test
    public void testAddCounter() {

        StringCounter counter1 = new StringCounter();
        counter1.add("element1");
        counter1.add("element2");
        counter1.add("element1");

        StringCounter counter2 = new StringCounter();
        counter2.add("element1");
        counter2.add("element2");
        counter2.add("element3");

        counter1.addCounters(counter2);

        assertEquals(3, counter1.uniqueCounts());
        assertEquals(3, counter1.getCountsByKey("element1"));
        assertEquals(2, counter1.getCountsByKey("element2"));
        assertEquals(1, counter1.getCountsByKey("element3"));
    }

    @Test
    public void testTopCounts_WithEmpty() {
        StringCounter counter = new StringCounter();

        var topCounts = counter.topCounts(3);

        assertEquals(0, topCounts.size());

    }

}
