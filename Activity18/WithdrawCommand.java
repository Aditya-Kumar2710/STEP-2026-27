package Activity18;

public class WithdrawCommand implements TransactionCommand {
    private static final long serialVersionUID = 1L;

    private transient Account account;
    private double amount;
    private int pin;
    private Transaction transaction;

    public WithdrawCommand(Account account, double amount, int pin) {
        this.account = account;
        this.amount = amount;
        this.pin = pin;
    }

    @Override
    public void execute() throws Exception {
        transaction = account.withdrawWithTransaction(amount, pin);
        account = null;
    }

    @Override
    public Transaction getTransaction() {
        return transaction;
    }
}