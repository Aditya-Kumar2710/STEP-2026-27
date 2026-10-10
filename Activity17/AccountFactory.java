package Activity17;

public class AccountFactory {

    public static IAccount createAccount(String type, int no,
            String name, int age, double balance) {

        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }

        switch (type.toUpperCase()) {
            case "SAVINGS":
                return new SavingsAccount(no, name, age, balance);

            case "CURRENT":
                return new CurrentAccount(no, name, age, balance, 25000);

            case "FIXED_DEPOSIT":
            case "FIXEDDEPOSIT":
                return new FixedDepositAccount(no, name, age, balance);

            default:
                throw new IllegalArgumentException(
                    "Unknown account type: " + type
                );
        }
    }
}