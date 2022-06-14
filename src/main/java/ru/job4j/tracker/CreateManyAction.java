package ru.job4j.tracker;

public class CreateManyAction implements UserAction {
    private final Output out;

    public CreateManyAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add many new Items";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Create many new Items ===");
        String name = input.askStr("Enter name: ");
        for (int i = 0; i < 5000000; i++) {
            Item item = new Item(name);
            tracker.add(item);
            out.println("Добавленная заявка: " + item);
        }
        return true;
    }
}
