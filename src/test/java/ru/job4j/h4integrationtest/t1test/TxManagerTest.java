package ru.job4j.h4integrationtest.t1test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import ru.job4j.h1config.t2todolist.model.Wrapper;
import ru.job4j.h2mapping.t3carmarket.entity.Brand;
import ru.job4j.h2mapping.t3carmarket.entity.Offer;
import ru.job4j.h2mapping.t3carmarket.entity.User;
import ru.job4j.h2mapping.t3carmarket.model.impl.TxManager;
import java.time.LocalDate;
import java.util.Arrays;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Vitaly Vasilyev, date: 26.02.2020, e-mail: rav.energ@rambler.ru
 * @version 1.0
 */
public class TxManagerTest {
    /**
     * Менеджер транзакций.
     */
    private final TxManager manager = TxManager.getInstance();

    /**
     * Добавляется объявление, которое становится персист.
     * У этого объявления берется id, по которому ищется объявление.
     */
    @Test
    public void addOfferTest() {
        final Offer offer = manager.addOffer(1, 1, "2010-02-03", 1, 1, 1, "test.jpg");
        assertThat(offer, is(manager.findById(offer.getId())));
    }

    /**
     * Добавляются три объявления.
     * Сравнивается, что список из этих трех объявлений равен списку, получаемому из БД.
     */
    @Test
    public void selectOffersTest() {
        final Offer offerOne = manager.addOffer(1, 1, "2010-02-03", 1, 1, 1, "test.jpg");
        final Offer offerTwo = manager.addOffer(2, 2, "2015-05-11", 2, 2, 2, "test2.jpg");
        final Offer offerThree = manager.addOffer(3, 3, "2019-11-05", 3, 3, 3, "test3.jpg");
        assertEquals(Arrays.asList(offerOne, offerTwo, offerThree), manager.selectAllOffers());
    }

    /**
     * Юзер делается персист.
     * Сравнивается, что сделанный юзер равен юзеру, полученному из БД.
     */
    @Test
    public void getUserTest() {
        final User user = new User();
        user.setName("name");
        user.setPassword("password");
        final SessionFactory factory = HibernateFactory.getFactory();
        new Wrapper(factory).perform(session -> session.save(user));
        assertThat(user, is(manager.getUser("name", "password")));
    }

    /**
     * Создаются три объявления. В них проставляются даты.
     * Они делаются персист.
     * Сравнивается, что список из двух объявлений, равен полученному списку за сегодняшний день.
     */
    @Test
    public void selectOffersByDayTest() {
        final Offer offerOne = new Offer();
        offerOne.setCreateDate(LocalDate.parse("2020-02-26"));

        final Offer offerTwo = new Offer();
        offerTwo.setCreateDate(LocalDate.parse("2020-02-25"));

        final Offer offerThree = new Offer();
        offerThree.setCreateDate(LocalDate.parse("2020-02-26"));

        final SessionFactory factory = HibernateFactory.getFactory();
        new Wrapper(factory).perform(session -> {
            session.save(offerOne);
            session.save(offerTwo);
            session.save(offerThree);
            return null;
        });
        assertThat(Arrays.asList(offerOne, offerThree), is(manager.selectOffersByDay()));
        assertThat(offerTwo, not(manager.selectOffersByDay()));
    }

    /**
     * Создается марка машины. Делается персист.
     * Добавляется объявление в БД. Теперь у персист.объявления есть персист.марка.
     * Из БД получается объявление по марке.
     */
    @Test
    public void selectOffersByCarTest() {
        final SessionFactory factory = HibernateFactory.getFactory();
        new Wrapper(factory).perform(session -> {
            Brand brand = new Brand();
            brand.setName("test");
            session.save(brand);
            return null;
        });
        Offer offer = manager.addOffer(1, 1, "2010-02-03", 1, 1, 1, "test.jpg");
        assertThat(offer, is(manager.selectOffersByCar(manager.selectAllOffers().get(0).getCar().getId()).get(0)));
    }
}