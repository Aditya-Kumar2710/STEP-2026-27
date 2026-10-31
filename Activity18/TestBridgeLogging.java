package Activity18;

public class TestBridgeLogging {

    // Creates and logs three transactions
    public static void logThreeTransactions(
            TransactionLogger logger) throws Exception {

        Account acc1 = new Account(
            1001, "Rajesh Sharma", 15000
        );

        Account acc2 = new Account(
            1002, "Priya Patel", 10000
        );

        acc1.setPin(1234);
        acc2.setPin(5678);

        // Deposit
        TransactionCommand deposit =
            new DepositCommand(acc1, 1000);

        deposit.execute();
        logger.log(deposit);

        // Withdrawal
        TransactionCommand withdraw =
            new WithdrawCommand(acc1, 500, 1234);

        withdraw.execute();
        logger.log(withdraw);

        // Transfer
        TransactionCommand transfer =
            new TransferCommand(acc1, acc2, 1000, 1234);

        transfer.execute();
        logger.log(transfer);
    }

    public static void main(String[] args) throws Exception {

        System.out.println("============================================================");
        System.out.println("  ACTIVITY 18 - BRIDGE PATTERN (FILE + DB)");
        System.out.println("============================================================");

        // Create all three destinations
        FileLogDestination fileDest =
            new FileLogDestination();

        SimulatedDatabase db =
            new SimulatedDatabase();

        DatabaseLogDestination dbDest =
            new DatabaseLogDestination(db);

        MemoryLogDestination memDest =
            new MemoryLogDestination();

        // Clear previous data
        fileDest.clear();
        dbDest.clear();
        memDest.clear();

        // Create logger with FILE destination
        TransactionLogger logger =
            new TransactionLogger(fileDest);

        // STEP 25: File logging
        System.out.println("\n[STEP 25] Logging to FILE destination...");

        logThreeTransactions(logger);

        System.out.println("  FILE log count: "
            + logger.readAll().size());

        // STEP 26: Switch to DATABASE
        logger.setDestination(dbDest);

        System.out.println("\n[STEP 26] Switched to DATABASE destination...");

        logThreeTransactions(logger);

        System.out.println("  DATABASE log count: "
            + logger.readAll().size());

        // STEP 27: Switch to MEMORY
        logger.setDestination(memDest);

        System.out.println("\n[STEP 27] Switched to MEMORY destination...");

        logThreeTransactions(logger);

        System.out.println("  MEMORY log count: "
            + logger.readAll().size());

        // STEP 28: Verify data isolation
        System.out.println("\n[STEP 28] Verifying Data Isolation:");

        logger.setDestination(fileDest);
        int fileCount = logger.readAll().size();

        logger.setDestination(dbDest);
        int dbCount = logger.readAll().size();

        logger.setDestination(memDest);
        int memoryCount = logger.readAll().size();

        System.out.println("  FILE count: " + fileCount
            + " [EXPECTED: 3]");

        System.out.println("  DATABASE count: " + dbCount
            + " [EXPECTED: 3]");

        System.out.println("  MEMORY count: " + memoryCount
            + " [EXPECTED: 3]");

        // STEP 29: Final result
        System.out.println("\n[STEP 29] Destination names:");

        System.out.println("  " + fileDest.getDestinationName());
        System.out.println("  " + dbDest.getDestinationName());
        System.out.println("  " + memDest.getDestinationName());

        if (fileCount == 3
                && dbCount == 3
                && memoryCount == 3) {

            System.out.println(
                "\nAll Bridge Pattern log backends "
                + "verified successfully!"
            );

        } else {
            System.out.println("\nVerification failed!");
        }
    }
}