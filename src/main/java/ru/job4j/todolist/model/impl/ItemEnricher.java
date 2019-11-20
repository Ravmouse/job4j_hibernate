package ru.job4j.todolist.model.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.todolist.entity.Item;
import ru.job4j.todolist.model.Enricher;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 18.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class ItemEnricher implements Enricher<Item> {
    /**
     * @param factory фабрика для сессий.
     * @return список элементов типа Item.
     */
    @Override
    public List<Item> enrich(final SessionFactory factory) {
        List<Item> items;
        try (final Session session = factory.openSession()) {
            session.beginTransaction();
            items = session.createQuery("from Item i ORDER BY i.id").list();
            session.getTransaction().commit();
        }
        return items;
    }
}