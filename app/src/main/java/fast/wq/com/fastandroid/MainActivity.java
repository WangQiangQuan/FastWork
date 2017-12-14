package fast.wq.com.fastandroid;

import android.Manifest;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import fast.wq.com.fastandroid.badge.BadgeChangedListener;
import fast.wq.com.fastandroid.badge.BadgeMessage;
import fast.wq.com.fastandroid.bean.ListBean;
import fast.wq.com.fastandroid.bean.SClass;
import fast.wq.com.fastandroid.bean.pClass;
import fast.wq.com.fastandroid.permissions.PermissionActivity;
import fast.wq.com.fastandroid.service.MyJobService;
import fast.wq.com.fastandroid.utils.DmSpannableUtils;
import fast.wq.com.fastandroid.utils.Utils;
import fast.wq.com.fastandroid.view.DynamicView;
import fast.wq.com.fastandroid.view.TaskLinerLayout;
import fast.wq.com.fastandroid.view.recyclerview.fengzhuang.DmRecommend;
import kuaiya.imitate.shortvideolibrary.ShortVideoDialog;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "wqqq";


    TaskLinerLayout mtaskLinerlayout;
    private ImageView image;
    private DynamicView mdynamicView;
    private float mStartX, mStopX;
    private TextView mTvTotalCoins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvTotalCoins = (TextView) findViewById(R.id.total_zapya);
        xuli();
//        String result = getArgUrl(GS_HOME_RES_RECORD_URL,"1");
//        Log.d(TAG, "onCreate() called with: result = [" + result + "]");


//        mtaskLinerlayout = (TaskLinerLayout) findViewById(R.id.layout);
//        addVIew(10);
//
//       String url= StringUtils.getPageName("http://downloadb.dewmobile.net/z/qiangjing13.apk");
//        Log.i("wang",url);

//        image = (ImageView) findViewById(R.id.image);
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (TextUtils.isEmpty(mPath)) {
////                    go2ShortVideo();
//                    autoObtainCameraPermission();
//                } else {
//                    openView(mPath);
//                }
//
//            }
//        });
//        BitmapUtils.calScleType(image,98,74,200,200);
//        image.setImageResource(R.drawable.test);

//        PushBadgeProcessor mPushProcessor = new PushBadgeProcessor(this,1);
//        mPushProcessor.registerBadgeChangeListener(mListener);
//        mPushProcessor.reloadData();

//        GlobalStates.setContext(this.getApplication());
//
//        mdynamicView = (DynamicView) findViewById(R.id.mdynamicView);
//        LinkListUtils list = new LinkListUtils();
//        list.add(111);
//        list.add(222);
//        list.add(333);
//        list.cauclate();
//
//        Intent mintent = new Intent(this, VGHActivity.class);
//        Intent mintent = new Intent(this, ListActivity.class);
//        Intent mintent = new Intent(this, ListFengActivity.class);
//        this.startActivity(mintent);

//        CountDownLatchUtils.go();

//        CyclicBarrierUtils.go();

//        setCoins(920);

//        Intent mintent = new Intent(this, PermissionActivity.class);
//        this.startActivity(mintent);


//        String a = "111";
//        String b = "true";
//        Long.parseLong(b);

//        test();
//        jobserver();

//        String wh = "100x145";
//       Point m = StringUtils.parseWh(wh);
//        Log.i("wang", "onCreate: ="+m.x+"//"+m.y);
//
//        float rate = Utils.getRate(m.x,m.y);
//        int height = Utils.getRateHeight(this,rate);
//        Log.i("wang", "onCreate: height="+height);
//
//
//        int a = 7;
//        int b =3;
//        Log.i("wang", "onCreate:  ="+( a | b));
        startTask();
    }
    Object obj = new Object();
    private void startTask(){

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                Log.d(TAG, "doInBackground() called with: voids = [" + voids + "]");


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);
                                Log.d(TAG, "notify() called with: voids = ["  + "]");
                                obj.notifyAll();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    try {
                        Log.d(TAG, "wait() called with: voids = ["  + "]");
                        obj.wait();


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Log.d(TAG, "end() called with: voids = [" + voids + "]");
                    return null;


            }
        }.execute();
    }

    public ExecutorService mSingleExecutor ;
    private int a = 0;
    public void test(){
        mSingleExecutor =  Executors.newFixedThreadPool(1);
        for (int i=0;i<20;i++){
            if ( mSingleExecutor.isShutdown()){
                return;
            }
            mSingleExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    if (isFinishing()){
                        return;
                    }
                    Log.i("wang", "run() called = "+a+" "+System.currentTimeMillis());
                    try {
                        a++;
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i("wang", "run() called finish = "+a+" "+System.currentTimeMillis());
                }
            });
        }
    }

    private void setCoins(int coins) {
        String totalFormatStr = getString(R.string.zapya_bean_total_format);
        Integer coinsInteger = Integer.valueOf(coins);
        String formattedStr = String.format(totalFormatStr, coins);
        String coinsStr = coinsInteger.toString();
        int startIndex = formattedStr.indexOf(coinsStr);
        int endIndex = startIndex + coinsStr.length();
        SpannableString tzSs = DmSpannableUtils.setTextSize(formattedStr, startIndex, endIndex, 54);
        mTvTotalCoins.setText(tzSs);


        SpannableStringBuilder sb = new SpannableStringBuilder(formattedStr); // 包装字体内容
//        ForegroundColorSpan fcs = new ForegroundColorSpan(getResources().getColor(R.color.)); // 设置字体颜色
        StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // 设置字体样式
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(54);  // 设置字体大小

//        sb.setSpan(fcs, 0, 10, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sb.setSpan(bss, startIndex, endIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        sb.setSpan(ass, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvTotalCoins.setText(sb);
//        mTvTotalCoins.setText(DmSpannableUtils.setTextForeground(formattedStr, startIndex, endIndex, getResources().getColor(R.color.bean_item_positive_color)));
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mStartX = ev.getRawX();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                mStopX = ev.getRawX();
//                mdynamicView.updateView(mStartX, mStopX);
//                break;
//        }
//        return super.dispatchTouchEvent(ev);
//    }

    BadgeChangedListener mListener = new BadgeChangedListener() {

        @Override
        public void onBadgeChanged(BadgeMessage badgeMessage) {
            Log.i("wang", "badgeMessage" + badgeMessage.toString());
        }
    };

    private void addVIew(int size) {
        for (int i = 0; i < size; i++) {

            View view = this.getLayoutInflater().inflate(R.layout.item_row, null);
            TextView tv = (TextView) view.findViewById(R.id.tv);
            TextView action = (TextView) view.findViewById(R.id.action);
            onclick(i, action);
            tv.setText("ss = " + i);
            mtaskLinerlayout.addView(view);
        }
    }

    private void onclick(final int i, TextView action) {
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("wang", "i=" + i);
            }
        });
    }

    private String mPath;

    public void go2ShortVideo() {
        ShortVideoDialog.show(getSupportFragmentManager(), new ShortVideoDialog.VideoCallback() {

            @Override
            public void videoPathCall(String path) {
                Log.d(TAG, "videoPathCall() called with: path = [" + path + "]");

                //根据视频地址获取缩略图
                mPath = path;
                Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.MINI_KIND);
                image.setImageBitmap(bitmap);
//                first.setText(getFileSize(path));

                scanFile(MainActivity.this, mPath);
            }
        }, ShortVideoDialog.Q480, MainActivity.this,R.style.EnterExitAnimation);
    }
    public void scanFile(Context context, String filePath) {
        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        scanIntent.setData(Uri.fromFile(new File(filePath)));
        context.sendBroadcast(scanIntent);
    }


    public void openView(String path) {
        if (TextUtils.isEmpty(path)) {

            return;
        }
        File file = new File(path);
        Utils.openFile(file, this);
    }

    /**
     * 自动获取相机权限
     */
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;

    private void autoObtainCameraPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
