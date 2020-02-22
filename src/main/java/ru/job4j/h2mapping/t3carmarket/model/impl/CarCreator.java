package ru.job4j.h2mapping.t3carmarket.model.impl;

import org.hibernate.Session;
import ru.job4j.h2mapping.t3carmarket.entity.Brand;
import ru.job4j.h2mapping.t3carmarket.entity.Car;
import ru.job4j.h2mapping.t3carmarket.entity.CarBody;
import ru.job4j.h2mapping.t3carmarket.entity.Engine;
import ru.job4j.h2mapping.t3carmarket.entity.Model;
import ru.job4j.h2mapping.t3carmarket.entity.Transmission;
import java.time.LocalDate;

/**
 * @author Vitaly Vasilyev, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class CarCreator {
    /**
     * Сессия.
     */
    private final Session session;

    /**
     * @param session сессия.
     */
    public CarCreator(Session session) {
        this.session = session;
    }

    /**
     * @param brand марка.
     * @param model модель.
     * @param year год.
     * @param body кузов.
     * @param transmission коробка передач.
     * @param engine двигатель.
     * @return машину.
     */
    public Car createCar(int brand, int model, String year, int body, int transmission, int engine) {
        final Car car = new Car();
        car.setYear(LocalDate.parse(year));
        car.setBrand(session.get(Brand.class, brand));
        car.setModel(session.get(Model.class, model));
        car.setTransmission(session.get(Transmission.class, transmission));
        car.setCarBody(session.get(CarBody.class, body));
        car.setEngine(session.get(Engine.class, engine));
        return car;
    }
}