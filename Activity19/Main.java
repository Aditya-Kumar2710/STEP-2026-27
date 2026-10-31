package Activity19;
public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("============================================================");
        System.out.println("  GLOBAL DIGITAL BANK - SERVICE DEMO");
        System.out.println("============================================================");

        // STEP 20: Wire up dependencies
        FileLogDestination destination =
            new FileLogDestination();

        TransactionLogger logger =
            new TransactionLogger(destination);

        AccountService service =
            new AccountService(logger);

        // STEP 21: Demo workflow

        Account john = service.openAccount(
            "SAVINGS", "John Doe", 25, 15000
        );

        john.setPin(1234);

        Account jane = service.openAccount(
            "SAVINGS", "Jane Smith", 30, 10000
        );

        jane.setPin(5678);

        System.out.println("\nOpened Accounts:");
        System.out.println(john.getAccountInfo());
        System.out.println(jane.getAccountInfo());

        Transaction deposit =
            service.deposit(john.getAccountNumber(), 5000);

        System.out.println("\nDeposit: " + deposit);

        Transaction withdrawal =
            service.withdraw(john.getAccountNumber(), 2000, 1234);

        System.out.println("Withdrawal: " + withdrawal);

        Transaction transfer =
            service.transfer(
                john.getAccountNumber(),
                jane.getAccountNumber(),
                1000,
                1234
            );

        System.out.println("Transfer: " + transfer);

        System.out.println("\nFinal Balances:");
        System.out.println("John: Rs. " + john.getBalance());
        System.out.println("Jane: Rs. " + jane.getBalance());

        System.out.println("\nTransaction History:");

        for (TransactionCommand cmd : service.getTransactionHistory()) {
            System.out.println(cmd.getTransaction());
        }

        System.out.println("\nLogging Destination: "
            + logger.getDestinationName());

        System.out.println("\nAccount Service Demo Completed!");
    }
}