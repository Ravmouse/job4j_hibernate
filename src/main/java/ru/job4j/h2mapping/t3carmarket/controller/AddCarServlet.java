package ru.job4j.h2mapping.t3carmarket.controller;

import ru.job4j.h2mapping.t3carmarket.entity.Offer;
import ru.job4j.h2mapping.t3carmarket.model.TransactionManager;
import ru.job4j.h2mapping.t3carmarket.model.impl.TxManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static ru.job4j.utils.Utils.getRequestString;
import static ru.job4j.utils.Utils.parse;

/**
 * @author Vitaly Vasilyev, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class AddCarServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String str = getRequestString(req);
        MANAGER.addOffer(
                Integer.parseInt(parse("brand", str)),
                Integer.parseInt(parse("model", str)),
                parse("year", str),
                Integer.parseInt(parse("carBody", str)),
                Integer.parseInt(parse("transmission", str)),
                Integer.parseInt(parse("engine", str)),
                parse("imgName", str)
                );
    }
}