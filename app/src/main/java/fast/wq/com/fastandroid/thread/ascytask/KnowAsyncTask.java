package fast.wq.com.fastandroid.thread.ascytask;

import android.os.AsyncTask;

/**
 * https://blog.csdn.net/google_huchun/article/details/65630850
 * AsyncTask是对Handler与线程池的封装
 * 可以从 构造方法+execute角度为源码说明
 */

public class KnowAsyncTask {
    public void test() {
        AsyTest m = new AsyTest();
        m.execute("");
    }

    /**
      public abstract class AsyncTask<Params, Progress, Result>
     Params：doInBackground方法的参数类型；
     Progress：AsyncTask所执行的后台任务的进度类型；
     Result：后台任务的返回结果类型。
     */
    public class AsyTest extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }
}
