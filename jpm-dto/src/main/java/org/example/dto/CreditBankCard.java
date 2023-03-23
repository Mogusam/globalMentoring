package org.example.dto;

import javax.persistence.Entity;

@Entity
public class CreditBankCard extends BankCard {
    public CreditBankCard(User user) {
        this.setUser(user);
    }

    public CreditBankCard() {
    }
}
