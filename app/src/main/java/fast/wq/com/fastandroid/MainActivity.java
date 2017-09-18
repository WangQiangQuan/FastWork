package fast.wq.com.fastandroid;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import fast.wq.com.fastandroid.badge.BadgeChangedListener;
import fast.wq.com.fastandroid.badge.BadgeMessage;
import fast.wq.com.fastandroid.utils.DmSpannableUtils;
import fast.wq.com.fastandroid.utils.Utils;
import fast.wq.com.fastandroid.view.DynamicView;
import fast.wq.com.fastandroid.view.TaskLinerLayout;
import kuaiya.imitate.shortvideolibrary.ShortVideoDialog;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


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

//        String result = getArgUrl(GS_HOME_RES_RECORD_URL,"1");
//        Log.d(TAG, "onCreate() called with: result = [" + result + "]");


//        mtaskLinerlayout = (TaskLinerLayout) findViewById(R.id.layout);
//        addVIew(10);
//
//       String url= StringUtils.getPageName("http://downloadb.dewmobile.net/z/qiangjing13.apk");
//        Log.i("wang",url);

        image = (ImageView) findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openView(mPath);
            }
        });
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
//        this.startActivity(mintent);

//        CountDownLatchUtils.go();

//        CyclicBarrierUtils.go();

//        setCoins(920);
        go2ShortVideo();
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                mStopX = ev.getRawX();
                mdynamicView.updateView(mStartX, mStopX);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

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
            }
        }, ShortVideoDialog.Q1080, MainActivity.this);
    }

    public void openView(String path) {
        if (TextUtils.isEmpty(path)) {

            return;
        }
        File file = new File(path);
        Utils.openFile(file, this);
    }
}
