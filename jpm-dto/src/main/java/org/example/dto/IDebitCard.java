package org.example.dto;

@FunctionalInterface
public interface IDebitCard {
    DebitBankCard getBankCard(User user);

}
