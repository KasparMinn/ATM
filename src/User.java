import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

public class User {

    private String firstName;
    private String lastName;
    private String uuid;

     /**
     * The MD5 hash of the user's pin number.
     */

    private byte[] pinHash;
    private ArrayList<Account> accounts;

    public User(String firstName, String lastName, String pin, Bank theBank) {

        // Set user's name:
        this.firstName = firstName;
        this.lastName = lastName;

        // Store the PIN number's MD5 hash, rather than the original value:
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());

        }

        // This will never happen!!
        catch (NoSuchAlgorithmException e) { System.err.println("Caught NoSuchAlgorithm!"); e.printStackTrace(); }

        // Get a new, unique universal ID for the user:
        this.uuid = theBank.getNewUserUUID();

        // Create empty list of accounts:
        this.accounts = new ArrayList<Account>();

        // Print out the log message:
        System.out.printf("New user %s, %s with ID %s created. \n", lastName, firstName, this.uuid);

    }

    public void addAccount(Account anAcct) { this.accounts.add(anAcct); }
    public String getUUID() { return this.uuid; }

    public boolean validatePin(String oPin) {

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(oPin.getBytes()), this.pinHash);

        }

        catch (NoSuchAlgorithmException e) { System.err.println("Caught NoSuchAlgorithm!"); e.printStackTrace();  }
        return false;

    }

    public String getFirstName() { return this.firstName; }

    public void printAccountsSummary() {

        System.out.printf("\n\n%s's accounts summary:", this.firstName);

        for (int a = 0; a < this.accounts.size(); a++) {
            System.out.printf("%d) %s\n", a+1, this.accounts.get(a).getSummaryLine());
        }

    }

    public int numAccounts() {
        return this.accounts.size();
    }

    public void printAccountTransactionHistory(int accountIndex) {

        this.accounts.get(accountIndex).printTransactionHistory();

    }
}
