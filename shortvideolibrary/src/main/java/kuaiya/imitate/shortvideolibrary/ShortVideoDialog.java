package kuaiya.imitate.shortvideolibrary;


import android.content.Context;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 录制小视频 对话框
 */

public class ShortVideoDialog extends DialogFragment {

    private static final String TAG = "ShortVideoDialog";

    private Context mContext;
    private int quality = CamcorderProfile.QUALITY_480P;
    private VideoCallback videoCall;

    public static int Q480 = CamcorderProfile.QUALITY_480P;
    public static int Q720 = CamcorderProfile.QUALITY_720P;
    public static int Q1080 = CamcorderProfile.QUALITY_1080P;
    public static int Q21600 = CamcorderProfile.QUALITY_2160P;
    private final int MAX_TIME = 5000;
    private final int Min_TIME = 3000;
    private final int TIME_SLICE = 10;

    private CameraPreview mCameraPreview;
    private ProgressBar mProgressRight, mProgressLeft;
    private MediaRecorder mMediaRecorder;
    FrameLayout preview;
    private Timer mTimer;
    private long time;
    private boolean isRecording = false;
    private String fileName;
    private int mTimeCount;
    private Handler mainHandler = new Handler(Looper.getMainLooper());


    public static ShortVideoDialog newInstance(VideoCallback videoCall, int quality, Context context) {
        ShortVideoDialog dialog = new ShortVideoDialog();
        dialog.setVideoCall(videoCall);
        dialog.setQuality(quality);
        dialog.setmContext(context);
        dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.maskDialog);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_video_input, container, false);

        initview(v);
        return v;
    }

    private void initview(View v) {
        preview = (FrameLayout) v.findViewById(R.id.camera_preview);
        if (mContext == null) {
            mContext = getActivity();
        }
        mCameraPreview = new CameraPreview(mContext);
        mProgressRight = (ProgressBar) v.findViewById(R.id.progress_right);
        mProgressLeft = (ProgressBar) v.findViewById(R.id.progress_left);
        mProgressRight.setMax(MAX_TIME);
        mProgressLeft.setMax(MAX_TIME);
        mProgressLeft.setRotation(180);
        ImageButton record = (ImageButton) v.findViewById(R.id.btn_record);
        preview.addView(mCameraPreview);

        record.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按下 开始录像
                        if (!isRecording) {
                            if (prepareVideoRecorder()) {
                                time = Calendar.getInstance().getTimeInMillis(); //倒计时
                                mMediaRecorder.start();
                                isRecording = true;
                                mTimer = new Timer();
                                mTimeCount = 0;
                                mTimer.schedule(new TimerTask() {
                                    @Override
                                    public void run() {
                                        mTimeCount++;
                                        Log.d(TAG, "run() called = "+mTimeCount+" / "+Thread.currentThread());
                                        mainHandler.post(updateProgress);
                                        if (mTimeCount * TIME_SLICE >= MAX_TIME) {
                                            if (mTimer != null) mTimer.cancel();
                                            mainHandler.post(sendVideo);
                                        }
                                    }
                                }, 0, TIME_SLICE);
                            } else {
                                releaseMediaRecorder();
                            }
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        //抬起 停止录像
                        recordStop();
                        break;
                }
                return true;
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mCameraPreview != null && mCameraPreview.getCamera() == null) {
            mCameraPreview = new CameraPreview(mContext);
            preview.addView(mCameraPreview);
        }

    }

    @Override
    public void onPause() {
        super.onPause();

        recordStop();
        releaseMediaRecorder();
        releaseCamera();
    }


    private Runnable updateProgress = new Runnable() {
        @Override
        public void run() {
            int progress = mTimeCount * TIME_SLICE;
            mProgressRight.setProgress(progress);
            mProgressLeft.setProgress(progress);
        }
    };
    private Runnable sendVideo = new Runnable() {
        @Override
        public void run() {
            recordStop();
        }
    };

    public static void show(FragmentManager ft, VideoCallback videoCall, int quality, Context context) {
        DialogFragment newFragment = ShortVideoDialog.newInstance(videoCall, quality, context);
        newFragment.show(ft, TAG);
    }

    //Recorder
    private boolean prepareVideoRecorder() {

        if (mCameraPreview.getCamera() == null) return false;
        mMediaRecorder = new MediaRecorder();
        mCameraPreview.getCamera().unlock();
        mMediaRecorder.setCamera(mCameraPreview.getCamera());
        //声音
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        //视频
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        //设置分辨率为480P
        mMediaRecorder.setProfile(CamcorderProfile.get(quality));
        //路径
        mMediaRecorder.setOutputFile(getOutputMediaFile().toString());
        mMediaRecorder.setPreviewDisplay(mCameraPreview.getHolder().getSurface());


        try {
            //旋转90度 保持竖屏
            mMediaRecorder.setOrientationHint(90);
            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            Log.d(TAG, "IllegalStateException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            Log.d(TAG, "IOException preparing MediaRecorder: " + e.getMessage());
            releaseMediaRecorder();
            return false;
        }
        return true;
    }

    private void releaseMediaRecorder() {
        if (mMediaRecorder != null) {
            mMediaRecorder.reset();   // clear recorder configuration
            mMediaRecorder.release(); // release the recorder object
            mMediaRecorder = null;
            if (mCameraPreview.getCamera()!= null){
                mCameraPreview.getCamera().lock();           // lock camera for later use
            }
            if (isLongEnough()) {
                videoCall.videoPathCall(fileName);
            } else {
                Toast.makeText(getContext(), "录制过短，请重试", Toast.LENGTH_SHORT).show();
            }
            disMissDialog();
        }
    }

    /**
     * 停止录制
     */
    private void recordStop() {
        if (isRecording) {
            isRecording = false;
            if (isLongEnough()) {
                mMediaRecorder.stop();
            }
            releaseMediaRecorder();
            if (mCameraPreview.getCamera() != null) {
                mCameraPreview.getCamera().lock();
            }

            if (mTimer != null) mTimer.cancel();
            mTimeCount = 0;
            mainHandler.post(updateProgress);

        }
    }

    /**
     * 停止 camera
     */
    private void releaseCamera() {
        mCameraPreview.releaseCamera();
    }

    public void disMissDialog() {
        if (this != null && this.getDialog() != null && this.getDialog().isShowing()) {
            this.dismiss();
        }

    }

    /**
     * 判断录制时间
     *
     * @return
     */
    private boolean isLongEnough() {
        return Calendar.getInstance().getTimeInMillis() - time > Min_TIME;
    }

    /**
     * Create a File for saving an image or video
     */
    private File getOutputMediaFile() {

//        return  new File(getContext().getExternalCacheDir().getAbsolutePath() + "/" + fileName);
//        PackageManager pm = mContext.getPackageManager();
        String appName = mContext.getPackageName();
        File dir = new File(Environment.getExternalStorageDirectory() + "/" + appName);
        if (!dir.exists()) {
            dir.mkdir();
        }
        fileName = dir + "/video_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".mp4";
        Log.i("filePath", fileName);
        return new File(fileName);
    }

    //interface
    public static interface VideoCallback {
        public void videoPathCall(String path);
    }

    public void setVideoCall(VideoCallback videoCall) {
        this.videoCall = videoCall;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }
}
