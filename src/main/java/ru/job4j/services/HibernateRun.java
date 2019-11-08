package ru.job4j.services;

import org.apache.log4j.Logger;
import ru.job4j.services.impl.HibernateFactory;
import ru.job4j.utils.Utils;

/**
 * @author Vitaly Vasilyev, date: 05.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class HibernateRun {
    /**
     * Логгер.
     */
    private static final Logger LOG = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * @param args аргс.
     */
    public static void main(String[] args) {
        try (final Factory factory = new HibernateFactory()) {
            factory.createUser("Tom");
            LOG.info(factory.findUserById(17));
            factory.updateUser(17, "Jerry");
            LOG.info(factory.findUserByName("Ken"));
            factory.deleteUser(19);
            LOG.info(factory.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}