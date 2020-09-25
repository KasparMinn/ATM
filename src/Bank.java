import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private String name;

    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank (String name) {

        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();

    }

    public String getNewUserUUID() {

        // Initialize!
        String uuid;
        Random rng = new Random();
        int len = 7;
        boolean nonUnique;

        // Continue looping until non-unique ID is true!
        do {

            uuid = "";

            // Generate the numbero:
            for (int c = 0 ; c < len ; c++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            // Here we check if the number is unique:
            nonUnique = false;

            for (User u : this.users) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return uuid;

    }

    public String getNewAccountUUID() {

        // Initialize!
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        // Continue looping until non-unique ID is true!
        do {

            uuid = "";

            // Generate the numbero:
            for (int c = 0 ; c < len ; c++) {
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            // Here we check if the number is unique:
            nonUnique = false;

            for (Account u : this.accounts) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }
            }

        } while (nonUnique);

        return uuid;

    }

    public void addAccount(Account anAcct) { this.accounts.add(anAcct); }

    public User addUser(String firstName, String lastName, String pin) {

        // Create a new user object and add it to our list.
        // As it takes 4 arguments specified in the User, we put 'this' as theBank:
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        // Create a savings account fr the user:
        Account newAccount = new Account("Savings", newUser, this);

        // Add to holder and bank lists:
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;

    }

    public User userLogin(String userID, String pin) {

        // Search through list of users:
        for (User u : this.users) {
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {

                return u;
            }
        }

        return null;

    }

    public String getName() {
        return this.name;
    }
}
