package concurrent3;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BankAccount {

    private static final AtomicLong accountId = new AtomicLong(1);
    private volatile double balance;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Amount больше 0");
        }
        this.balance = initialBalance;
    }

    public long getAccountId() {
        return accountId.getAndIncrement();
    }

    public ReentrantReadWriteLock getLock() {
        return lock;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount больше 0");
        }
        lock.writeLock().lock();
        try {
            balance += amount;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public synchronized void withdraw(double amount) {
        if (amount <= 0)  {
            throw new IllegalArgumentException("Amount больше 0");
        }
        lock.writeLock().lock();
        try {
            if (balance >= amount) {
                balance -= amount;
            } else {
                System.out.println("Деняк не хватает");
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public double getBalance() {
        lock.readLock().lock();
        try {
            return balance;
        } finally {
            lock.readLock().unlock();
        }
    }
}
