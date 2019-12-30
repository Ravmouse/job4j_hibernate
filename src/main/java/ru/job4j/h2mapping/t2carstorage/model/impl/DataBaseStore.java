package ru.job4j.h2mapping.t2carstorage.model.impl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.h1config.t2todolist.model.Wrapper;
import ru.job4j.h2mapping.t2carstorage.entity.Car;
import ru.job4j.h2mapping.t2carstorage.entity.Driver;
import ru.job4j.h2mapping.t2carstorage.entity.Engine;
import ru.job4j.h2mapping.t2carstorage.model.Store;

/**
 * @author Vitaly Vasilyev, date: 22.12.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class DataBaseStore implements Store {

    private final SessionFactory factory = new Configuration()
            .configure("ru/job4j/h2mapping/t2carstorage/carstorage.cfg.xml")
            .buildSessionFactory();

    @Override
    public Car addCar(String carTitle, String driverName, String engineTitle) {
        return new Wrapper(factory).perform(session -> {
            Engine engine = new Engine(engineTitle);
            Driver driver = new Driver(driverName);

            Car car = new Car(carTitle);
            car.setEngine(engine);
            car.addDriver(driver);

            session.save(car);
            return car;
        });
    }

    @Override
    public Car updateCar(int id, String carTitle) {
        return new Wrapper(factory).perform(session -> {
            Car car = session.get(Car.class, id);
            car.setName(carTitle);
            return car;
        });
    }

    @Override
    public Car deleteCar(int id) {
        return new Wrapper(factory).perform(session -> {
            Car car = session.get(Car.class, id);
            session.delete(car);
            return car;
        });
    }

    @Override
    public void close() throws Exception {
        if (factory != null) {
            factory.close();
        }
    }
}