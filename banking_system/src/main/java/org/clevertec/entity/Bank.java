package org.clevertec.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bank {
    private int id;
    private String name;

    public Bank(String name) {
        this.name = name;
    }
}
