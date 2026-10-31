package Activity18;
import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    private int accountNumber;
    private String name;
    private double balance;
    private int pin;

    public Account(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public boolean verifyPin(int pin) {
        return this.pin == pin;
    }

    public void deposit(double amount) throws AccountException {
        if (amount <= 0) {
            throw new AccountException("Invalid deposit amount");
        }

        balance += amount;
    }

    public void withdraw(double amount, int pin)
            throws AccountException {

        if (!verifyPin(pin)) {
            throw new AccountException("Incorrect PIN");
        }

        if (amount <= 0) {
            throw new AccountException("Invalid withdrawal amount");
        }

        if (amount > balance) {
            throw new AccountException("Insufficient balance");
        }

        balance -= amount;
    }

    public Transaction depositWithTransaction(double amount)
            throws AccountException {

        deposit(amount);

        return new Transaction(
            TransactionType.DEPOSIT,
            amount,
            balance,
            "Deposit to account " + accountNumber
        );
    }

    public Transaction withdrawWithTransaction(double amount, int pin)
            throws AccountException {

        withdraw(amount, pin);

        return new Transaction(
            TransactionType.WITHDRAW,
            amount,
            balance,
            "Withdrawal from account " + accountNumber
        );
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
}