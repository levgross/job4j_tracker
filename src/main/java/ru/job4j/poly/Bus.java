package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Тыр-тыр-тыр");
    }

    @Override
    public void passengers(int quantity) {
        System.out.println("Количество пассажиров - " + quantity);
    }

    @Override
    public double fuel(int gasoline) {
        double price = 47.5;
        return gasoline * price;
    }
}
