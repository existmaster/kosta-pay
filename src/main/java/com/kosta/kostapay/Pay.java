package com.kosta.kostapay;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Pay {

    @Id
    private String userId;
    private int balance;

    public int deposit(int amount){
        if(amount>=0) balance+=amount;
        else throw new IllegalArgumentException("Amount cannot be negative");
        return balance;
    }
    public int withdrawal(int amount){
        if(amount>=0) balance-=amount;
        else throw new IllegalArgumentException("Amount cannot be negative");
        return balance;
    }
}
