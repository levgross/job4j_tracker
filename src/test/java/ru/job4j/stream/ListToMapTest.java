package ru.job4j.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ListToMapTest {

    @Test
    public void whenInputListNotHaveDuplicate() {
        List<Student> list = Arrays.asList(
                new Student("Ivanov", 30),
                new Student("Petrov", 40),
                new Student("Sidorov", 50)
        );
        Map<String, Student> result = ListToMap.convert(list);
        Map<String, Student> expected = new LinkedHashMap<>();
        expected.put("Ivanov", new Student("Ivanov", 30));
        expected.put("Petrov", new Student("Petrov", 40));
        expected.put("Sidorov", new Student("Sidorov", 50));
        assertEquals(expected, result);
    }

    @Test
    public void whenInputListHaveDuplicate() {
        List<Student> list = Arrays.asList(
                new Student("Ivanov", 30),
                new Student("Petrov", 40),
                new Student("Ivanov", 50),
                new Student("Sidorov", 60),
                new Student("Petrov", 70)
        );
        Map<String, Student> result = ListToMap.convert(list);
        Map<String, Student> expected = new LinkedHashMap<>();
        expected.put("Ivanov", new Student("Ivanov", 30));
        expected.put("Petrov", new Student("Petrov", 40));
        expected.put("Sidorov", new Student("Sidorov", 60));
        assertEquals(expected, result);
    }
}
