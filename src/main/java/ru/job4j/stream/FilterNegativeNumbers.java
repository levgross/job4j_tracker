package ru.job4j.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterNegativeNumbers {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(0, -5, 3, -1, 2, -3, -1, 7);
        List<Integer> positive = numbers.stream().filter(i -> i > 0).collect(Collectors.toList());
        positive.forEach(System.out::println);
    }
}