//                ToastUtils.showShort(this, "您已经拒绝过一次");
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.RECORD_AUDIO}, CAMERA_PERMISSIONS_REQUEST_CODE);
        } else {//有权限直接调用系统相机拍照
            go2ShortVideo();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case  CAMERA_PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    go2ShortVideo();
                }
                break;
        }
    }

    public LinkedHashMap<Integer, ArrayList<DmRecommend>> dataMap = new LinkedHashMap<Integer, ArrayList<DmRecommend>>();
    final long awaitTime = 5 * 1000;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            mSingleExecutor.shutdown();
            if(!mSingleExecutor.awaitTermination(awaitTime, TimeUnit.MILLISECONDS)){
                // 超时的时候向线程池中所有的线程发出中断(interrupted)。
                mSingleExecutor.shutdownNow();
            }
        }catch (Exception e){

        }

    }

    private int mJobId = 0;
    private void jobserver(){
        JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(MainActivity.this, MyJobService.class);
        JobInfo.Builder builder = new JobInfo.Builder(++mJobId, componentName);

        //设置JobService执行的最小延时时间
//        builder.setMinimumLatency(Long.valueOf("5") * 1000);
        //设置JobService执行的最晚时间
        builder.setOverrideDeadline(Long.valueOf("10") * 1000);

        boolean requiresUnmetered = false;
        boolean requiresAnyConnectivity =false;
        if (requiresUnmetered) {
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);
        } else if (requiresAnyConnectivity) {
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        }

        builder.setPeriodic(1000);//可以每隔1秒运行一次。
        builder.setPersisted(true);//这个方法告诉系统当你的设备重启之后你的任务是否还要继续执行。
//        builder.setRequiresDeviceIdle(true);//是否要求设备为idle状态
//        builder.setRequiresCharging(false);//是否要设备为充电状态

        scheduler.schedule(builder.build());
    }

    private void xuli(){
//        //序列化到本地
//        User user=new User(0,"wcl_android@163.com","123456");
//        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("user.obj"));
//        out.writeObject(user);
//        out.close;
//
////反序列化
//        ObjectInputStream in=new ObjectInputStream(new FileInputStream("user.obj"));
//        User user=(User)in.readObject();
//        in.close();

        pClass m = new pClass();
        m.name = "名字";
        ListBean bean = new ListBean();
        bean.setName("名字");
        SClass ms = new SClass();
        ms.sName = "子类名字";
        ms.name = "父名字";

        Bundle bundle = new Bundle();
//        bundle.putParcelable("bean", m);
//        bundle.putParcelable("bean", bean);
        bundle.putParcelable("bean", ms);
        Intent intent = new Intent(MainActivity.this,PermissionActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
