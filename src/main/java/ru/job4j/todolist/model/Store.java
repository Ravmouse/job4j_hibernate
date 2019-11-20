package ru.job4j.todolist.model;

import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 09.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public interface Store<T> extends AutoCloseable {
    /**
     * @param description описание задачи.
     */
    void add(String description);

    /**
     * @return лист всех задач.
     */
    List<T> findAll();

    /**
     * @return лист только невыполненных задач.
     */
    List<T> findNotPerformed();

    /**
     * @return самую последнюю задачу.
     */
    T findTheLast();
}