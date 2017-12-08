package fast.wq.com.fastandroid.permissions;

import android.Manifest;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import fast.wq.com.fastandroid.R;
import fast.wq.com.fastandroid.bean.SClass;

/**
 * http://blog.csdn.net/yanzhenjie1003/article/details/52503533/
 */
public class PermissionActivity extends Activity {

    private static final String TAG = "PermissionActivity";
    private static final int CAMERA_PERMISSIONS_REQUEST_CODE = 0x03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

//        checkAndIntent();
        getmyIntent();
        miuiCheak();
    }
    private void getmyIntent(){
        Intent intent = getIntent();
        Bundle bun = intent.getExtras();
//        pClass bean = bun.getParcelable("bean");
       // ListBean bean = bun.getParcelable("bean");
        SClass bean = bun.getParcelable("bean");
        Log.i("wang", "getmyIntent() called = "+ bean.sName +" ／／／" +bean.name);
    }

    private void checkAndIntent() {
        if (PermissionsUtils.isNeedPermission()) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // 没有权限。
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    // 用户拒绝过这个权限了，应该提示用户，为什么需要这个权限。
                } else {
                    // 申请授权。
                    ActivityCompat.requestPermissions(PermissionActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSIONS_REQUEST_CODE);
                }
            }
        } else {
            doSomething();
        }
    }

    private void checkAndIntentWithMi() {
        if (PermissionsUtils.isNeedPermission()) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // 没有权限。
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    // 用户拒绝过这个权限了，应该提示用户，为什么需要这个权限。
                }
                else {
                    // 申请授权。
                    ActivityCompat.requestPermissions(PermissionActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSIONS_REQUEST_CODE);
                }
            }
        } else {
            doSomething();
        }
    }

    private void miuiCheak(){
        //适配小米机型
        Log.d(TAG, "miuiCheak()0000 called");
        AppOpsManager appOpsManager = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        int checkOp = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            checkOp = appOpsManager.checkOp(AppOpsManager.OPSTR_CAMERA, android.os.Process.myUid(), getPackageName());
            Log.d(TAG, "miuiCheak() checkOp = "+checkOp);
            if (checkOp == AppOpsManager.MODE_IGNORED) {
                Log.d(TAG, "miuiCheak()11111 called");
                //0允许
                //1拒绝
                //4询问
                intent2SysSetting();
                ActivityCompat.requestPermissions(PermissionActivity.this,
                        new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSIONS_REQUEST_CODE);

            }else {
                Log.d(TAG, "miuiCheak()2222 called");
            }
        }
        Log.d(TAG, "miuiCheak()3333 called");
    }

    private void intent2SysSetting(){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
// 根据包名打开对应的设置界面
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);

    }

    private void doSomething() {

    }

    /**
     * @param requestCode  int requestCode，在调用requestPermissions()时的第一个参数。
     * @param permissions  权限数组，在调用requestPermissions()时的第二个参数。
     * @param grantResults 授权结果数组，对应permissions，具体值和上方提到的PackageManager中的两个常量做比较。
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
