package ru.job4j.poly;

public class PolyUsage {
    public static void main(String[] args) {
        Vehicle bus = new Autobus();
        Vehicle autobus = new Autobus();
        Vehicle train = new Train();
        Vehicle train1 = new Train();
        Vehicle airplane = new Plane();
        Vehicle plane = new Plane();
        Vehicle[] vehicles = new Vehicle[] {bus, autobus, train, train1, airplane, plane};
        for (Vehicle a : vehicles) {
            a.move();
            a.fuel();
        }
    }
}
