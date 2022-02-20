package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] left = o1.split("/");
        String[] right = o2.split("/");
        int res = right[0].compareTo(left[0]);
        return res == 0 ? o1.compareTo(o2) : res;
    }
}
