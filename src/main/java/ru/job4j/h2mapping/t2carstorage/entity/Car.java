package ru.job4j.h2mapping.t2carstorage.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Vitaly Vasilyev, date: 22.12.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
@Entity(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "car_driver", joinColumns = @JoinColumn(name = "car_id"),
               inverseJoinColumns = @JoinColumn(name = "driver_id"))
    private Set<Driver> drivers = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    public Car() {
    }

    public Car(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
        engine.addCar(this);
    }

    public void addDriver(Driver driver) {
        Optional.ofNullable(driver).ifPresent(d -> {
            drivers.add(d);
            d.addCar(this);
        });
    }

    @Override
    public String toString() {
        return String.format("Car: id = %d, name = %s, engine = %s, drivers = %s", id, name, engine, drivers);
    }
}