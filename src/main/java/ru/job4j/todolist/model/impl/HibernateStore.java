package ru.job4j.todolist.model.impl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.todolist.entity.Item;
import ru.job4j.todolist.model.Store;
import ru.job4j.todolist.model.Wrapper;
import java.util.List;

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
        final Item item = new Item();
        item.setDescription(description);
        new Wrapper(factory).perform(session -> session.save(item));
    }

    /**
     * @return лист всех задач.
     */
    @Override
    public List<Item> findAll() {
        return new Wrapper(factory).perform(session -> session.createQuery("from Item i ORDER BY i.id").list());
    }

    /**
     * @return лист только невыполненных задач.
     */
    @Override
    public List<Item> findNotPerformed() {
        return new Wrapper(factory).perform(session -> session.createQuery("from Item i WHERE done != true ORDER BY i.id").list());
    }

    /**
     * @return самую последнюю задачу.
     */
    @Override
    public Item findTheLast() {
        return new Wrapper(factory).perform(session -> {
            final Query query = session.createQuery("from Item i ORDER BY i.id DESC");
            query.setMaxResults(1);
            return (Item) query.list().get(0);
        });
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