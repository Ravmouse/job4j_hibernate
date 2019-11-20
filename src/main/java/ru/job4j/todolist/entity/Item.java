package ru.job4j.todolist.entity;

import java.util.Calendar;

/**
 * @author Vitaly Vasilyev, date: 09.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Item {
    /**
     * Номер.
     */
    private int id;
    /**
     * Описание.
     */
    private String description;
    /**
     * Дата создания.
     */
    private Calendar created = Calendar.getInstance();
    /**
     * Статус.
     */
    private boolean done;

    /**
     * @return номер.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id число для номера.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return описание.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description описание.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return календарь.
     */
    public Calendar getCreated() {
        return created;
    }

    /**
     * @param created календарь.
     */
    public void setCreated(Calendar created) {
        this.created = created;
    }

    /**
     * @return статус.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * @param done бул.переменная.
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * @return строковое представление.
     */
    @Override
    public String toString() {
        return String.format("%d, %s, %s, %b", id, description, created.getTime(), done);
    }
}