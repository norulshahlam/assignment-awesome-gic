package com.shah.assignmentawesomegic.impl;

import com.shah.assignmentawesomegic.model.Transaction;
import com.shah.assignmentawesomegic.service.TransactionService;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.shah.assignmentawesomegic.startup.StartBankingApp.options;
import static com.shah.assignmentawesomegic.startup.StartBankingApp.rePrompt;


/**
 * @author NORUL
 */
@Data
public class TransactionServiceImpl implements TransactionService {
    public static final String messageFormatter = "%-22s| %-8s| %-8s%n";
    private double balance;
    private List<Transaction> transactions;
    Scanner scanner = new Scanner(System.in);

    public TransactionServiceImpl() {
        balance = 0.0;
        transactions = new ArrayList<>();
    }

    @Override
    public void deposit() {
        System.out.print("Please enter the amount to deposit: ");
        String depositAmount = scanner.nextLine();

        double amount = checkForValidIntegerInput(depositAmount);
        if (amount != 0) {
            balance += amount;
            Transaction transaction = new Transaction(LocalDateTime.now(), "Deposit", amount, balance);
            transactions.add(transaction);
            System.out.println("Thank you. $" + amount + " has been deposited to your account.");
        }
        rePrompt();
        options();
    }

    @Override
    public void withdraw() {

        System.out.print("Please enter the amount to withdraw: ");
        String withdrawAmount = scanner.nextLine();

        double amount = checkForValidIntegerInput(withdrawAmount);

        // If user presses 'q', skip to rePrompt() without executing further
        if (amount == 0) {
            rePrompt();
            options();
            return;
        }

        while (amount > balance) {
            System.out.println("Insufficient balance. Withdrawal amount exceeds available balance. Please enter the amount to withdraw:");
            withdrawAmount = scanner.nextLine();
            amount = checkForValidIntegerInput(withdrawAmount);

            // If user presses 'q', skip to rePrompt() without executing further
            if (amount == 0) {
                rePrompt();
                options();
                return;
            }
        }

        balance -= amount;
        Transaction transaction = new Transaction(LocalDateTime.now(), "Withdrawal", -amount, balance);
        transactions.add(transaction);
        System.out.println("Thank you. $" + amount + " has been withdrawn.");
        rePrompt();
        options();
    }

    @Override
    public void printStatement() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        System.out.printf(messageFormatter, "Date", "Amount", "Balance");
        System.out.println("--------------------------------------------------------");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm:ssa");
        for (Transaction transaction : transactions) {
            String formattedDate = transaction.getDate().format(formatter);
            String formattedAmount = String.format("%.2f", transaction.getAmount());
            String formattedBalance = String.format("%.2f", transaction.getBalance());
            System.out.printf(messageFormatter, formattedDate, formattedAmount, formattedBalance);
        }
        rePrompt();
        options();
    }

    @Override
    public void quit() {
        goodByeMessage();
    }

    private double checkForValidIntegerInput(String depositAmount) {

        // Return zero if user wants to exit from current selection
        if ("q".equalsIgnoreCase(depositAmount)) {
            return 0;
        }

        // Check if input is digit, positive value, up to 2 decimal places
        while (!depositAmount.matches("^(?:0|[1-9]\\d*)(?:\\.\\d{1,2})?$")) {
            if ("q".equalsIgnoreCase(depositAmount)) {
                return 0;
            }
            System.out.println("Invalid input. Please enter a digit with positive value, up to 2 decimal places.");
            depositAmount = scanner.nextLine();
        }
        return Double.parseDouble(depositAmount);
    }

    private static void goodByeMessage() {
        System.out.println("Thank you for banking with AwesomeGIC Bank.");
        System.out.println("Have a nice day!");
    }
}
