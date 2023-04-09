package com.shah.assignmentawesomegic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {
        private LocalDateTime date;
        private String type;
        private double amount;
        private double balance;
    }