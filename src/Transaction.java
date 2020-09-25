import java.util.Date;

public class Transaction {

    private double amount;
    private Date timestamp;

    // Comment that goes with the transaction.
    private String memo;

    // The account in which the transaction was performed.
    private Account inAccount;

    public Transaction(double amount, Account inAccount) {

        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";

    }

    public Transaction(double amount, String memo, Account inAccount) {

        // Call the two-argument constructor first:
        this(amount, inAccount);

        // Set the memo as an addition!
        this.memo = memo;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getSummaryLine() {

        if(this.amount >= 0) {
            return String.format("%s : $%.2f : %s", this.timestamp.toString(), this.amount, this.memo);
        } else {
            return String.format("%s : $(%.2f) : %s", this.timestamp.toString(), this.amount, this.memo);
        }
    }
}
