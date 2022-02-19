package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] left = o1.split("/");
        String[] right = o2.split("/");
        int size = Math.min(left.length, right.length);
        int res;
        if (!left[0].equals(right[0])) {
            return right[0].compareTo(left[0]);
        }
        if (size == 1) {
            return Integer.compare(left.length, right.length);
        }
            for (int i = 1; i < size; i++) {
                if (!left[i].equals(right[i])) {
                    return left[i].compareTo(right[i]);
                }
            }
        return Integer.compare(left.length, right.length);
    }
}
