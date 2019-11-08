package ru.job4j.services.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.entity.User;
import ru.job4j.services.Factory;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vitaly Vasilyev, date: 05.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class HibernateFactory implements Factory {
    /**
     * Фабрика.
     */
    private final SessionFactory factory;
    /**
     * Конструктор.
     */
    public HibernateFactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    /**
     * Сохранить юзера в БД.
     * @param name имя юзера.
     */
    @Override
    public void createUser(String name) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            final User user = new User();
            user.setName(name);

            session.save(user);
            session.getTransaction().commit();
        }
    }

    /**
     * Обновить данные юзера в БД.
     * @param id   номер юзера.
     * @param name имя юзера.
     */
    @Override
    public void updateUser(int id, String name) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            final User user = new User();
            user.setId(id);
            user.setName(name);
            user.setExpired(Calendar.getInstance());

            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }
    }

    /**
     * Удалить юзера из БД.
     * @param id номер юзера.
     */
    @Override
    public void deleteUser(int id) {
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            final User user = new User();
            user.setId(id);

            session.delete(user);
            session.getTransaction().commit();
        }
    }

    /**
     * Найти юзера по id.
     * @param id номер юзера.
     * @return найденного юзера.
     */
    @Override
    public User findUserById(int id) {
        User user;
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            List<User> users = session.createQuery("from User").list();
            user = users.stream().filter(u -> id == u.getId()).findFirst().orElse(null);

            session.getTransaction().commit();
        }
        return user;
    }

    /**
     * Найти юзера(ов) по имени.
     * @param name имя юзера.
     * @return список найденных юзеров.
     */
    @Override
    public List<User> findUserByName(String name) {
        List<User> users;
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            users = session.createQuery("from User").list();
            users = users.stream().filter(u -> name.equals(u.getName())).collect(Collectors.toList());

            session.getTransaction().commit();
        }
        return users;
    }

    /**
     * @return список всех юзеров.
     */
    @Override
    public List<User> findAll() {
        List<User> users;
        try (final Session session = factory.openSession()) {
            session.beginTransaction();

            users = session.createQuery("from User").list();

            session.getTransaction().commit();
        }
        return users;
    }

    /**
     * Закрывает фабрику.
     */
    @Override
    public void close() {
        if (factory != null) {
            factory.close();
        }
    }
}