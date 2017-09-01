package fast.wq.com.fastandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2017/9/1.
 */

public class LazyFragment extends ALazyFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_open_result, container, false);
        //初始化view
        isPrepared = true;
        lazyLoad();
        return super.onCreateView(inflater, container, savedInstanceState);


    }

    @Override
    protected void lazyLoad() {
        if (isCanLoad()) {

        }
    }
}
