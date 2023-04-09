# Awesome GIC Application ([Requirement](./src/main/resources/BankAccountCodingExercise.docx))


### Design

The application is designed using the following components:

`StartBankingApp`: Main class that initializes and runs the application. It prompts the user for input and calls the appropriate method in the TransactionService interface to handle the user's request.

`TransactionService`: Interface that defines the methods for deposit, withdraw, print statement, and quit. The TransactionServiceImpl class implements this interface and provides the logic for handling these operations.

`Transaction`: Model class that represents a single transaction. It contains information such as the date, type of transaction (deposit/withdrawal), amount, and balance.

### The following assumptions were made during the development of this application:

- Users can only deposit or withdraw money in whole dollars or up to 2 decimal places (e.g. 10.50).
- Users can only withdraw money up to the available balance in their account.
- The transaction history is stored in memory and will not persist after the application is closed.
- Users can go back to main page halfway in a transaction by pressing `q`.
- The application only supports a single user account.

### How to Run

To run the application, follow these steps:

- Make sure you have Java installed on your machine and Maven running.

- Download or clone the repository to your local machine.

  `https://github.com/norulshahlam/assignment-awesome-gic`

- Navigate to the project directory in the terminal/command prompt.

- Compile the code by running the following command:

  `mvn clean install package`

- The build application will will stored in /target folder. Now run the following command:  

  `java -jar target/assignment-awesome-gic-0.0.1-SNAPSHOT.jar`

- The application will display a welcome message and prompt you to choose an action. Type in the letter corresponding to the action you want to take (e.g. "d" for deposit) and press Enter.

- Follow the prompts to complete the selected action.

- After completing an action, the application will ask if there is anything else you'd like to do. Type in the letter corresponding to the action you want to take (again) and press `Enter`

- To quit the application at any time, type "q" and press `Enter`.

### Conclusion

Congratulations, you have successfully compiled and run the AwesomeGIC Banking App! With this application, users can easily manage their finances from the command line.