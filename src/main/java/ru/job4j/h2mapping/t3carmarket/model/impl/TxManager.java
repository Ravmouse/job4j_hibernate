package ru.job4j.h2mapping.t3carmarket.model.impl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.h1config.t2todolist.model.Wrapper;
import ru.job4j.h2mapping.t3carmarket.entity.Offer;
import ru.job4j.h2mapping.t3carmarket.entity.User;
import ru.job4j.h2mapping.t3carmarket.model.TransactionManager;
import ru.job4j.utils.Utils;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Vitaly Vasilyev, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class TxManager implements TransactionManager<Offer> {
    /**
     * Экз. данного класса.
     */
    private static final TxManager INSTANCE = new TxManager();
    /**
     * Фабрика сессий.
     */
    private final SessionFactory factory = new Configuration()
            .configure("ru/job4j/h2mapping/t3carmarket/carmarket.cfg.xml")
            .buildSessionFactory();
    /**
     * Логгер.
     */
    private static final Logger LOGGER = Logger.getLogger(Utils.getNameOfTheClass());

    /**
     * Приватный конструктор.
     */
    private TxManager() { }

    /**
     * @return экз. класса.
     */
    public static TxManager getInstance() {
        return INSTANCE;
    }

    /**
     * @param brand марка.
     * @param model модель.
     * @param year год.
     * @param body кузов.
     * @param transmission коробка передач.
     * @param engine двигатель.
     * @param image изображение.
     * @return объявление.
     */
    @Override
    public Offer addOffer(int brand, int model, String year, int body, int transmission, int engine, String image) {
        return new Wrapper(factory).perform(session -> new OfferCreator(session)
                .createOffer(brand, model, year, body, transmission, engine, image));
    }

    /**
     * @return список всех объявлений.
     */
    @Override
    public List<Offer> selectAllOffers() {
        return new Wrapper(factory).perform(session -> session.createQuery("from Offer o order by o.id").list());
    }

    /**
     * @param name имя пользователя для проверки в БД.
     * @param password пароль пользователя для проверки в БД.
     * @return экземпляр класса User.
     */
    @Override
    public User getUser(String name, String password) {
        return new Wrapper(factory).perform(session -> {
            User user = null;
            try {
                user = session.createQuery("from UserOffer u WHERE u.name = :name AND u.password = :password", User.class)
                        .setParameter("name", name)
                        .setParameter("password", password)
                        .getSingleResult();
            } catch (NoResultException e) {
                LOGGER.warn(e);
            }
            return user;
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