package ru.job4j.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import ru.job4j.todolist.entity.Item;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Vitaly Vasilyev, date: 05.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class Utils {
    /**
     * Логгер.
     */
    private static final Logger LOG = Logger.getLogger(Utils.getNameOfTheClass());

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

    /**
     * @param req запрос.
     * @return строка из запроса.
     */
    public static String getRequestString(final HttpServletRequest req) {
        String result = null;
        try (final BufferedReader reader = req.getReader()) {
            result = reader.readLine();
        } catch (IOException io) {
            LOG.warn(io);
        }
        return result;
    }

    /**
     * @param items лист задач.
     * @return строка в виде json-объекта.
     * @throws IOException искл.
     */
    public static String jsonFromList(List<Item> items) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return mapper.writeValueAsString(items);
    }

    /**
     * @param item одна задача.
     * @return строка в виде json-объекта.
     * @throws IOException искл.
     */
    public static String jsonFromElement(Item item) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return mapper.writeValueAsString(item);
    }
}