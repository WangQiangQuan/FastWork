package fast.wq.com.fastandroid.thread;

import fast.wq.com.fastandroid.thread.syn.Account;

/**
 * Created by admin on 2018/3/9.
 */

public class Test {
    public void main() {
        Account mAccount = new Account("1", 1000);
        get(mAccount,500);
        set(mAccount,500);

    }

    public void get(final Account mAccount, final int num) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    mAccount.drawMoney(num);
                }

            }
        }).start();
    }

    public void set(final Account mAccount, final int num) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    mAccount.saveMoney(num);
                }

            }
        }).start();
    }
}
