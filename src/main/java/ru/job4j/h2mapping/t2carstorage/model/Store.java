package ru.job4j.h2mapping.t2carstorage.model;

import ru.job4j.h2mapping.t2carstorage.entity.Car;

/**
 * @author Vitaly Vasilyev, date: 20.12.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public interface Store extends AutoCloseable {
    Car addCar(String carTitle, String driverName, String engineTitle);

    Car updateCar(int id, String carTitle);

    Car deleteCar(int id);
}