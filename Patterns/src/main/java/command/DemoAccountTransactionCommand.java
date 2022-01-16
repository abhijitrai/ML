package command;

import lombok.Setter;
import lombok.ToString;

public class DemoAccountTransactionCommand {
    public static void main(String[] args) {
        Account a1 = new Account("A", 100);
        Account a2 = new Account("B", 10);
        printAccount(a1,a2);
        Command cmd1 = new DebitCommand(a1, 5);
        Command cmd2 = new CreditCommand(a2, 5);
        cmd1.apply();
        cmd2.apply();
        printAccount(a1,a2);
        cmd1.undo();
        cmd2.undo();
        printAccount(a1,a2);
    }

    private static void printAccount(Account... accounts) {
        for (Account account : accounts
        ) {
            System.out.println(account);
        }

    }

}

@ToString
class Account {
    Account(String name, long balance) {
        this.name = name;
        this.balance = balance;
    }

    String name;
    @Setter
    long balance;
}

interface Command {
    void apply();

    void undo();
}

class DebitCommand implements Command {

    DebitCommand(Account account, long amount) {
        this.account = account;
        this.amount = amount;
    }

    long amount;
    Account account;
    boolean applied = false;

    @Override
    synchronized public void apply() {
        long newAmount = account.balance - amount;
        account.setBalance(newAmount);
        applied = true;
    }

    @Override
    synchronized public void undo() {
        if (applied) {
            account.setBalance(account.balance + amount);
            applied = false;
        }

    }
}

class CreditCommand implements Command {

    CreditCommand(Account account, long amount) {
        this.account = account;
        this.amount = amount;
    }

    long amount;
    Account account;
    boolean applied = false;

    @Override
    public void undo() {
        if (applied) {
            long newAmount = account.balance - amount;
            account.setBalance(newAmount);
            applied = false;
        }
    }

    @Override
    public void apply() {
        account.setBalance(account.balance + amount);
        applied = true;
    }

}
