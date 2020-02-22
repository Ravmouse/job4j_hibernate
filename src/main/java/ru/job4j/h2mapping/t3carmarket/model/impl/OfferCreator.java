package ru.job4j.h2mapping.t3carmarket.model.impl;

import org.hibernate.Session;
import ru.job4j.h2mapping.t3carmarket.entity.Offer;

/**
 * @author Vitaly Vasilyev, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class OfferCreator {
    /**
     * Сессия.
     */
    private final Session session;

    /**
     * @param session сессия.
     */
    public OfferCreator(Session session) {
        this.session = session;
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
    public Offer createOffer(int brand, int model, String year, int body, int transmission, int engine, String image) {
        final Offer offer = new Offer();
        offer.setImgName(image);
        offer.setSold(false);
        offer.setCar(new CarCreator(session).createCar(brand, model, year, body, transmission, engine));
        session.save(offer);
        return offer;
    }
}