package ru.job4j.h2mapping.t1carcatalog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Vitaly Vasilyev, date: 26.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
@Entity(name = "carbodies")
public class CarBody {
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
     * @return строковое представление.
     */
    @Override
    public String toString() {
        return String.format("CarBody: id = %d, name = %s", id, name);
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
        if (!(obj instanceof CarBody)) {
            return false;
        }
        CarBody carBody = (CarBody) obj;
        return id == carBody.id && name.equals(carBody.name);
    }

    /**
     * @return хэш-код.
     */
    @Override
    public int hashCode() {
        int result = id;
        result = result * 31 + name.hashCode();
        return result;
    }

    /**
     * @return экземпляр внутреннего класса Builder.
     */
    public static Builder newBuilder() {
        return new CarBody().new Builder();
    }

    /**
     * Внутр.класс.
     */
    public class Builder {
        /**
         * Приват.конструктор.
         */
        private Builder() {

        }

        /**
         * @param id номер.
         * @return экз. внутр. класса.
         */
        public Builder setId(int id) {
            CarBody.this.id = id;
            return this;
        }

        /**
         * @param name название.
         * @return экз. внутр. класса.
         */
        public Builder setName(String name) {
            CarBody.this.name = name;
            return this;
        }

        /**
         * @return экз. внешнего класса.
         */
        public CarBody build() {
            return CarBody.this;
        }
    }
}