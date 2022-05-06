package ru.job4j.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SqlTrackerTest {
    private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
              config.getProperty("url"),
              config.getProperty("username"),
              config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(tracker.findById(item.getId()), is(item));
    }

    @Test
    public void whenDeleteItemByGeneratedIdThenTrue() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertTrue(tracker.delete(item.getId()));
    }

    @Test
    public void whenReplaceItemByGeneratedIdThenTrue() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        Item newItem = new Item("newItem");
        tracker.add(item);
        assertTrue(tracker.replace(item.getId(), newItem));
        assertThat(tracker.findById(item.getId()).getName(), is("newItem"));
    }

    @Test
    public void whenFindByNameThen2Items() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        Item item1 = new Item("item");
        Item item2 = new Item("item2");
        tracker.add(item);
        tracker.add(item1);
        tracker.add(item2);
        List<Item> items = List.of(
                tracker.findById(item.getId()),
                tracker.findById(item1.getId())
        );
        assertEquals(items, tracker.findByName("item"));
    }

    @Test
    public void whenFindAllThen3Items() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        Item item1 = new Item("item");
        Item item2 = new Item("item2");
        tracker.add(item);
        tracker.add(item1);
        tracker.add(item2);
        List<Item> items = List.of(
                tracker.findById(item.getId()),
                tracker.findById(item1.getId()),
                tracker.findById(item2.getId())
        );
        assertEquals(items, tracker.findAll());
    }
}
