import java.time.LocalDate;

public class Transaction {

    private LocalDate issueDate;
    private double amount;

    private double balance;

    public Transaction(LocalDate issueDate, double amount, double balance) {
        this.issueDate = issueDate;
        this.amount = amount;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
    public LocalDate getIssueDate() {
        return issueDate;
    }

    public double getAmount() {
        return amount;
    }

}
