import java.util.Scanner;

public class ATMSimulation {
    private double balance;
    private int pin;
    private String[] transactionHistory;
    private int transactionCount;

    public ATMSimulation(double initialBalance, int initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
        this.transactionHistory = new String[100]; 
        this.transactionCount = 0;
        addTransaction("Account opened with initial balance: " + initialBalance);
    }

    private void addTransaction(String transaction) {
        if (transactionCount < transactionHistory.length) {
            transactionHistory[transactionCount++] = transaction;
        } else {
            System.out.println("Transaction history is full.");
        }
    }

    public void checkBalance() {
        System.out.println("Your current balance is: " + balance);
        addTransaction("Checked balance: " + balance);
    }

    public void withdrawCash(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("You have withdrawn: " + amount);
            addTransaction("Withdrew: " + amount + ", Remaining balance: " + balance);
        }
    }

    public void depositCash(double amount) {
        balance += amount;
        System.out.println("You have deposited: " + amount);
        addTransaction("Deposited: " + amount + ", New balance: " + balance);
    }

    public void changePin(int oldPin, int newPin) {
        if (oldPin == pin) {
            pin = newPin;
            System.out.println("PIN successfully changed.");
            addTransaction("PIN changed.");
        } else {
            System.out.println("Incorrect old PIN.");
        }
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (int i = 0; i < transactionCount; i++) {
            System.out.println(transactionHistory[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMSimulation atm = new ATMSimulation(1000.0, 1234);  // Initial balance and PIN

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Cash");
            System.out.println("3. Deposit Cash");
            System.out.println("4. Change PIN");
            System.out.println("5. Print Transaction History");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int ch = scanner.nextInt();

            switch (ch) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdrawCash(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.depositCash(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter old PIN: ");
                    int oldPin = scanner.nextInt();
                    System.out.print("Enter new PIN: ");
                    int newPin = scanner.nextInt();
                    atm.changePin(oldPin, newPin);
                    break;
                case 5:
                    atm.printTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
