package Activity20;
public class TransferService {

    public Transaction transfer(
            Account from,
            Account to,
            double amount,
            int pin) throws AccountException {

        if (from == null || to == null) {
            throw new AccountException("Account not found");
        }

        if (!from.isActive() || !to.isActive()) {
            throw new AccountException("Both accounts must be active");
        }

        if (!from.verifyPin(pin)) {
            throw new InvalidPinException("Incorrect PIN");
        }

        if (amount <= 0 || amount > from.getBalance()) {
            throw new AccountException("Invalid transfer amount");
        }

        from.withdraw(amount, pin);
        to.deposit(amount);

        return new Transaction(
            Transaction.generateId(),
            java.time.LocalDateTime.now(),
            from.getAccountNumber(),
            TransactionType.TRANSFER,
            amount,
            from.getBalance(),
            "SUCCESS",
            "Transfer of Rs. " + amount
                + " to Account #" + to.getAccountNumber(),
            from.getAccountNumber(),
            to.getAccountNumber()
        );
    }
}