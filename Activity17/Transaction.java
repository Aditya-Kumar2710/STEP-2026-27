package Activity17;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int counter = 0;

    private String transactionId;
    private LocalDateTime timestamp;
    private int accountNumber;
    private TransactionType type;
    private double amount;
    private double balanceAfter;
    private String status;
    private String description;
    private int fromAccount;
    private int toAccount;

    public Transaction(String transactionId, LocalDateTime timestamp,
            int accountNumber, TransactionType type, double amount,
            double balanceAfter, String status, String description,
            int fromAccount, int toAccount) {
        this.transactionId = transactionId;
        this.timestamp = timestamp;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.status = status;
        this.description = description;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public static String generateId() {
        return "TXN-" + System.currentTimeMillis() + "-" + (++counter);
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + transactionId + "] " + type
                + " | Rs. " + amount
                + " | Balance After: Rs. " + balanceAfter
                + " | Status: " + status
                + " | " + description;
    }
}