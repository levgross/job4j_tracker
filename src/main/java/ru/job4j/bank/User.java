package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает объект User - клиент банка
 * @author Grossevich Lev
 * @version 1.0
 */
public class User {
    /**
     * Объект имеет 2 поля номер паспорта клиента и имя клиента
     */
    private String passport;
    private String username;

    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Метод переопределяет метод equals
     * Сравнение по номеру пасспорта
     * @return  номер паспорта клиента
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    /**
     * Метод переопределяет метод hashCode
     * @return Ключом является номер паспорта клиента
     */
    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
