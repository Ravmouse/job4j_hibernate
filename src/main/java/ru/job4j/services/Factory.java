package ru.job4j.services;

import ru.job4j.entity.User;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 05.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public interface Factory extends AutoCloseable {
    /**
     * @param name имя юзера.
     */
    void createUser(String name);
    /**
     * @param id номер юзера.
     * @param name имя юзера.
     */
    void updateUser(int id, String name);
    /**
     * @param id номер юзера.
     */
    void deleteUser(int id);
    /**
     * @param id номер юзера.
     * @return найденного юзера.
     */
    User findUserById(int id);
    /**
     * @param name имя юзера.
     * @return список найденных юзеров.
     */
    List<User> findUserByName(String name);
    /**
     * @return список всех юзеров.
     */
    List<User> findAll();
}