package ru.job4j.h2mapping.t3carmarket.controller;

import org.apache.log4j.Logger;
import ru.job4j.h2mapping.t3carmarket.entity.Offer;
import ru.job4j.h2mapping.t3carmarket.model.TransactionManager;
import ru.job4j.h2mapping.t3carmarket.model.impl.TxManager;
import ru.job4j.utils.Utils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import static ru.job4j.utils.Utils.jsonFromList;

/**
 * @author Vitaly Vasilyev, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class DayServlet extends HttpServlet {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = Logger.getLogger(Utils.getNameOfTheClass());
    /**
     * Менеджер транзакций.
     */
    private static final TransactionManager<Offer> MANAGER = TxManager.getInstance();

    /**
     * @param req запрос.
     * @param resp ответ.
     * @throws ServletException искл.
     * @throws IOException искл.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        try (final PrintWriter w = resp.getWriter()) {
            final String results = jsonFromList(MANAGER.selectOffersByDay());
            resp.setContentType("application/json");
            w.write(results);
            w.flush();
        } catch (Exception e) {
            LOGGER.warn(e);
        }
    }
}