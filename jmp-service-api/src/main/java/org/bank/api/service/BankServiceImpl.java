package org.bank.api.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.example.dto.BankCard;
import org.example.dto.Subscription;
import org.example.dto.User;

public class BankServiceImpl implements BankService {
    @Override
    public void subscribe(BankCard bankCard) {

    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
        return null;
    }
}
