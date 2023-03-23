package org.example.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;


@MappedSuperclass
public class BankCard {

    public BankCard() {
    }

    public BankCard(User user) {
        this.user = user;
    }

    @Id
    @Column
    private String number;
    @JoinColumn(name = "users")
    @OneToOne(targetEntity = User.class)
    private User user;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
