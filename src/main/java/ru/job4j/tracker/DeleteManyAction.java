package ru.job4j.tracker;

public class DeleteManyAction implements UserAction {
    private final Output out;

    public DeleteManyAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Delete many items";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Delete many items ===");
        int id = input.askInt("Enter id: ");
        for (int i = 0; i < 5000; i++) {
            if (tracker.delete(id++)) {
                out.println("Заявка удалена успешно.");
            } else {
                out.println("Ошибка удаления заявки.");
            }
        }
        return true;
    }
}
