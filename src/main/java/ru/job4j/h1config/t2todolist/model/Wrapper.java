package ru.job4j.h1config.t2todolist.model;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.utils.Utils;
import java.util.function.Function;

/**
 * @author Vitaly Vasilyev, date: 24.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Wrapper {
    /**
     * Фабрика для сессий.
     */
    private final SessionFactory factory;
    /**
     * Логгер.
     */
    private static final Logger LOGGER = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * @param factory фабрика для сессий.
     */
    public Wrapper(SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * @param command функция для выполнения.
     * @param <T> обобщенный параметр.
     * @return результат выполнения функции.
     */
    public <T> T perform(final Function<Session, T> command) {
        T rsl = null;
        final Session session = factory.openSession();
        final Transaction tr = session.beginTransaction();
        try {
            rsl = command.apply(session);
            tr.commit();
        } catch (final Exception e) {
            LOGGER.warn("Can't perform the session operation: " + e);
            e.printStackTrace();
            tr.rollback();
        } finally {
            session.close();
        }
        return rsl;
    }
}