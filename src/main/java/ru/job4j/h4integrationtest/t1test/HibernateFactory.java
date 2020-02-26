package ru.job4j.h4integrationtest.t1test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Vitaly Vasilyev, date: 26.02.2020, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class HibernateFactory {
    /**
     * Фабрика сессий.
     */
    private static final SessionFactory FACTORY =
            new Configuration()
                    .configure("ru/job4j/h4integrationtest/t1test/carmarket_test.cfg.xml")
                    .buildSessionFactory();

    /**
     * @return ссылку на созданную фабрику сессий.
     */
    public static SessionFactory getFactory() {
        return FACTORY;
    }
}