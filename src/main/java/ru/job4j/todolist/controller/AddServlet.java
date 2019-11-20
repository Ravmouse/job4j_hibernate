package ru.job4j.todolist.controller;

import ru.job4j.todolist.model.impl.HibernateStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import static ru.job4j.utils.Utils.getRequestString;
import static ru.job4j.utils.Utils.jsonFromElement;

/**
 * @author Vitaly Vasilyev, date: 09.11.2019, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class AddServlet extends HttpServlet {
    /**
     * Доступ к БД.
     */
    private static final HibernateStore STORE = HibernateStore.getInstance();

    /**
     * @param req запрос.
     * @param resp ответ.
     * @throws ServletException искл.
     * @throws IOException искл.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String description = getRequestString(req);
        STORE.add(description);
        final String result = jsonFromElement(STORE.findTheLast());
        resp.setContentType("application/json");
        try (final PrintWriter w = resp.getWriter()) {
            w.write(result);
            w.flush();
        }
    }
}