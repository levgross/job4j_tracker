package ru.job4j.poly;

public class Autobus implements Vehicle {
    @Override
    public void move() {
        System.out.println(getClass().getSimpleName() + " двигается по скоростным трассам.");
    }

    @Override
    public void fuel() {
        System.out.println(getClass().getSimpleName() + " работает на дизельном топливе.");
    }
}
