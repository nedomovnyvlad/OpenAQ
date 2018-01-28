package com.example.vlad.openaq.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DescByCountComparatorTest {

    private final CityInfo.DescByCountComparator comparator = new CityInfo.DescByCountComparator();

    @Test
    public void compareTo_shouldReturnZeroIfCountsAreEqual() {
        CityInfo first = CityInfo.create("Name1", 100);
        CityInfo second = CityInfo.create("Name2", 100);
        assertEquals(0, comparator.compare(first, second));
    }

    @Test
    public void compareTo_shouldReturnNegativeIfFirstCountIsGreater() {
        CityInfo first = CityInfo.create("Name1", 200);
        CityInfo second = CityInfo.create("Name2", 100);
        assertTrue(comparator.compare(first, second) < 0);
    }

    @Test
    public void compareTo_shouldReturnPositiveIfFirstCountIsLess() {
        CityInfo first = CityInfo.create("Name1", 100);
        CityInfo second = CityInfo.create("Name2", 200);
        assertTrue(comparator.compare(first, second) > 0);
    }
}
