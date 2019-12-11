package ru.job4j.h2mapping.t1carcatalog.model.impl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.h2mapping.t1carcatalog.entity.Car;
import ru.job4j.h2mapping.t1carcatalog.entity.CarBody;
import ru.job4j.h2mapping.t1carcatalog.entity.Engine;
import ru.job4j.h2mapping.t1carcatalog.entity.Transmission;
import ru.job4j.h2mapping.t1carcatalog.model.Store;
import ru.job4j.h1config.t2todolist.model.Wrapper;

/**
 * @author Vitaly Vasilyev, date: 30.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class DbStore implements Store<Car> {
    /**
     * Фабрика сессий.
     */
    private final SessionFactory factory = new Configuration().configure("ru/job4j/h2mapping/t1carcatalog/carcatalog.cfg.xml")
            .buildSessionFactory();

    /**
     * @param name      название.
     * @param exstBody  кузов.
     * @param exstEng   двигатель.
     * @param exstTrans транс.
     * @return объект, который был добавлен.
     */
    @Override
    public Car add(String name, int exstBody, int exstEng, int exstTrans) {
        return new Wrapper(factory).perform(session -> {
            Car car = new Car();
            car.setName(name);
            car.setCarBody(session.get(CarBody.class, exstBody));
            car.setEngine(session.get(Engine.class, exstEng));
            car.setTransmission(session.get(Transmission.class, exstTrans));

            session.save(car);
            return car;
        });
    }

    /**
     * @param id       номер.
     * @param newName  новое имя.
     * @param newBody  номер нового кузова.
     * @param newEng   номер нового двигателя.
     * @param newTrans номер новой кор.передач.
     * @return объект, который был обновлен.
     */
    @Override
    public Car update(int id, String newName, int newBody, int newEng, int newTrans) {
        return new Wrapper(factory).perform(session -> {
            Car car = session.get(Car.class, id);
            car.setName(newName);
            car.setCarBody(session.get(CarBody.class, newBody));
            car.setEngine(session.get(Engine.class, newEng));
            car.setTransmission(session.get(Transmission.class, newTrans));

            session.update(car);
            return car;
        });
    }

    /**
     * @param id номер.
     */
    @Override
    public void delete(int id) {
        new Wrapper(factory).perform(session -> {
            Car car = session.get(Car.class, id);
            session.delete(car);
            return car;
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