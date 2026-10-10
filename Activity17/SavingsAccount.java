package Activity17;
public class SavingsAccount extends Account {
    private double minimumBalance = 1000.0;

    public SavingsAccount(int no, String name, int age, double balance) {
        super(no, name, age, balance, "SAVINGS");
    }

    @Override
    public void withdraw(double amount, int pin) throws AccountException {
        if (!status.equalsIgnoreCase("ACTIVE")) {
            throw new InactiveAccountException("Account is inactive");
        }

        if (!verifyPin(pin)) {
            throw new InvalidPinException("Incorrect PIN");
        }

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }

        if (balance - amount < minimumBalance) {
            throw new InsufficientBalanceException(
                "Minimum balance must be maintained"
            );
        }

        balance -= amount;
    }
}