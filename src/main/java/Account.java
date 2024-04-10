import java.time.LocalDate;
import java.util.LinkedList;

public class Account {

    private LinkedList<Transaction> transactionList ;

    private double balance;

    public Account() {
        this.transactionList = new LinkedList<>();
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        balance += amount;
        LocalDate todaysDate = LocalDate.now();
        transactionList.add(new Transaction(todaysDate, amount, balance));
    }

    public void remove(double amount) throws IllegalArgumentException, InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (balance < amount) {
            throw new InsufficientFundsException("Insufficient funds for this transaction.");
        }
        balance -= amount;
        LocalDate todaysDate = LocalDate.now();
        transactionList.add(new Transaction(todaysDate, -amount, balance));
    }


    @Override
    public String toString() {
        StringBuilder statement = new StringBuilder("Date\t\tAmount\tBalance\n");
        for (Transaction transaction : transactionList) {
            statement.append(transaction.getIssueDate())
                    .append("\t")
                    .append((transaction.getAmount() > 0 ? "+" : "")).append(transaction.getAmount())
                    .append("\t")
                    .append(transaction.getBalance())
                    .append("\n");
        }
        return statement.toString();
    }


}
