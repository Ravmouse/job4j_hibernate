package ru.job4j.todolist.model.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.todolist.entity.Item;
import ru.job4j.todolist.model.Store;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vitaly Vasilyev, date: 09.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class HibernateStore implements Store<Item> {
    /**
     * Ссылка на экз. класса.
     */
    private static final HibernateStore INSTANCE = new HibernateStore();
    /**
     * Фабрика для сессий.
     */
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    /**
     * Закрытый конструктор.
     */
    private HibernateStore() {

    }

    /**
     * @return ссылку на экз. данного класса.
     */
    public static HibernateStore getInstance() {
        return INSTANCE;
    }

    /**
     * @param description описание задачи.
     */
    @Override
    public void add(final String description) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            final Item item = new Item();
            item.setDescription(description);

            session.save(item);
            session.getTransaction().commit();
        }
    }

    /**
     * @return лист всех задач.
     */
    @Override
    public List<Item> findAll() {
        return new ItemEnricher().enrich(factory);
    }

    /**
     * @return лист только невыполненных задач.
     */
    @Override
    public List<Item> findNotPerformed() {
        final List<Item> items = new ItemEnricher().enrich(factory);
        return items.stream().filter(e -> !e.isDone()).collect(Collectors.toList());
    }

    /**
     * @return самую последнюю задачу.
     */
    @Override
    public Item findTheLast() {
        final List<Item> items = new ItemEnricher().enrich(factory);
        return items.stream().max(Comparator.comparing(Item::getId)).orElse(null);
    }

    /**
     * @throws Exception искл.
     */
    @Override
    public void close() throws Exception {
        if (factory != null) {
            factory.close();
        }
    }
}