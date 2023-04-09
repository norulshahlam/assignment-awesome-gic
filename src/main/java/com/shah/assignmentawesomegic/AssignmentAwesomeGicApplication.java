package com.shah.assignmentawesomegic;

import com.shah.assignmentawesomegic.startup.StartBankingApp;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author NORUL
 */
@SpringBootApplication
public class AssignmentAwesomeGicApplication {

    public static void main(String[] args) {
        StartBankingApp startBankingApp = new StartBankingApp();
        startBankingApp.startMe();
    }
}
