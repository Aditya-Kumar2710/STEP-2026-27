package Activity17;

import java.time.LocalDateTime;

public abstract class Account implements IAccount {
    protected int accountNumber;
    protected String name;
    protected int age;
    protected double balance;
    protected String accountType;
    protected String status = "ACTIVE";
    protected int pin;

    public Account(int accountNumber, String name, int age,
                   double balance, String accountType) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.accountType = accountType;
    }

    @Override
    public boolean verifyPin(int enteredPin) {
        return pin == enteredPin;
    }

    @Override
    public void setPin(int pin) {
        if (pin < 1000 || pin > 9999) {
            throw new IllegalArgumentException("PIN must contain 4 digits");
        }
        this.pin = pin;
    }

    @Override
    public void deposit(double amount) throws AccountException {
        if (!status.equalsIgnoreCase("ACTIVE")) {
            throw new InactiveAccountException("Account is inactive");
        }

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }

        balance += amount;
    }

    public Transaction depositWithTransaction(double amount)
            throws AccountException {
        deposit(amount);

        return new Transaction(
            Transaction.generateId(), LocalDateTime.now(),
            accountNumber, TransactionType.DEPOSIT, amount,
            balance, "SUCCESS", "Deposit of Rs. " + amount,
            0, accountNumber
        );
    }

    @Override
    public void withdraw(double amount, int enteredPin)
            throws AccountException {
        if (!status.equalsIgnoreCase("ACTIVE")) {
            throw new InactiveAccountException("Account is inactive");
        }

        if (!verifyPin(enteredPin)) {
            throw new InvalidPinException("Incorrect PIN");
        }

        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }

        if (!canWithdraw(amount)) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        balance -= amount;
    }

    public Transaction withdrawWithTransaction(double amount, int enteredPin)
            throws AccountException {
        withdraw(amount, enteredPin);

        return new Transaction(
            Transaction.generateId(), LocalDateTime.now(),
            accountNumber, TransactionType.WITHDRAW, amount,
            balance, "SUCCESS", "Withdrawal of Rs. " + amount,
            accountNumber, 0
        );
    }

    public boolean canWithdraw(double amount) {
        return amount <= balance;
    }

    @Override
    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getAccountType() {
        return accountType;
    }

    @Override
    public String getStatus() {
        return status;
    }
}