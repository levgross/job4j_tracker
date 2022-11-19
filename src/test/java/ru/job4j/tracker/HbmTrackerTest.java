package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class HbmTrackerTest {

    private final HbmTracker tracker = new HbmTracker();
    private final SessionFactory sf = tracker.getSf();

    @AfterEach
    public void wipeTable() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM items")
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item();
        item.setName("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName()).isEqualTo(item.getName());
    }

    @Test
    public void whenReplaceItemThenTrackerHasNewData() {
        LocalDateTime time = LocalDateTime.now();
        LocalDateTime newTime = time.minusHours(1);
        Item item = new Item("item");
        item.setCreated(time);
        Item newItem = new Item("new item");
        newItem.setCreated(newTime);
        tracker.add(item);
        assertThat(tracker.replace(item.getId(), newItem)).isTrue();
        Item result = tracker.findById(item.getId());
        assertThat(result.getName()).isEqualTo(newItem.getName());
        assertThat(result.getCreated()).isEqualToIgnoringSeconds(newItem.getCreated());
    }

    @Test
    public void whenDeleteItemThenTrackerDoesNotHaveItem() {
        Item item = new Item("item");
        Item newItem = new Item("new item");
        tracker.add(item);
        assertThat(tracker.delete(item.getId())).isTrue();
        var result = tracker.findAll();
        assertThat(result).doesNotContain(item);
    }

    @Test
    public void whenFindAll() {
        Item item = new Item("item");
        Item test = new Item("test2");
        tracker.add(item);
        tracker.add(test);
        var results = tracker.findAll();
        assertThat(results).containsExactly(item, test);
    }

    @Test
    public void whenFindByName() {
        Item item = new Item("item");
        Item test = new Item("test2");
        tracker.add(item);
        tracker.add(test);
        var results = tracker.findByName("test2");
        assertThat(results).containsExactly(test);
    }
}