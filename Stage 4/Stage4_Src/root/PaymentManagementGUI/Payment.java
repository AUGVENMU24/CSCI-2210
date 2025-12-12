package PaymentManagementGUI;

public class Payment {
    private double balance;

    public Payment(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void pay(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    public void addCharge(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}
