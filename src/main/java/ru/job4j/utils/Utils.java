package ru.job4j.utils;
/**
 * @author Vitaly Vasilyev, date: 05.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Utils {
    /**
     * Выкидывается исключение и, ввиду того, что этот метод должен запускаться из другого класса, получается имя
     * класса, из которого был вызван данный метод.
     * @return имя класса в виде строки, из которого был выполнен этот метод.
     */
    public static String getNameOfTheClass() {
        try {
            throw new RuntimeException();
        } catch (RuntimeException re) {
            return re.getStackTrace()[1].getClassName();
        }
    }
}