package fast.wq.com.fastandroid.surport;

import android.os.AsyncTask;

/**
 * 部分源代码阅读
 * AsyncTask:http://blog.csdn.net/whocases/article/details/54289288
 * handler
 * volley
 * eventbus
 * glide
 * greendao
 */

public class read {
    public void Asy() {
        new MyAsyncTask().execute();
    }

    private class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
