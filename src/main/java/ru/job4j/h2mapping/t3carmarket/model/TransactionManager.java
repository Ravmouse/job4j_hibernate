package ru.job4j.h2mapping.t3carmarket.model;

import ru.job4j.h2mapping.t3carmarket.entity.User;
import java.util.List;

/**
 * @author Vitaly Vasilyev, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public interface TransactionManager<T> extends AutoCloseable {
    T addOffer(int brand, int model, String year, int body, int transmission, int engine, String image);
    List<T> selectAllOffers();
    User getUser(String name, String password);
}