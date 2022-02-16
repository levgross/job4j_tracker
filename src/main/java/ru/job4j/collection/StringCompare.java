package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int res;
        int lengthCompare = Integer.compare(left.length(), right.length());
        if (lengthCompare >= 0) {
            for (int i = 0; i < right.length(); i++) {
                res = Character.compare(left.charAt(i), right.charAt(i));
                if (res != 0) {
                    return res;
                }
            }
            res = lengthCompare == 0 ? 0 : 1;
        } else {
            for (int i = 0; i < left.length(); i++) {
                res = Character.compare(left.charAt(i), right.charAt(i));
                if (res != 0) {
                    return res;
                }
            }
            res = -1;
        }
        return res;
    }
}