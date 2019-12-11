package ru.job4j.h2mapping.t1carcatalog.model;

/**
 * @author Vitaly Vasilyev, date: 30.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public interface Store<T> extends AutoCloseable {
    /**
     * @param name название.
     * @param exstBody кузов.
     * @param exstEng двигатель.
     * @param exstTrans транс.
     * @return объект, который был добавлен.
     */
    T add(String name, int exstBody, int exstEng, int exstTrans);
    /**
     * @param id номер.
     * @param newName новое имя.
     * @param newBody номер нового кузова.
     * @param newEng номер нового двигателя.
     * @param newTrans номер новой кор.передач.
     * @return объект, который был обновлен.
     */
    T update(int id, String newName, int newBody, int newEng, int newTrans);
    /**
     * @param id номер.
     */
    void delete(int id);
}