package com.shah.assignmentawesomegic.startup;

import com.shah.assignmentawesomegic.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StartBankingAppTest {

    @Mock
    private TransactionServiceImpl transactionService;

    @InjectMocks
    private StartBankingApp startBankingApp;

    @Mock
    Scanner scanner;

    @BeforeEach
    void setUp() {
        transactionService.setBalance(100.0);
        transactionService.setTransactions(new ArrayList<>());
    }

    @Test
    void testStartMe() {

        // for main options
        when(scanner.nextLine()).thenReturn("f", "w", "d", "p", "q");

        // for service options
        String input = "q\nq\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        startBankingApp.startMe();
    }
}