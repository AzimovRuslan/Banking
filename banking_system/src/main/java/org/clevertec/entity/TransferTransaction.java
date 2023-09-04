package org.clevertec.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class TransferTransaction extends Transaction {
    private Account accountOfSender;
    private Account accountOfGetter;

    public TransferTransaction(String type, BigDecimal amount, LocalDate date, LocalTime time, Account accountOfSender, Account accountOfGetter) {
        super(type, amount, date, time);
        this.accountOfSender = accountOfSender;
        this.accountOfGetter = accountOfGetter;
    }

    @Override
    public String toString() {
        return "TransferTransaction{" +
                "accountOfSender=" + accountOfSender +
                ", accountOfGetter=" + accountOfGetter +
                " " + super.toString() +
                '}';
    }
}
