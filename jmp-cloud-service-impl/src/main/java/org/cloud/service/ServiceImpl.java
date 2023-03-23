package org.cloud.service;

import static java.util.Collections.EMPTY_LIST;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bank.api.service.BankService;
import org.example.dto.BankCard;
import org.example.dto.Subscription;
import org.example.dto.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ServiceImpl implements BankService {
    @Override
    public void subscribe(BankCard bankCard) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(bankCard);
            Subscription subscription = new Subscription();
            subscription.setBankcard(bankCard.getNumber());
            subscription.setStartDate(LocalDate.now());

            session.save(subscription);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        }
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Subscription.class, cardNumber));
//                    .orElseThrow(() -> new UserNotFoundException("User not found for card" + cardNumber));
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserNotFoundException("User not found for card" + cardNumber);
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class)
                    .list()
                    .stream()
//                    .toList();
                    .collect(Collectors.toUnmodifiableList());
        } catch (Exception e) {
            e.printStackTrace();
            return EMPTY_LIST;
        }
    }

    public static void fillDb() {
        User user1 = new User("Ramesh", "Fadatare", LocalDate.of(2022, 12, 12));
        User user2 = new User("John", "Cena", LocalDate.of(2020, 10, 7));
        User user3 = new User("Lenny", "Cravitz", LocalDate.of(1999, 3, 25));
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user1);
            session.save(user2);
            session.save(user3);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Subscription", Subscription.class)
                    .list()
                    .stream()
                    .filter(predicate)
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return EMPTY_LIST;
        }

    }

    public void shutdown() {
        HibernateUtil.shutdown();

    }
}
