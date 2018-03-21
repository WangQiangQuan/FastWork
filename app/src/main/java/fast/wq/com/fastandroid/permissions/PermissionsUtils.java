package fast.wq.com.fastandroid.permissions;

import android.os.Build;

/**
 * 兼容处理小米权限问题
 * http://blog.csdn.net/qq_30167925/article/details/53955026
 */

public class PermissionsUtils {

    public static boolean isNeedPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            return true;
        }
        return false;
    }
}
/**
 备注！！！
 1
 （1）checkSelfPermission：检查是否拥有这个权限
 1
 （2）requestPermissions：请求权限，一般会弹出一个系统对话框，询问用户是否开启这个权限。
 1
 （3）shouldShowRequestPermissionRationale：Android原生系统中，如果第二次弹出权限申请的对话框，会出现“以后不再弹出”的提示框，如果用户勾选了，你再申请权限，
 则shouldShowRequestPermissionRationale返回true，意思是说要给用户一个 解释，告诉用户为什么要这个权限。然而，在实际开发中，需要注意的是，很多手机对原生     系统做了修改，比如小米，小米4的6.0的shouldShowRequestPermissionRationale 就一直返回false，而且在申请权限时，如果用户选择了拒绝，则不会再弹出对话框了
 。。。。 所以说这个地方有坑，我的解决方法是，在回调里面处理，如果用户拒绝了这个权限，则打开本应用信息界面，由用户自己手动开启这个权限。
 1
 （4）每个应用都有自己的权限管理界面，里面有本应用申请的权限以及各种状态，即使用户已经同意了你申请的权限，他也随时可以关闭
 */
