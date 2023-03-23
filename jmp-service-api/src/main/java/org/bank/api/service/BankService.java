package org.bank.api.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.example.dto.BankCard;
import org.example.dto.Subscription;
import org.example.dto.User;

public interface BankService {

    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber);

    List<User> getAllUsers();

    default Map<Integer, User> getUsersWithBirthdayForNextWeek() {
        var now = LocalDate.now();
        var nextWeek = now.plus(1, ChronoUnit.WEEKS);
        getAllUsers().stream()
                .filter(s -> s.getBirthday().withYear(now.getYear()).isAfter(now)
                        && s.getBirthday().withYear(now.getYear()).isBefore(nextWeek))
                .mapToLong(s -> s.getBirthday().getYear() - now.getYear())
                .sorted()
                .forEach(System.out::println);

        return getAllUsers().stream()
                .filter(s -> s.getBirthday().withYear(now.getYear()).isAfter(now)
                        && s.getBirthday().withYear(now.getYear()).isBefore(nextWeek))
                .collect(Collectors.toMap(s -> now.getYear() - s.getBirthday().getYear(), Function.identity()));
    }

    default double getAverageUsersAge() {
        var now = LocalDate.now();
        return getAllUsers().stream()
                .mapToLong(s -> ChronoUnit.YEARS.between(s.getBirthday(), now))
                .average()
                .orElse(0);
    }

    default boolean isPayableUser(User user) {
        var now = LocalDate.now();
        return getAllUsers().stream()
                .anyMatch(s -> s.equals(user) && ChronoUnit.YEARS.between(s.getBirthday(), now) > 18);
    }

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate);

}
