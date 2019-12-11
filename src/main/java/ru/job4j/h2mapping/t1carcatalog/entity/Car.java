package ru.job4j.h2mapping.t1carcatalog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Vitaly Vasilyev, date: 26.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
@Entity(name = "cars")
public class Car {
    /**
     * Номер.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Название.
     */
    @Column(name = "name")
    private String name;
    /**
     * Кузов.
     */
    @ManyToOne
    @JoinColumn(name = "carbody_id")
    private CarBody carBody;
    /**
     * Двигатель.
     */
    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;
    /**
     * Трансмиссия.
     */
    @ManyToOne
    @JoinColumn(name = "trans_id")
    private Transmission transmission;

    /**
     * @return номер.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id номер.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return название.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name название.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return кузов.
     */
    public CarBody getCarBody() {
        return carBody;
    }

    /**
     * @param carBody кузов.
     */
    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    /**
     * @return двигатель.
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * @param engine двигатель.
     */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    /**
     * @return кор.передач.
     */
    public Transmission getTransmission() {
        return transmission;
    }

    /**
     * @param transmission кор.передач.
     */
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    /**
     * @return строковое представление.
     */
    @Override
    public String toString() {
        return String.format("Car: id = %d, name = %s, carBody = %s, engine = %s, transmission = %s", id, name, carBody,
                engine, transmission);
    }

    /**
     * @param obj объект для сравнения.
     * @return true or false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Car)) {
            return false;
        }
        Car car = (Car) obj;
        return id == car.id
                && name.equals(car.name)
                && carBody.equals(car.carBody)
                && engine.equals(car.engine)
                && transmission.equals(car.transmission);
    }

    /**
     * @return хэш-код.
     */
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + carBody.hashCode();
        result = 31 * result + engine.hashCode();
        result = 31 * result + transmission.hashCode();
        return  result;
    }
}