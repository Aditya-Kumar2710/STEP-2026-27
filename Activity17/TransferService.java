package Activity17;
import java.time.LocalDateTime;

public class TransferService {

    public Transaction transferWithTransaction(
            IAccount from, IAccount to, double amount, int pin)
            throws AccountException {

        if (from == null || to == null) {
            throw new AccountException("Accounts cannot be null");
        }

        if (!from.getStatus().equalsIgnoreCase("ACTIVE")
                || !to.getStatus().equalsIgnoreCase("ACTIVE")) {
            throw new InactiveAccountException("Both accounts must be active");
        }

        if (!from.verifyPin(pin)) {
            throw new InvalidPinException("Incorrect PIN");
        }

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }

        Account source = (Account) from;

        if (!source.canWithdraw(amount)) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        // Withdraw first, then deposit.
        from.withdraw(amount, pin);
        to.deposit(amount);

        return new Transaction(
            Transaction.generateId(), LocalDateTime.now(),
            from.getAccountNumber(), TransactionType.TRANSFER,
            amount, from.getBalance(), "SUCCESS",
            "Transfer of Rs. " + amount + " to Account #"
                + to.getAccountNumber(),
            from.getAccountNumber(), to.getAccountNumber()
        );
    }
}