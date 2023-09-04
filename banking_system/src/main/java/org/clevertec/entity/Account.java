package org.clevertec.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
    private int id;
    private int number;
    private Bank bank;
    private User user;
    private BigDecimal balance;

    public Account(int number, Bank bank, User user, BigDecimal balance) {
        this.number = number;
        this.bank = bank;
        this.user = user;
        this.balance = balance;
    }

    public Account(int number) {
        this.number = number;
    }
}
