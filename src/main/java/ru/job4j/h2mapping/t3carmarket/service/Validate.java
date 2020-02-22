package ru.job4j.h2mapping.t3carmarket.service;

import ru.job4j.h2mapping.t3carmarket.entity.Offer;
import ru.job4j.h2mapping.t3carmarket.entity.User;
import ru.job4j.h2mapping.t3carmarket.model.TransactionManager;
import ru.job4j.h2mapping.t3carmarket.model.impl.TxManager;

/**
 * @author Vitaly Vasilyev, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Validate {
    /**
     * Экз. данного класса.
     */
    private static final Validate INSTANCE = new Validate();
    /**
     * Менеджер транзакций.
     */
    private static final TransactionManager<Offer> MANAGER = TxManager.getInstance();

    /**
     * Приватный конструктор.
     */
    private Validate() { }

    /**
     * @return экз. класса.
     */
    public static Validate getInstance() {
        return INSTANCE;
    }

    /**
     * @param name имя пользователя для проверки в БД.
     * @param password пароль пользователя для проверки в БД.
     * @return true или false.
     */
    public boolean userExist(String name, String password) {
        final User user = MANAGER.getUser(name, password);
        return user != null;
    }
}