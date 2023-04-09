package com.shah.assignmentawesomegic.startup;

import com.shah.assignmentawesomegic.impl.TransactionServiceImpl;
import com.shah.assignmentawesomegic.service.TransactionService;

import java.util.Scanner;

/**
 * @author NORUL
 */
public class StartBankingApp {

    Scanner scanner = new Scanner(System.in);
    public void startMe() {
        TransactionService service = new TransactionServiceImpl();

        welcomeMesage();
        options();

        while (true) {
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "d":
                    service.deposit();
                    break;
                case "w":
                    service.withdraw();
                    break;
                case "p":
                    service.printStatement();
                    break;
                case "q":
                    service.quit();
                    scanner.close();
                    return;
                default:
                    inValidChoiceMessage();
                    options();
                    break;
            }
        }
    }
    private static void inValidChoiceMessage() {
        System.out.println("Invalid choice. Please try again.");
    }

    private static void welcomeMesage() {
        System.out.println("Welcome to AwesomeGIC Bank! What would you like to do?");
    }

    public static void options() {
        System.out.println("[D]eposit");
        System.out.println("[W]ithdraw");
        System.out.println("[P]rint statement");
        System.out.println("[Q]uit");
    }

    public static void rePrompt() {
        System.out.println("Is there anything else you'd like to do?");
    }
}
