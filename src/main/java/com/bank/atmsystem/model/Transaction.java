package com.bank.atmsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String atmId;
    private String type;  // DEPOSIT, WITHDRAWAL, BALANCE
    private double amount;
    private String customerId;
    private Date timestamp;
}

