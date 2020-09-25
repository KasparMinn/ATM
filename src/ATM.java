import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {

        // INITIALIZE!
        Scanner sc = new Scanner(System.in);

        // Initialize banko:
        Bank theBank = new Bank("Bank of Banks");

        // Add a user, which also creates a savings account. We hope.
        User addUser = theBank.addUser("John", "Jooben", "6969");

        // Add a checking account for the user
        Account newAccount = new Account("Hecking", addUser, theBank);
        addUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while (true) {

            // Stay in the login prompt until successful login:
            curUser = ATM.mainMenuPrompt(theBank, sc);

            //  Stay in the main menu until user quits:
            ATM.printUserMenu(curUser, sc);
        }
    }

    public static User mainMenuPrompt(Bank theBank, Scanner sc) {

        // Initializations:
        String userID;
        String pin;
        User authUser;

        // Prompt the user for user-ID and pin until a correct one is reached:
        do {

            System.out.print("\n\nWelcome to " + theBank.getName() + "!");
            System.out.println("Enter user ID: ");
            userID = sc.nextLine();

            System.out.print("Enter user PIN: ");
            pin = sc.nextLine();

            // Try to get the user object corresponding to the ID & PIN!
            authUser = theBank.userLogin(userID, pin);

            if (authUser == null) {
                System.out.println("Incorrect user ID and/or PIN.\nPlease try again!\n");
            }

        } while(authUser == null);

        System.out.println("Test result:\n" + authUser.toString() + "\n\n");
        return authUser;

    }

    public static void printUserMenu(User theUser, Scanner sc) {

        // Prints a summary of the user's accounts:
        theUser.printAccountsSummary();

        // Initialize!
        int choice;

        // User menu:
        do {

            System.out.println("Welcome!\n What would you like to do, " + theUser.getFirstName() + "?");

            System.out.println("1) Show account transaction history.");
            System.out.println("2) Withdrawal.");
            System.out.println("3) Deposit.");
            System.out.println("4) Transfer.");
            System.out.println("5) Quit.");

            System.out.println("\n\nEnter choice: ");

            choice = sc.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice, please choose numbers from 1 to 5.");
            }

        } while(choice < 1 || choice > 5);

        // Process the user's choice:
        switch (choice) {

            case 1:
                ATM.showTransactionHistory(theUser, sc);
                break;
            case 2:
                // ATM.withdrawFunds(theUser, sc);
                break;
            case 3:
                // ATM.depositFunds(theUser, sc);
                break;
            case 4:
                // ATM.transferFunds(theUser, sc);
                break;
        }

        // redisplay this menu unless the user wants to quit with simple recursion:
        if (choice != 5) {
            ATM.printUserMenu(theUser, sc);
        }
    }

    public static void showTransactionHistory(User theUser, Scanner sc) {

        int theAcct;

        // Get account whose transaction history to look at:
        do {
            System.out.printf("Enter the number of your account (1-%d) of the account" +
                    "whose transactions you want to see: ", theUser.numAccounts());
            theAcct = sc.nextInt() - 1;

            if (theAcct < 0 || theAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account, please try again!");
            }
        } while (theAcct < 0 || theAcct >= theUser.numAccounts());

        theUser.printAccountTransactionHistory(theAcct);

    }

}
