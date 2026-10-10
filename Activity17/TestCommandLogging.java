package Activity17;

import java.util.List;

public class TestCommandLogging {

    public static void main(String[] args) throws Exception {

        System.out.println("==============================================");
        System.out.println(" ACTIVITY 17 - COMMAND PATTERN + FILE LOGGING");
        System.out.println("==============================================");

        TransactionLog log = new TransactionLog();

        // Start with a fresh log file for this test.
        log.clear();

        SavingsAccount acc1 = new SavingsAccount(
            1001, "Rajesh Sharma", 30, 15000
        );

        SavingsAccount acc2 = new SavingsAccount(
            1002, "Priya Patel", 28, 10000
        );

        acc1.setPin(1234);
        acc2.setPin(5678);

        // Step 1: Deposit
        DepositCommand deposit =
            new DepositCommand(acc1, 5000);

        deposit.execute();
        log.log(deposit);

        System.out.println("\n[STEP 11] Logged: "
            + deposit.getTransaction());

        // Step 2: Withdrawal
        WithdrawCommand withdraw =
            new WithdrawCommand(acc1, 2000, 1234);

        withdraw.execute();
        log.log(withdraw);

        System.out.println("[STEP 12] Logged: "
            + withdraw.getTransaction());

        // Step 3: Transfer
        TransferCommand transfer =
            new TransferCommand(acc1, acc2, 3000, 1234);

        transfer.execute();
        log.log(transfer);

        System.out.println("[STEP 13] Logged: "
            + transfer.getTransaction());

        // Step 4: Read all saved commands
        List<TransactionCommand> history = log.readAll();

        System.out.println("\n[STEP 14] Read "
            + history.size() + " commands from transaction log:");

        int count = 1;

        for (TransactionCommand command : history) {
            System.out.println("  [" + count + "] "
                + command.getTransaction());
            count++;
        }

        // Step 5: Verify saved data with a new log object
        TransactionLog freshLog = new TransactionLog();
        List<TransactionCommand> saved = freshLog.readAll();

        if (saved.size() == 3) {
            System.out.println("\n[STEP 15] Fresh reader verified "
                + saved.size() + " persisted commands [PASS]");
        } else {
            System.out.println("\n[STEP 15] Persistence check failed.");
        }

        System.out.println("\nFinal account balances:");
        System.out.println("Rajesh: Rs. " + acc1.getBalance());
        System.out.println("Priya: Rs. " + acc2.getBalance());
    }
}
