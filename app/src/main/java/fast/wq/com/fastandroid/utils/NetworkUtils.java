package fast.wq.com.fastandroid.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.HashMap;

import static android.telephony.TelephonyManager.NETWORK_TYPE_1xRTT;
import static android.telephony.TelephonyManager.NETWORK_TYPE_CDMA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EDGE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EHRPD;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_0;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_A;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_B;
import static android.telephony.TelephonyManager.NETWORK_TYPE_GPRS;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSDPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSPAP;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSUPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_IDEN;
import static android.telephony.TelephonyManager.NETWORK_TYPE_LTE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_UMTS;

/**
 * https://www.jianshu.com/p/983889116526
 */
public class NetworkUtils {
    
    private static HashMap<String, String> headers;
    private static int versionCode = -1;
    private static int version = -1;
    private static String versionName = null;

    private static final int MOBILE_TYPE_UNKNOWN = 0;
    private static final int MOBILE_TYPE_2G = 1;
    private static final int MOBILE_TYPE_3G = 2;
    private static final int MOBILE_TYPE_4G = 3;
    public static final String GOOGLE_SERVICE_PKGNAME="com.google.android.gms";
    public static final String GOOGLE_PLAY_PKGNAME="com.android.vending";

    public static NetworkInfo getNetworkInfo(Context context) {
        try {
            ConnectivityManager connMan = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = connMan.getActiveNetworkInfo();
            return info;
        } catch (Exception e) {
        }
        return null;
    }

    public static void checkState_23orNew(Context context) {
        //获得ConnectivityManager对象
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //获取所有网络连接的信息
        Network[] networks = connMgr.getAllNetworks();
        //用于存放网络连接信息
        StringBuilder sb = new StringBuilder();
        //通过循环将网络信息逐个取出来
        for (int i = 0; i < networks.length; i++) {
            //获取ConnectivityManager对象对应的NetworkInfo对象
            NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
            sb.append(networkInfo.getTypeName() + " connect is " + networkInfo.isConnected());
        }
        Log.i("wang", "checkState_23orNew: "+sb.toString());
    }

