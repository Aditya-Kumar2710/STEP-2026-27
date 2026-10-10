package Activity17;

public class DepositCommand implements TransactionCommand {
    private static final long serialVersionUID = 1L;

    private transient Account account;
    private double amount;
    private Transaction transaction;

    public DepositCommand(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() throws Exception {
        transaction = account.depositWithTransaction(amount);
        account = null;
    }

    @Override
    public Transaction getTransaction() {
        return transaction;
    }
}