package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByNameActionTest {
    @Test
    public void whenExecuteOK() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        String name = "Item";
        Item item = new Item(name);
        tracker.add(item);
        FindByNameAction fbn = new FindByNameAction(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(name);
        fbn.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by name ===" + ln + item + ln));
    }

    @Test
    public void whenExecuteFail() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        String name = "Item";
        String wrongName = "another name";
        Item item = new Item(name);
        tracker.add(item);
        FindByNameAction fbn = new FindByNameAction(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(wrongName);
        fbn.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("=== Find items by name ===" + ln + "Заявки с именем: " + wrongName + " не найдены." + ln));
    }
}