    public static void checkAPI(){
        //检测API是不是小于23，因为到了API23之后getNetworkInfo(int networkType)方法被弃用
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {

        }
        Log.i("wang", "checkAPI: "+android.os.Build.VERSION.SDK_INT +" //"+android.os.Build.VERSION_CODES.LOLLIPOP);
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected());
    }

    public static boolean isWifiOr4GAvailable(Context context){
        return isWifiAvailable(context) || is4GAvailable(context);
    }

    public static boolean isWifiAvailable(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    public static boolean isMobileNetAvailable(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    public static boolean is4GAvailable(Context context){
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected()  && info.getType() == ConnectivityManager.TYPE_MOBILE && (getMobileSubType(info.getSubtype()) == MOBILE_TYPE_4G) );
    }

    private static int getMobileSubType(int subType){
        switch (subType) {
            case NETWORK_TYPE_GPRS:
            case NETWORK_TYPE_EDGE:
            case NETWORK_TYPE_CDMA:
            case NETWORK_TYPE_1xRTT:
            case NETWORK_TYPE_IDEN:
                return MOBILE_TYPE_2G;
            case NETWORK_TYPE_UMTS:
            case NETWORK_TYPE_EVDO_0:
            case NETWORK_TYPE_EVDO_A:
            case NETWORK_TYPE_HSDPA:
            case NETWORK_TYPE_HSUPA:
            case NETWORK_TYPE_HSPA:
            case NETWORK_TYPE_EVDO_B:
            case NETWORK_TYPE_EHRPD:
            case NETWORK_TYPE_HSPAP:
                return MOBILE_TYPE_3G;
            case NETWORK_TYPE_LTE:
                return MOBILE_TYPE_4G;
            default:
                return MOBILE_TYPE_UNKNOWN;
        }
    }

    public static boolean is3GAvailable(Context context){
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected()  && info.getType() == ConnectivityManager.TYPE_MOBILE && getMobileSubType(info.getSubtype()) == MOBILE_TYPE_3G);
    }

    public static boolean is2GAvailable(Context context){
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected()  && info.getType() == ConnectivityManager.TYPE_MOBILE && getMobileSubType(info.getSubtype()) == MOBILE_TYPE_2G);
    }

    public static boolean is2GOr3GAvailable(Context context){
        NetworkInfo info = getNetworkInfo(context);
        if(info != null && info.isConnected()  && info.getType() == ConnectivityManager.TYPE_MOBILE){
            int mobileType = getMobileSubType(info.getSubtype());
            return mobileType == MOBILE_TYPE_2G || mobileType == MOBILE_TYPE_3G;
        }
        return false;
    }
    
    public static String getNetworkType(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        if (info != null && info.isConnected()) {
            return info.getTypeName();
        }
        return "NONE";
    }

    public static boolean is5GHzWiFiSupported(Context context) {
        //反编译qiezi源码，得知判断这2个值就可知道当前设备是否支持5GHz频段wifi
        return is5GHzBandSupported(context) || isDualBandSupported(context);
    }

    private static boolean is5GHzBandSupported(Context context) {
        if (Build.VERSION.SDK_INT < 21) {
            return false;
        }
        Boolean bool;
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        Boolean valueOf = Boolean.valueOf(false);
        try {
            Class<?>[] clsArr =null;
            Object[] objArr=null;
            Method declaredMethod = wifiManager.getClass().getDeclaredMethod("is5GHzBandSupported", clsArr);
            boolean isAccessible = declaredMethod.isAccessible();
            if (!isAccessible) {
                try {
                    declaredMethod.setAccessible(true);
                } catch (Throwable th) {
                    declaredMethod.setAccessible(isAccessible);
                }
            }
            Object invoke = declaredMethod.invoke(wifiManager, objArr);
            declaredMethod.setAccessible(isAccessible);
            bool = (Boolean) invoke;
        } catch (Exception e) {
            bool = valueOf;
        }
        return bool.booleanValue();
    }

    private static boolean isDualBandSupported(Context context) {
        if (Build.VERSION.SDK_INT < 19) {
            return false;
        }
        Boolean bool;
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        Boolean valueOf = Boolean.valueOf(false);
        try {
            Class<?>[] clsArr =null;
            Object[] objArr=null;
            Method declaredMethod = wifiManager.getClass().getDeclaredMethod("isDualBandSupported", clsArr);
            boolean isAccessible = declaredMethod.isAccessible();
            if (!isAccessible) {
                try {
                    declaredMethod.setAccessible(true);
                } catch (Throwable th) {
                    declaredMethod.setAccessible(isAccessible);
                }
            }
            Object invoke = declaredMethod.invoke(wifiManager, objArr);
            declaredMethod.setAccessible(isAccessible);
            bool = (Boolean) invoke;
        } catch (Exception e) {
            bool = valueOf;
        }
        return bool.booleanValue();
    }

    public static int getVersionCode(Context context) {
        if (versionCode != -1) {
            return versionCode;
        }
        try {
            PackageInfo packInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (NameNotFoundException e) {
        }
        return versionCode;
    }

    private static String channel;
    private static final boolean CHANNEL_IN_ZIP = true;



    
    /**
     * Donald, we define a ZAPYA_VERSION metadata in manifest
     * which will upgrade everytime we deliver software
     * @param context
     * @return
     */
    public static int getZapyaVersion(Context context) {
        if (version != -1) {
            return version;
        }
        String pkg = context.getPackageName();
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(pkg, PackageManager.GET_META_DATA);
            version = info.metaData.getInt("ZAPYA_VERSION");
        } catch (Throwable e) {
            Log.w("Donald", "" + e.getMessage());
        }
        return version;
    }
    
    public static String getZapyaVersionName(Context context) {
        if (versionName != null) {
            return versionName;
        }
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(),
                    0);
            versionName = packInfo.versionName;
        } catch (Exception e) {
        }
        return versionName;
    }
    
    public static int isGooglePlayInstall(Context context) {
        PackageInfo packInfo = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            packInfo = packageManager.getPackageInfo(GOOGLE_PLAY_PKGNAME, 0);
        } catch (Exception e) {
            return 0;
        }
        return packInfo == null ? 0 : 1;
    }

    private static boolean isMockInfoInited = false;
    private static String mockOutLang;



}
