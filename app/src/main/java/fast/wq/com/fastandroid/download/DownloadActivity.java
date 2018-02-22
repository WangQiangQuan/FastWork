package fast.wq.com.fastandroid.download;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import fast.wq.com.fastandroid.R;
import fast.wq.com.fastandroid.utils.IntentUtils;

public class DownloadActivity extends Activity  implements View.OnClickListener{
    Button btn_start,btn_stop;
    ProgressBar mPgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        btn_start = findViewById(R.id.start);
        btn_stop = findViewById(R.id.stop);
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        mPgress = findViewById(R.id.progressBar);
        mPgress.setMax(100);

        IntentFilter filter = new IntentFilter();
        filter.addAction(DownLoadService.ACTION_UPDATE );
        registerReceiver(mReceiver,filter);
   }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start:
                intentToService(DownLoadService.ACTION_START);
                break;
            case R.id.stop:
                intentToService(DownLoadService.ACTION_STOP);
                break;
        }
    }

    String url = "http://www.imooc.com/mobile/imooc.apk";
    private void intentToService(String actionStop) {
        FileInfo fileInfo = new FileInfo(0, "http", "filename", 0, 0);
        Intent mintent = new Intent(DownloadActivity.this, DownLoadService.class);
        mintent.setAction(actionStop);
        IntentUtils.putParcelable(mintent, DownLoadService.fileInof, fileInfo);
    }


    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (DownLoadService.ACTION_UPDATE .equals(intent.getAction())){
                int finished = intent.getIntExtra("finished",0);
                mPgress.setProgress(finished);
            }
        }
    };


}
