package ru.job4j.ex;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


public class PasswordValidatorTest {

    @Test
    public void whenPasswordIsNullThenException() {
        String password = null;
        assertThatThrownBy(() -> {
            PasswordValidator.validate(password);
        })
                .hasMessage("Password cannot be null!");
    }

    @Test
    public void whenSizeIs7ThenException() {
        String password = "1234567";
        assertThatThrownBy(() -> {
            PasswordValidator.validate(password);
        })
                .hasMessage("Wrong password length!");
    }

    @Test
    public void whenSizeIs33ThenException() {
        String password = "012345678901234567890123456789012";
        assertThatThrownBy(() -> {
            PasswordValidator.validate(password);
        })
                .hasMessage("Wrong password length!");
    }

    @Test
    public void whenPasswordHasNoUpperCaseThenException() {
        String password = "zxcvbn8!";
        assertThatThrownBy(() -> {
            PasswordValidator.validate(password);
        })
                .hasMessage("At least one character should be a letter in upper case!");
    }

    @Test
    public void whenPasswordHasNoLowerCaseThenException() {
        String password = "ZXCVBN8!";
        assertThatThrownBy(() -> {
            PasswordValidator.validate(password);
        })
                .hasMessage("At least one character should be a letter in lower case!");
    }

    @Test
    public void whenPasswordHasNoDigitThenException() {
        String password = "ZxcvbnN!";
        assertThatThrownBy(() -> {
            PasswordValidator.validate(password);
        })
                .hasMessage("At least one character should be a digit!");
    }

    @Test
    public void whenPasswordHasNoSymbolThenException() {
        String password = "Zxcvbn88";
        assertThatThrownBy(() -> {
            PasswordValidator.validate(password);
        })
                .hasMessage("At least one character should be a special symbol!");
    }

    @Test
    public void whenPasswordHasBadSubstringThenException() {
        String password1 = "Z12345qw!";
        assertThatThrownBy(() -> {
            PasswordValidator.validate(password1);
        })
                .hasMessage("Contains bad sequence of characters!");

        String password2 = "Z12345qwErtY!";
        assertThatThrownBy(() -> {
            PasswordValidator.validate(password2);
        })
                .hasMessage("Contains bad sequence of characters!");

        String password3 = "Z1passwordqw!";
        assertThatThrownBy(() -> {
            PasswordValidator.validate(password3);
        })
                .hasMessage("Contains bad sequence of characters!");

        String password4 = "Z1ADMINw!";
        assertThatThrownBy(() -> {
            PasswordValidator.validate(password4);
        })
                .hasMessage("Contains bad sequence of characters!");

        String password5 = "User21qw!";
        assertThatThrownBy(() -> {
            PasswordValidator.validate(password5);
        })
                .hasMessage("Contains bad sequence of characters!");
    }
}