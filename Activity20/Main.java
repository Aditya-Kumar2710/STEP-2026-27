package Activity20;


public class Main {

    public static void main(String[] args) {

        System.out.println("Booting Global Digital Bank...");

        // STEP 15: Connect all components

        LogDestination destination = new FileLogDestination();

        TransactionLogger logger =
                new TransactionLogger(destination);

        AccountService service =
                new AccountService(logger);

        AccountUI ui = new AccountUI(service);

        ui.start();
    }
}
