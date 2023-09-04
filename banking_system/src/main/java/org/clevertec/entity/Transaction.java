package org.clevertec.entity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {
    private int id;
    private String type;
    private BigDecimal amount;
    private LocalDate date;
    private LocalTime time;

    public Transaction(String type, BigDecimal amount, LocalDate date, LocalTime time) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.time = time;
    }
}
