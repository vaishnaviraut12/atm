import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private double balance;
    private int pin;
    private ArrayList<String> transactionHistory;

    public ATM(double initialBalance, int initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial balance: $" + initialBalance);
    }

    public boolean authenticate(int enteredPin) {
        return this.pin == enteredPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void changePin(int newPin) {
        this.pin = newPin;
        transactionHistory.add("PIN changed.");
        System.out.println("PIN changed successfully.");
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating a new ATM account with an initial balance and PIN
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        System.out.print("Set your PIN: ");
        int initialPin = scanner.nextInt();

        ATM atm = new ATM(initialBalance, initialPin);
        boolean authenticated = false;

        while (!authenticated) {
            System.out.print("Enter PIN to access ATM: ");
            int enteredPin = scanner.nextInt();
            authenticated = atm.authenticate(enteredPin);
            if (!authenticated) {
                System.out.println("Invalid PIN. Try again.");
            }
        }

        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Cash Deposit");
            System.out.println("3. Cash Withdrawal");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: $" + atm.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter new PIN: ");
                    int newPin = scanner.nextInt();
                    atm.changePin(newPin);
                    break;
                case 5:
                    atm.printTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}