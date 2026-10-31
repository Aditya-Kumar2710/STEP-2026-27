package Activity18;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private static int counter = 0;

    private String transactionId;
    private LocalDateTime timestamp;
    private TransactionType type;
    private double amount;
    private double balanceAfter;
    private String description;

    public Transaction(TransactionType type, double amount,
                       double balanceAfter, String description) {
        this.transactionId = generateId();
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.description = description;
    }

    public static String generateId() {
        return "TXN-" + System.currentTimeMillis() + "-" + (++counter);
    }

    @Override
    public String toString() {
        return "[" + transactionId + "] "
                + type + " | Rs. " + amount
                + " | Balance After: Rs. " + balanceAfter
                + " | SUCCESS | " + description;
    }
}