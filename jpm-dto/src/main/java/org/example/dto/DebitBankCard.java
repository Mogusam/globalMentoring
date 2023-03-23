package org.example.dto;

import javax.persistence.Entity;

@Entity(name = "debit_bank_card")
public class DebitBankCard extends BankCard {

    public DebitBankCard(User user) {
        super(user);
    }

    public DebitBankCard() {
    }
}
