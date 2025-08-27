package concurrent3;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBank {
    Set<BankAccount> accounts;
    ConcurrentHashMap<Long, BankAccount> bankAccounts = new ConcurrentHashMap<>();

    public BankAccount createAccount(double amount) {
        BankAccount account = new BankAccount(amount);
        bankAccounts.put(account.getAccountId(), account);
        return account;
    }

    public void transfer(BankAccount account1, BankAccount account2, double amount) {
        if (account1 == null || account2 == null) {

        throw new IllegalArgumentException("Аккаунты не налл");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount больше нуля");
        }
        BankAccount firstAccountLock = account1.getAccountId() < account2.getAccountId() ? account1 : account2;
        BankAccount secondAccountLock = account2.getAccountId() < account1.getAccountId() ? account2 : account1;
        firstAccountLock.getLock().writeLock().lock();
        try {
            secondAccountLock.getLock().writeLock().lock();

            try {
                if (firstAccountLock.getBalance() >= amount) {
                    account1.withdraw(amount);
                    account2.deposit(amount);
                } else {
                    System.out.println("Деняк не хватает");
                }
            } finally {
                secondAccountLock.getLock().writeLock().unlock();
            }
        } finally {
            firstAccountLock.getLock().writeLock().unlock();
        }
    }

    public double getTotalBalance() {
        return bankAccounts.values().stream().mapToDouble(BankAccount::getBalance).sum();
        }
}
