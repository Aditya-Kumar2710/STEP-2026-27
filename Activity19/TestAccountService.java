package Activity19;
import java.util.List;

public class TestAccountService {

    public static void main(String[] args) throws Exception {

        System.out.println("============================================================");
        System.out.println("  ACTIVITY 19 - ACCOUNT SERVICE DEMO");
        System.out.println("============================================================");

        // STEP 12: Set up the service
        MemoryLogDestination destination =
            new MemoryLogDestination();

        TransactionLogger logger =
            new TransactionLogger(destination);

        AccountService service =
            new AccountService(logger);

        // STEP 13: Open two accounts
        Account john = service.openAccount(
            "SAVINGS", "John Doe", 25, 15000
        );

        john.setPin(1234);

        Account jane = service.openAccount(
            "SAVINGS", "Jane Smith", 30, 10000
        );

        jane.setPin(5678);

        System.out.println("\n[STEP 13] Opened: "
            + john.getAccountInfo());

        System.out.println("[STEP 13] Opened: "
            + jane.getAccountInfo());

        // STEP 14: Deposit
        Transaction deposit = service.deposit(
            john.getAccountNumber(), 5000
        );

        System.out.println("\n[STEP 14] Deposit: " + deposit);

        // STEP 15: Withdraw
        Transaction withdrawal = service.withdraw(
            john.getAccountNumber(), 2000, 1234
        );

        System.out.println("[STEP 15] Withdrawal: " + withdrawal);

        // STEP 16: Transfer
        Transaction transfer = service.transfer(
            john.getAccountNumber(),
            jane.getAccountNumber(),
            1000,
            1234
        );

        System.out.println("[STEP 16] Transfer: " + transfer);

        // STEP 17: Final balances
        System.out.println("\n[STEP 17] Final Balances:");

        System.out.println("  John (Account #"
            + john.getAccountNumber() + "): Rs. "
            + john.getBalance());

        System.out.println("  Jane (Account #"
            + jane.getAccountNumber() + "): Rs. "
            + jane.getBalance());

        // STEP 18: Transaction history
        List<TransactionCommand> history =
            service.getTransactionHistory();

        System.out.println("\n[STEP 18] Transaction History ("
            + history.size() + " records):");

        int count = 1;

        for (TransactionCommand cmd : history) {
            System.out.println("  [" + count + "] "
                + cmd.getTransaction());

            count++;
        }

        // STEP 19: Error handling
        System.out.println("\n[STEP 19] Error Handling Checks:");

        try {
            service.deposit(9999, 1000);
        } catch (AccountException e) {
            System.out.println(
                "  Deposit to missing account caught: "
                + e.getMessage() + " [PASS]"
            );
        }

        try {
            service.withdraw(
                john.getAccountNumber(), 500, 9999
            );
        } catch (InvalidPinException e) {
            System.out.println(
                "  Withdrawal with wrong PIN caught: "
                + e.getMessage() + " [PASS]"
            );
        }
    }
}