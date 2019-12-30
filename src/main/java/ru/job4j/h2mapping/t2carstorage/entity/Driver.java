package ru.job4j.h2mapping.t2carstorage.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Vitaly Vasilyev, date: 22.12.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
@Entity(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,
                cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
                mappedBy = "drivers")
    private Set<Car> cars = new HashSet<>();

    public Driver() {
    }

    public Driver(String name) {
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

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car) {
        Optional.ofNullable(car).ifPresent(c -> cars.add(c));
    }

    public void removeCar(Car car) {
        Optional.ofNullable(car).ifPresent(c -> cars.remove(c));
    }

    @Override
    public String toString() {
        return String.format("Driver: id = %d, name = %s", id, name);
    }

    public static Builder newBuilder() {
        return new Driver().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder setId(int id) {
            Driver.this.id = id;
            return this;
        }

        public Builder setName(String name) {
            Driver.this.name = name;
            return this;
        }

        public Builder setCars(Set<Car> cars) {
            Driver.this.cars = cars;
            return this;
        }

        public Driver build() {
            return Driver.this;
        }
    }
}