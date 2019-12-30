package ru.job4j.h2mapping.t2carstorage.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Vitaly Vasilyev, date: 22.12.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
@Entity(name = "engine")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "engine",
               cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH},
               fetch = FetchType.EAGER)
    private List<Car> cars = new ArrayList<>();

    public Engine() {
    }

    public Engine(String name) {
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

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
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
        return String.format("Engine: id = %d, name = %s", id, name);
    }

    public static Builder newBuilder() {
        return new Engine().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder setId(int id) {
            Engine.this.id = id;
            return this;
        }

        public Builder setName(String name) {
            Engine.this.name = name;
            return this;
        }

        public Engine build() {
            return Engine.this;
        }
    }
}