package ru.job4j.oop;

public class Stud {

    public void music(String lyrics) {
        System.out.println("I can sign a song : " + lyrics);
    }

    public void song() {
        System.out.println("I believe I can fly");
    }

    public static void main(String[] args) {
        Stud petya = new Stud();
        String song = "I believe, I can fly";
        petya.music(song);
    }
}
