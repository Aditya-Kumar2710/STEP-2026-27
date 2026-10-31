package Activity20;
public class AccountFactory {

    public static Account createAccount(
            String type,
            int accountNumber,
            String name,
            int age,
            double balance) throws AccountException {

        if (type == null || !type.equalsIgnoreCase("SAVINGS")) {
            throw new AccountException("Invalid account type: " + type);
        }

        if (age < 18) {
            throw new AccountException(
                "Customer must be at least 18 years old"
            );
        }

        if (balance < 1000) {
            throw new AccountException(
                "Minimum opening balance is Rs. 1000"
            );
        }

        return new Account(accountNumber, name, age, balance);
    }
}