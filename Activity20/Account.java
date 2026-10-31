package Activity20;
import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private int pin;
    private String status = "Active";

    public Account(int accountNumber, String name, int age, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = balance;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public boolean verifyPin(int pin) {
        return this.pin == pin;
    }

    public void deposit(double amount) throws AccountException {
        if (!isActive()) {
            throw new AccountException("Account is inactive");
        }
        if (amount <= 0) {
            throw new AccountException("Invalid deposit amount");
        }
        balance += amount;
    }

    public void withdraw(double amount, int pin) throws AccountException {
        if (!isActive()) {
            throw new AccountException("Account is inactive");
        }
        if (!verifyPin(pin)) {
            throw new InvalidPinException("Incorrect PIN");
        }
        if (amount <= 0) {
            throw new AccountException("Invalid withdrawal amount");
        }
        if (amount > balance) {
            throw new AccountException("Insufficient balance");
        }
        balance -= amount;
    }

    public void closeAccount() throws AccountException {
        if (!isActive()) {
            throw new AccountException("Account is already closed");
        }
        status = "Inactive";
    }

    public Transaction depositWithTransaction(double amount)
            throws AccountException {
        deposit(amount);

        return new Transaction(
            Transaction.generateId(),
            java.time.LocalDateTime.now(),
            accountNumber,
            TransactionType.DEPOSIT,
            amount,
            balance,
            "SUCCESS",
            "Deposit of Rs. " + amount,
            0,
            accountNumber
        );
    }

    public Transaction withdrawWithTransaction(double amount, int pin)
            throws AccountException {
        withdraw(amount, pin);

        return new Transaction(
            Transaction.generateId(),
            java.time.LocalDateTime.now(),
            accountNumber,
            TransactionType.WITHDRAW,
            amount,
            balance,
            "SUCCESS",
            "Withdrawal of Rs. " + amount,
            accountNumber,
            0
        );
    }

    public boolean isActive() {
        return status.equalsIgnoreCase("Active");
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return "Savings";
    }

    public String getStatus() {
        return status;
    }

    public String getAccountInfo() {
        return "Account #" + accountNumber + " | " + name
            + " (" + age + " yrs) | Savings | Rs. "
            + balance + " | " + status;
    }
}