package fast.wq.com.fastandroid.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by admin on 2017/11/22.
 * JobScheduler来执行这些特殊的后台任务时来减少电量的消耗。
 * 5.0以上才有
 * 最后，总结一下JobService的使用：
 1）先继承JobService，并重写startJob和stopJob
 2）在manifest.xml中声明JobService的时候，记得一定要加上
 android:permission=”android.permission.BIND_JOB_SERVICE”
 3）后台任务不能执行耗时任务，如果一定要这么做，一定要再起一个线程去做，使用 thread/handler/AsyncTask都可以。
 4）JobService一定要设置至少一个执行条件，如有网络连接、充电中、系统空闲...
 5）任务执行完后记得调用jobFinish通知系统释放相关资源
 如果我们的后台任务满足JobService的一个或多个约束条件，就可以考虑是不是应该用JobService来执行。
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)//API需要在21及以上
public class MyJobService extends JobService {
    private static final String TAG = "MyJobService";
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.i(TAG, "onStartJob: "+ jobParameters.getJobId());
        Toast.makeText(MyJobService.this, "start job:" + jobParameters.getJobId(), Toast.LENGTH_SHORT).show();
        jobFinished(jobParameters, false);//任务执行完后记得调用jobFinsih通知系统释放相关资源
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i(TAG, "onStopJob: "+jobParameters.getJobId());
        return false;
    }

}
