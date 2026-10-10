package Activity17;

public class FixedDepositAccount extends Account {

    public FixedDepositAccount(int no, String name, int age, double balance) {
        super(no, name, age, balance, "FIXED_DEPOSIT");
    }

    @Override
    public void withdraw(double amount, int pin) throws AccountException {
        throw new AccountException("Premature withdrawal is not allowed");
    }
}