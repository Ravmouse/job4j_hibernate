package ru.job4j.h2mapping.t2carstorage.model.impl;

import org.junit.Test;
import ru.job4j.h2mapping.t2carstorage.entity.Car;
import ru.job4j.h2mapping.t2carstorage.entity.Driver;
import ru.job4j.h2mapping.t2carstorage.entity.Engine;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.junit.Assert.assertThat;

/**
 * @author Vitaly Vasilyev, date: 23.12.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class DataBaseStoreTest {
    @Test
    public void addCarTest() {
        DataBaseStore db = new DataBaseStore();

        Engine engine = Engine.newBuilder().setId(36).setName("Gasoline").build();
        Driver driver = Driver.newBuilder().setId(36).setName("Tom").build();

        Car car = new Car();
        car.setId(36);
        car.setName("Ford");
        car.setEngine(engine);
        car.addDriver(driver);

        assertThat(db.addCar("Ford", "Tom", "Gasoline").toString(), equalToIgnoringWhiteSpace(car.toString()));
    }

    @Test
    public void updateCarTest() {
        DataBaseStore db = new DataBaseStore();

        Engine engine = Engine.newBuilder().setId(36).setName("Gasoline").build();
        Driver driver = Driver.newBuilder().setId(36).setName("Tom").build();

        Car car = new Car();
        car.setId(36);
        car.setName("Ferrari");
        car.setEngine(engine);
        car.addDriver(driver);

        assertThat(db.updateCar(36, "Ferrari").toString(), equalToIgnoringWhiteSpace(car.toString()));
    }
}