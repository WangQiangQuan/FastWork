package fast.wq.com.fastandroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.List;
import java.util.Locale;

/**
 * 1. fix subFragment cannot startActivityForResult bug(google's bug)
 * <br>
 * http://stackoverflow.com/questions/13580075/onactivityresult-not-called-in-new-nested-fragment-api
 * <br>
 * 2. fix bug :https://code.google.com/p/android/issues/detail?id=19917
 * <br>
 *  Issue 19917:	ViewPager NullPointerException when onPause is called from activity and ViewPager has no adapter set.
 * @author yqpan
 *http://www.cnblogs.com/dongweiq/p/5356229.html
 */
public class DmBaseFragment extends Fragment {

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		List<Fragment> fragments = getChildFragmentManager().getFragments();
		if (fragments != null) {
			for (Fragment fragment : fragments) {
				fragment.onActivityResult(requestCode, resultCode, data);
			}
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		//first saving my state, so the bundle won't be empty.
		//https://code.google.com/p/android/issues/detail?id=19917
		outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
		super.onSaveInstanceState(outState);
	}

	/*
         * this method has been override by DmBaseFragment
         * for the reason that this class noted
         */
	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		if(getParentFragment() != null){
			getParentFragment().startActivityForResult(intent, requestCode);
		}else{
			super.startActivityForResult(intent, requestCode);
		}
		
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
//		if (com.dewmobile.library.BuildConfig.ZAPYA_LITE_VERSION) {
//			String p = DmPreferenceManager.getInstance().getString(DmPreferenceManager.LANGUAGE, "");
//			if (p.equalsIgnoreCase(Locale.getDefault().getLanguage())) {
//				return;
//			}
//			DmLanguageActivity.changeLang(getContext());
//		}
	}
	
}
