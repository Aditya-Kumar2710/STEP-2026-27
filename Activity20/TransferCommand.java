package Activity20;
public class TransferCommand implements TransactionCommand {
    private static final long serialVersionUID = 1L;

    private transient Account from;
    private transient Account to;
    private double amount;
    private int pin;
    private Transaction transaction;

    public TransferCommand(Account from, Account to,
                           double amount, int pin) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.pin = pin;
    }

    @Override
    public void execute() throws Exception {
        transaction = new TransferService().transfer(
            from, to, amount, pin
        );

        from = null;
        to = null;
    }

    @Override
    public Transaction getTransaction() {
        return transaction;
    }
}