package ru.job4j.entity;

import java.util.Calendar;

/**
 * @author Vitaly Vasilyev, date: 05.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class User {
    /**
     * Номер.
     */
    private int id;
    /**
     * Имя.
     */
    private String name;
    /**
     * Дата.
     */
    private Calendar expired = Calendar.getInstance();

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
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name имя.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return дата.
     */
    public Calendar getExpired() {
        return expired;
    }

    /**
     * @param expired дата.
     */
    public void setExpired(Calendar expired) {
        this.expired = expired;
    }

    /**
     * @return строковое представление.
     */
    @Override
    public String toString() {
        return String.format("User id=%d, name=%s, date=%s", id, name, expired.getTime());
    }
}