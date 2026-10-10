package Activity17;

public class TransferCommand implements TransactionCommand {
    private static final long serialVersionUID = 1L;

    private transient IAccount from;
    private transient IAccount to;
    private double amount;
    private int pin;
    private Transaction transaction;

    public TransferCommand(IAccount from, IAccount to,
                           double amount, int pin) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.pin = pin;
    }

    @Override
    public void execute() throws Exception {
        TransferService service = new TransferService();

        transaction = service.transferWithTransaction(
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