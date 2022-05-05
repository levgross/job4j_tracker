package ru.job4j.tracker;

public final class SingleTracker {
    private static MemTracker tracker = null;

    private SingleTracker() {
    }

    public static MemTracker getTracker() {
        if (tracker == null) {
            tracker = new MemTracker();
        }
        return tracker;
    }

    public static void main(String[] args) {
        MemTracker start = SingleTracker.getTracker();
        Item item = new Item("test");
        start.add(item);
        start.findAll();
        start.findByName("test");
        start.findById(0);
        start.replace(0, item);
        start.delete(0);
    }
}
