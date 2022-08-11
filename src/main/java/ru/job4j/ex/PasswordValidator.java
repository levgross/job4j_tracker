package ru.job4j.ex;

import java.util.function.Predicate;

import static java.lang.Character.*;

public class PasswordValidator {
    public static String validate(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null!");
        }
        char[] chars = password.toCharArray();
        int charsNum = chars.length;
        if (charsNum < 8 || charsNum > 32) {
            throw new IllegalArgumentException("Wrong password length!");
        }
        if (!check(chars, f -> isUpperCase((Character) f))) {
            throw new IllegalArgumentException("At least one character should be a letter in upper case!");
        }
        if (!check(chars, f -> isLowerCase((Character) f))) {
            throw new IllegalArgumentException("At least one character should be a letter in lower case!");
        }
        if (!check(chars, f -> isDigit((Character) f))) {
            throw new IllegalArgumentException("At least one character should be a digit!");
        }
        if (!check(chars, f -> !isLetterOrDigit((Character) f))) {
            throw new IllegalArgumentException("At least one character should be a special symbol!");
        }
        if (password.toLowerCase().contains("qwerty")
                || password.contains("12345")
                || password.toLowerCase().contains("password")
                || password.toLowerCase().contains("admin")
                || password.toLowerCase().contains("user")) {
            throw new IllegalArgumentException("Contains bad sequence of characters!");
        }
        return password;
    }

    private static boolean check(char[] chars, Predicate filter) {
        for (char symbol : chars) {
            if (filter.test(symbol)) {
                return true;
            }
        }
        return false;
    }
}
