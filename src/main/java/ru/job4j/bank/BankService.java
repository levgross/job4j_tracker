package ru.job4j.bank;

import java.util.*;

/**
 * Класс описывает работу с клиентами и их счетами
 * @author Grossevich Lev
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение задания осуществляется в коллекции типа HashMap
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход клиента и добавляет его в список клиентов.
     * Клиент добавляется с пустым списком счетов типа ArrayList.
     * Если такой клиент уже есть в списке, добавление игнорируется.
     * Сравнение клиентов про номеру пасспорта.
     * @see User#hashCode()
     * @param user клиент банка, которого нужно добавить.
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод принимает на вход пасспорт клиента, которому нужно добавить счет, и сам счет.
     * Метод добавляет клиенту новый счет.
     * Находит клиента по номеру паспорта.
     * Если клиент есть в списке клиентов банка и у нег ещё нет такого счета,
     * то добавляет счёт.
     * @param passport пасспорт клиента, которому нужно добавить счет.
     * @param account счёт, который нужно добавить.
     */
    public void addAccount(String passport, Account account) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent() && !users.get(user.get()).contains(account)) {
            users.get(user.get()).add(account);
        }
    }

    /**
     * Метод принимает на вход номер пасспорта и находит по нему клиента.
     * Если такого клиента нет, аозвращает null.
     * @param passport пасспорт клиента, которого надо найти.
     * @return возвращает клиента или null, если клиента с таким пасспортом нет.
     */
    public Optional<User> findByPassport(String passport) {
           return users.keySet()
                    .stream()
                    .filter(p -> passport.equals(p.getPassport()))
                    .findFirst();
    }

    /**
     * Метод находит счет клиента, принимает на вход номер пасспорта и реквизиты счета, который нужно найти.
     * @param passport пасспорт клиента, у которого надо найти счет.
     * @param requisite реквизиты счета, который нужно найти.
     * @return возвращает счет или null, если нет клиента с таким пасспортом
     * или у клиента нет счета с такими реквизитами.
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> user = findByPassport(passport);
        if (user.isPresent()) {
            return users.get(user.get())
                    .stream()
                    .filter(r -> requisite.equals(r.getRequisite()))
                    .findFirst();
        }
        return Optional.empty();
    }

    /**
     * Метод переводит средства с одного счета на другой, принимает на вход сумму,
     * пасспорт и счет отправителя и получателя.
     * @param srcPassport пасспорт клиента-отправителя
     * @param srcRequisite реквизиты счета-отправителя
     * @param destPassport пасспорт клиента-получателя
     * @param destRequisite реквизиты счета-получателя
     * @param amount сумма для перевода
     * @return возвращает true, если перевод успешный; false, если клиента получателя или отправителя не нашлось,
     * и если остаток на счете-отправителе меньше суммы перевода.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        Optional<Account> srcAccount = findByRequisite(srcPassport, srcRequisite);
        Optional<Account> destAccount = findByRequisite(destPassport, destRequisite);
        boolean rsl = srcAccount.isPresent()
                    && destAccount.isPresent()
                    && amount <= srcAccount.get().getBalance();
        if (rsl) {
            srcAccount.get().setBalance(srcAccount.get().getBalance() - amount);
            destAccount.get().setBalance(destAccount.get().getBalance() + amount);
        }
        return rsl;
    }
}