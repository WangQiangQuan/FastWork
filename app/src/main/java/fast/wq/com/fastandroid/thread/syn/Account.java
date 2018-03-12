package fast.wq.com.fastandroid.thread.syn;

import android.util.Log;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 账户类
 */

public class Account {
    private static final String TAG = "Account";
    private String accountNo;
    private double balance;

    public Account() {
    }

    public Account(String accountNo, double balance) {

        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (Double.compare(account.balance, balance) != 0) return false;
        return accountNo != null ? accountNo.equals(account.accountNo) : account.accountNo == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = accountNo != null ? accountNo.hashCode() : 0;
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    //定义锁对象
    private final ReentrantLock lock = new ReentrantLock();

    public void drawMoney(double drawAmount) {
        lock.lock();
        try {
            if (balance >= drawAmount) {
                Log.i(TAG, "--->run: 取钱成功");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance = balance - drawAmount;
                Log.i(TAG, "run: 余额:" + balance);
            } else {
                Log.i(TAG, "run: 余额不足");
            }
        } finally {
            lock.unlock();
        }
    }

    public void saveMoney(double saveAmount) {
        lock.lock();
        try {
            Log.i(TAG, "<--run: 存钱成功");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance = balance + saveAmount;
            Log.i(TAG, "run: 余额:" + balance);

        } finally {
            lock.unlock();
        }
    }



}
