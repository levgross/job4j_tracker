package ru.job4j.ex;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FactTest {
    @Test
    public void whenNIs5Then120() {
        int rsl = new Fact().calc(5);
        assertThat(rsl, is(120));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNIsLess0ThenFinish() {
        new Fact().calc( -1);
    }
}
