package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ItemTest {
    @Test
    public void whenSorted() {
    List<Item> items = Arrays.asList(
            new Item("John"),
            new Item("Vanessa"),
            new Item("Bob")

    );
    List<Item> expected = Arrays.asList(items.get(2), items.get(0), items.get(1));
    Collections.sort(items, new ItemAscByName());
        assertThat(items, is(expected));
    }

    @Test
    public void whenSortedReverseOrder() {
        List<Item> items = Arrays.asList(
                new Item("John"),
                new Item("Vanessa"),
                new Item("Bob")

        );
        List<Item> expected = Arrays.asList(items.get(1), items.get(0), items.get(2));
        Collections.sort(items, new ItemDescByName());
        assertThat(items, is(expected));
    }
}