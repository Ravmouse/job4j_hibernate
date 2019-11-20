package ru.job4j.todolist.model;

import org.hibernate.SessionFactory;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 18.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public interface Enricher<T> {
    /**
     * @param factory фабрика для сессий.
     * @return список элементов.
     */
    List<T> enrich(SessionFactory factory);
}