package org.example.main;

import java.time.LocalDate;
import java.util.function.Predicate;

import org.cloud.service.ServiceImpl;
import org.cloud.service.UserNotFoundException;
import org.example.dto.CreditBankCard;
import org.example.dto.Subscription;
import org.example.dto.User;

public class Main {
    private static final String CARD_NUMBER = "12345678";
    private static ServiceImpl service;

    static {
        service = new ServiceImpl();
        service.fillDb();

    }

    public static void main(String[] args) {
        var allUsers = service.getAllUsers();
        allUsers.forEach(System.out::println);
        var card = new CreditBankCard();
        card.setNumber(CARD_NUMBER);
        var creditUser = new User("John", "Doe", LocalDate.of(1988, 2, 3));
        card.setUser(creditUser);
        service.subscribe(card);

        Subscription res = service.getSubscriptionByBankCardNumber(CARD_NUMBER)
                .orElseThrow(() -> new UserNotFoundException("user not found with card = " + CARD_NUMBER));

        System.out.println("Subscription: " + res);

        System.out.println("Next week birthday has:" + service.getUsersWithBirthdayForNextWeek());

        System.out.println("Average  age:" + service.getAverageUsersAge());

        System.out.println("John older than 18: " + service.isPayableUser(creditUser));


        System.out.println("Conditions by predicate #1: " + service.getAllSubscriptionsByCondition(s -> s.getStartDate().isAfter(LocalDate.MIN)));

        Predicate<Subscription> predicate = s -> s.getBankcard().length() > 3;
        System.out.println("Conditions by predicate #2: " + service.getAllSubscriptionsByCondition(predicate));


        service.shutdown();

    }


}


