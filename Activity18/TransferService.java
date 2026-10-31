package Activity18;

public class TransferService {

    public Transaction transfer(Account from, Account to,
                                double amount, int pin)
            throws AccountException {

        if (from == null || to == null) {
            throw new AccountException("Accounts cannot be null");
        }

        if (!from.verifyPin(pin)) {
            throw new AccountException("Incorrect PIN");
        }

        if (amount <= 0 || amount > from.getBalance()) {
            throw new AccountException("Invalid transfer amount");
        }

        from.withdraw(amount, pin);
        to.deposit(amount);

        return new Transaction(
            TransactionType.TRANSFER,
            amount,
            from.getBalance(),
            "Transfer from account " + from.getAccountNumber()
                + " to account " + to.getAccountNumber()
        );
    }
}