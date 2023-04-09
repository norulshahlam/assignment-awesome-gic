package com.shah.assignmentawesomegic.impl;

import com.shah.assignmentawesomegic.model.Transaction;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {
    @Mock
    Scanner scanner = new Scanner(System.in);

    @InjectMocks
    TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        transactionService.setBalance(100.0);
        transactionService.setTransactions(new ArrayList<>());
    }

    @Test
    void depositTest() {
        when(scanner.nextLine()).thenReturn("10");
        transactionService.deposit();
        assertEquals(110.0, transactionService.getBalance());
        assertEquals(1, transactionService.getTransactions().size());
        Transaction transaction = transactionService.getTransactions().get(0);
        assertEquals(LocalDateTime.now().getDayOfYear(), transaction.getDate().getDayOfYear());
        assertEquals("Deposit", transaction.getType());
        assertEquals(10.0, transaction.getAmount());
        assertEquals(110.0, transaction.getBalance());
    }

    @Test
    @DisplayName("Test withdraw with sufficient balance")
    void withdrawTestSufficientBalance() {
        when(scanner.nextLine()).thenReturn("10");
        transactionService.withdraw();
        assertEquals(90.0, transactionService.getBalance());
        Transaction transaction = transactionService.getTransactions().get(0);
        assertEquals(LocalDateTime.now().getDayOfYear(), transaction.getDate().getDayOfYear());
        assertEquals("Withdrawal", transaction.getType());
        assertEquals(-10.0, transaction.getAmount());
        assertEquals(90.0, transaction.getBalance());

        transactionService.withdraw();

    }

    @Test
    @DisplayName("Test withdraw negative, then decided to quit")
    void withdrawTestButQuit() {
        when(scanner.nextLine()).thenReturn("-1", "q");
        transactionService.withdraw();
        assertEquals(100.0, transactionService.getBalance());
        Assertions.assertThat(transactionService.getTransactions()).isEmpty();
    }

    @Test
    @DisplayName("Test withdraw with insufficient balance, then with sufficient balance")
    void withdrawTestInsufficientBalance() {
        when(scanner.nextLine()).thenReturn("200", "50");
        transactionService.withdraw();
        assertEquals(50.0, transactionService.getBalance());
        assertEquals(1, transactionService.getTransactions().size());
        Transaction transaction = transactionService.getTransactions().get(0);
        assertEquals(LocalDateTime.now().getDayOfYear(), transaction.getDate().getDayOfYear());
        assertEquals("Withdrawal", transaction.getType());
        assertEquals(50.0, transaction.getBalance());
    }

    @Test
    @DisplayName("Test withdraw with insufficient balance, then decided to quit")
    void withdrawTestInsufficientBalanceButQuit() {
        when(scanner.nextLine()).thenReturn("200", "q");
        transactionService.withdraw();
        assertEquals(100.0, transactionService.getBalance());
        Assertions.assertThat(transactionService.getTransactions()).isEmpty();
    }

    @Test
    @DisplayName("Test print statement with no transactions")
    void printStatementTestNoTransactions() {
        transactionService.printStatement();
        assertEquals(0, transactionService.getTransactions().size());
    }

    @Test
    @DisplayName("Test print statement with transactions")
    void printStatementTestWithTransactions() {
        when(scanner.nextLine()).thenReturn("10", "20");
        transactionService.deposit();
        transactionService.withdraw();
        transactionService.printStatement();
        assertEquals(2, transactionService.getTransactions().size());
    }

    @Test
    void quit() {
        transactionService.quit();
    }
}
