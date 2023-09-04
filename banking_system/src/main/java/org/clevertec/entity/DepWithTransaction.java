package org.clevertec.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class DepWithTransaction extends Transaction{
    private Account account;

    public DepWithTransaction(String type, BigDecimal amount, LocalDate date, LocalTime time, Account account) {
        super(type, amount, date, time);
        this.account = account;
    }

    @Override
    public String toString() {
        return "DepositTransaction{" +
                "account=" + account +
                " " + super.toString() +
                '}';
    }
}
