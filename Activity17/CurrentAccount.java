package Activity17;

public class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(int no, String name, int age,
                          double balance, double limit) {
        super(no, name, age, balance, "CURRENT");
        overdraftLimit = limit;
    }

    @Override
    public boolean canWithdraw(double amount) {
        return amount <= balance + overdraftLimit;
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

        if (amount > balance + overdraftLimit) {
            throw new InsufficientBalanceException("Overdraft limit exceeded");
        }

        balance -= amount;
    }
}