package org.bank.service;

import org.bank.api.Bank;
import org.example.dto.BankCard;
import org.example.dto.BankCardType;
import org.example.dto.CreditBankCard;
import org.example.dto.DebitBankCard;
import org.example.dto.ICreditCard;
import org.example.dto.IDebitCard;
import org.example.dto.User;

public class BankImpl implements Bank, IDummy {
    @Override
    public BankCard createBankCard(User user, BankCardType type) {
//        return BankCardType.CREDIT == type ?   new CreditBankCard(user) :  new DebitBankCard(user);
        BankCard res;
        if (BankCardType.CREDIT == type) {
            ICreditCard credit = CreditBankCard::new;
            res = credit.getBankCard(user);
        } else {
            IDebitCard debit = DebitBankCard::new;
            res = debit.getBankCard(user);
        }
        return res;
    }

    @Override
    public void doFoo() {
        //empty
    }
}
