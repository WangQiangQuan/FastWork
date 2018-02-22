package fast.wq.com.fastandroid.thread.syn;

import android.util.Log;

/**
 * 取钱线程
 * 引入同步监视器来解决问题。
 * synchronized同一时刻只有一个线程可以获得监视器的锁定。
 */

public class SynDrawThread extends Thread {
    private static final String TAG = "DrawThread";
    private Account mAccount;
    private double drawAmount;//希望取出多少钱

    public SynDrawThread(Account mAccount, double drawAmount) {
        this.mAccount = mAccount;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {
        synchronized (mAccount){
            if (mAccount.getBalance() >= drawAmount){
                Log.i(TAG, "run: 取钱成功");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mAccount.setBalance(mAccount.getBalance() - drawAmount);
                Log.i(TAG, "run: 余额:"+mAccount.getBalance());
            }else {
                Log.i(TAG, "run: 余额不足");
            }
        }

    }

    public static void main(){
        Account acct = new Account("111",1000);
        //两个线程对同一个账户取钱
        new SynDrawThread(acct,800).start();
        new SynDrawThread(acct,800).start();
        //多次运行后，正确

    }
}
