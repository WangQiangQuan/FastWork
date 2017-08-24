package fast.wq.com.fastandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.List;

/**
 * 软键盘
 */

public class InputUtil {
    /**
     * 当点击其他View时隐藏软键盘
     * @param activity
     * @param ev
     * @param excludeViews  点击这些View不会触发隐藏软键盘动作
     */
    public static void hideInputWhenTouchOtherView(Activity activity, MotionEvent ev, List<View> excludeViews){


        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            if (excludeViews != null && !excludeViews.isEmpty()){
                for (View excludeView : excludeViews) {
                    if (isTouchView(excludeView, ev)){
                        return;
                    }
                }
            }
            View v = activity.getCurrentFocus();
            if (isShouldHideInput(v, ev)){
                InputMethodManager inputMethodManager = (InputMethodManager)
                        activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null){
                    inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }

        }
    }


    private static boolean isTouchView(View view, MotionEvent event){
        if (view == null || event == null){
            return false;
        }
        int[] leftTop = {0, 0};
        view.getLocationInWindow(leftTop);
        int left = leftTop[0];
        int top = leftTop[1];
        int bottom = top + view.getHeight();
        int right = left + view.getWidth();
        return event.getRawX() > left && event.getRawX() < right
                && event.getRawY() > top && event.getRawY() < bottom;
    }

    private static boolean isShouldHideInput(View v, MotionEvent event){
        if (v != null && (v instanceof EditText)){
            return !isTouchView(v, event);
        }
        return false;
    }
    //
    private InputMethodManager manager;
    public void init(Activity mActivity){
        manager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
    }
    private void showKeyboard(EditText etSendMessage){
        etSendMessage.requestFocus();
        manager.showSoftInput(etSendMessage, 0);
    }
    private void hideKeyboard(Activity mActivity, EditText etSendMessage) {
        if (mActivity.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (mActivity.getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(mActivity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(etSendMessage.getWindowToken(), 0); //强制隐藏键盘
        }
    }
}
