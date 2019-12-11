package ru.job4j.h2mapping.t1carcatalog.model.impl;

import org.junit.Test;
import ru.job4j.h2mapping.t1carcatalog.entity.Car;
import ru.job4j.h2mapping.t1carcatalog.entity.CarBody;
import ru.job4j.h2mapping.t1carcatalog.entity.Engine;
import ru.job4j.h2mapping.t1carcatalog.entity.Transmission;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Класс тестов.
 */
public class DbStoreTest {
    /**
     * Тест на создание объекта.
     */
    @Test
    public void addTest() {
        DbStore store = new DbStore();

        Car car = new Car();
        car.setId(24);
        car.setName("Ferrari");

        car.setCarBody(CarBody.newBuilder().setId(4).setName("Купе").build());
        car.setEngine(Engine.newBuilder().setId(1).setName("Бензиновый").build());
        car.setTransmission(Transmission.newBuilder().setId(1).setName("Механическая коробка (МКПП)").build());

        assertThat(store.add("Ferrari", 4, 1, 1), is(car));
    }

    /**
     * Тест на обновление объекта.
     */
    @Test
    public void updateTest() {
        DbStore store = new DbStore();

        Car car = new Car();
        car.setId(24);
        car.setName("Jeep");

        car.setCarBody(CarBody.newBuilder().setId(6).setName("Внедорожник").build());
        car.setEngine(Engine.newBuilder().setId(2).setName("Дизельный").build());
        car.setTransmission(Transmission.newBuilder().setId(2).setName("Автоматическая коробка (АКПП)").build());

        assertThat(store.update(24, "Jeep", 6, 2, 2), is(car));
    }
}