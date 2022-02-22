package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        Predicate<Person> predByName = (p) -> p.getName().contains(key);
        Predicate<Person> predBySurname = (p) -> p.getSurname().contains(key);
        Predicate<Person> predByPhone = (p) -> p.getPhone().contains(key);
        Predicate<Person> predByAddress = (p) -> p.getAddress().contains(key);
        Predicate<Person> combine = predByName.or(predBySurname.or(predByPhone.or(predByAddress)));
        ArrayList<Person> result = new ArrayList<>();
        for (var person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
