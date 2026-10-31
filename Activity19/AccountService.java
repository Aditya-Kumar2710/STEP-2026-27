package Activity19;
import java.util.*;

public class AccountService {

    // STEP 1: Fields
    private Map<Integer, Account> accounts;
    private TransactionLogger logger;
    private TransferService transferService;
    private int nextAccountNumber = 1001;

    // STEP 2: Constructor
    public AccountService(TransactionLogger logger) {
        this.logger = logger;
        this.accounts = new HashMap<>();
        this.transferService = new TransferService();
        this.nextAccountNumber = 1001;
    }

    // STEP 3: Open Account
    public Account openAccount(String type, String name,
            int age, double initialBalance) throws AccountException {

        int accountNumber = nextAccountNumber++;

        Account account = AccountFactory.createAccount(
            type, accountNumber, name, age, initialBalance
        );

        accounts.put(accountNumber, account);

        return account;
    }

    // STEP 4: Close Account
    public void closeAccount(int accountNumber, int pin)
            throws AccountException {

        Account account = accounts.get(accountNumber);

        if (account == null) {
            throw new AccountException(
                "Account not found: " + accountNumber
            );
        }

        if (!account.verifyPin(pin)) {
            throw new InvalidPinException("Incorrect PIN");
        }

        account.closeAccount();
    }

    // STEP 5: Deposit
    public Transaction deposit(int accountNumber, double amount)
            throws Exception {

        Account account = accounts.get(accountNumber);

        if (account == null) {
            throw new AccountException(
                "Account not found: " + accountNumber
            );
        }

        DepositCommand cmd = new DepositCommand(account, amount);

        cmd.execute();

        if (logger != null) {
            logger.log(cmd);
        }

        return cmd.getTransaction();
    }

    // STEP 6: Withdraw
    public Transaction withdraw(int accountNumber,
            double amount, int pin) throws Exception {

        Account account = accounts.get(accountNumber);

        if (account == null) {
            throw new AccountException(
                "Account not found: " + accountNumber
            );
        }

        WithdrawCommand cmd =
            new WithdrawCommand(account, amount, pin);

        cmd.execute();

        if (logger != null) {
            logger.log(cmd);
        }

        return cmd.getTransaction();
    }

    // STEP 7: Transfer
    public Transaction transfer(int fromAccountNumber,
            int toAccountNumber, double amount, int pin)
            throws Exception {

        Account fromAccount = accounts.get(fromAccountNumber);
        Account toAccount = accounts.get(toAccountNumber);

        if (fromAccount == null) {
            throw new AccountException(
                "Account not found: " + fromAccountNumber
            );
        }

        if (toAccount == null) {
            throw new AccountException(
                "Account not found: " + toAccountNumber
            );
        }

        TransferCommand cmd = new TransferCommand(
            fromAccount, toAccount, amount, pin
        );

        cmd.execute();

        if (logger != null) {
            logger.log(cmd);
        }

        return cmd.getTransaction();
    }

    // STEP 8: Get Account
    public Account getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    // STEP 9: Get All Accounts
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    // STEP 10: Get Transaction History
    public List<TransactionCommand> getTransactionHistory() {
        return logger != null
            ? logger.readAll()
            : new ArrayList<>();
    }

    // STEP 11: Get Next Account Number
    public int getNextAccountNumber() {
        return nextAccountNumber;
    }
}