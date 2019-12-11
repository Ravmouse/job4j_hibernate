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
@Entity(name = "engines")
public class Engine {
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
        return String.format("Engine: id = %d, name = %s", id, name);
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
        if (!(obj instanceof Engine)) {
            return false;
        }
        Engine engine = (Engine) obj;
        return id == engine.id && name.equals(engine.name);
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
        return new Engine().new Builder();
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
            Engine.this.id = id;
            return this;
        }

        /**
         * @param name название.
         * @return экз. внутр. класса.
         */
        public Builder setName(String name) {
            Engine.this.name = name;
            return this;
        }

        /**
         * @return экз. внешнего класса.
         */
        public Engine build() {
            return Engine.this;
        }
    }
}