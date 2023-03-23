package org.example.dto;

@FunctionalInterface
public interface ICreditCard {
    CreditBankCard getBankCard(User user);

}
