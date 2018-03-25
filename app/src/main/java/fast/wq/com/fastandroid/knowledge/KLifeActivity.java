package fast.wq.com.fastandroid.knowledge;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fast.wq.com.fastandroid.R;

/**
 * 1正常情况下的生命周期
 * 2异常情况
 *  1资源相关的系统配置发生变化导致activity被杀死，并且重新创建。
 *  2资源不足导致低优先级activity被杀死
 *
 * 不想屏幕旋转销毁activity
 * android:configChanges="orientation|screenSize"
 *
 *  LaunchMode 和标志位：
 *  1standard:
 *    a启动了b ，那么就把b压入a的栈里。
 *    如果用ApplicationContext那么需要指定 Flag_activity_new_task标志位。
 *  2singleTop: 栈顶复用
 *    onNewIntent将会被调用。
 *  3singleTask: 栈内复用
 *    寻找需要栈内实例，有则用，没有则创建。
 *   自带cleartop
 *    onNewIntent将会被调用。
 *  4singleInstance:单实例模式
 *   加强版栈内复用：此种模式的activity只能弹雨位于一个任务栈
 *
 *   activiy所需要的任务栈：
 *   TaskAffinity:任务的相关性：
 *   默认包名
 *   TaskAffinity 主要和singleTask或者allowTaskReparenting属性配对使用。
 *   1：待启动的activity 会运行在名字和 TaskAffinity相同的任务栈中。
 *   2：应用a启动b的某个activity C，如果c allowTaskReparentin= true，那么当b启动后，C会直接从a的任务栈里转移到b的任务栈。
 *
 *   adb shell dumpsys activity 查看activity信息
 */
public class KLifeActivity extends AppCompatActivity {

    private static final String TAG = "KLife";
    /**
     * 初始化
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klife);
    }

    /**
     * 不可见到可见
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * 已经可见了，但是还无法交互
     * 还在后台
     */
    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * 可见，可交互 到前台。
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 可以简单处理一些暂停，保存数据的工作
     * 但不能耗时，会影响新的activity显示
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 可以稍微做一点重量级的回收。
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 最终释放
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 异常时才会调用。
     *  调用时机onStop 之前
     *  onPause 没有时序关系。
     * @param outState
     * @param outPersistentState
     * activity为我们默认保存一些试图结构
     * 文本框的输入数据，listview的滚动位置等
     * 具体可以查看view的onSaveInstanceState看看恢复了那些数据。
     * 比如Textview
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("extra_test","test");
    }

    /**
     * 调用时机 onStart 之后
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
       String test = savedInstanceState.getString("extra_test","");
    }

    /**
     * android:configChanges="orientation|screenSize"
     * 不会调用舍命周期
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
