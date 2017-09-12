package fast.wq.com.fastandroid.view;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by admin on 2017/9/4.
 */

public class TabSlideView extends HorizontalScrollView {
    public TabSlideView(Context context) {
        this(context, null);
    }

    public TabSlideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabSlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private LinearLayout tabsContainer;

    private void init(Context context) {
        tabsContainer = new LinearLayout(context);
        tabsContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabsContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(tabsContainer);
    }

    //save

    /**
     * http://www.bubuko.com/infodetail-828889.html
     * @return
     */

    //我主动存储父类与子view的状态到独立的SparseArray中。
    @Override
    protected Parcelable onSaveInstanceState() {
//        return super.onSaveInstanceState();
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.childrenStates = new SparseArray();
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).saveHierarchyState(ss.childrenStates);
        }
        return ss;
    }

    //主动从保存期间创建的SparseArray中恢复父类和子view的状态。
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
//        super.onRestoreInstanceState(state);
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).restoreHierarchyState(ss.childrenStates);
        }
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        //dispatchFreezeSelfOnly()方法可以告诉安卓只保存viewGroup的状态
        dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    static class SavedState extends BaseSavedState {
        SparseArray childrenStates;

        public SavedState(Parcel source) {
            super(source);
        }

        private SavedState(Parcel source, ClassLoader loader) {
            super(source, loader);
            childrenStates = source.readSparseArray(loader);
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeSparseArray(childrenStates);
        }

        public static final ClassLoaderCreator<SavedState> CREATOR
                = new ClassLoaderCreator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel source, ClassLoader loader) {
                return new SavedState(source, loader);
            }

            @Override
            public SavedState createFromParcel(Parcel source) {
                return createFromParcel(null);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
