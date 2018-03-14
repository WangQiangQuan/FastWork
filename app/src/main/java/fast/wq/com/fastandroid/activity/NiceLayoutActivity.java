package fast.wq.com.fastandroid.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fast.wq.com.fastandroid.R;

/**
 * ConstraintLayout的相对位置的设置
 * 相对位置属性：layout_constraint[自身控件位置]_[目标控件位置]="[目标控件ID]"，如果id是父布局的id，可以使用parent。如：
 * layout_constraintLeft_toLeftOf=”@id/…”：控件自身的左边和目标控件的左边对齐。
 *
 * app:layout_constraintBaseline_toBaselineOf=”@id/btn”：文字的底部线对齐
 * 想要居中
 app:layout_constraintBottom_toBottomOf="parent"

 app:layout_constraintTop_toTopOf="parent"
 app:layout_constraintLeft_toLeftOf="parent"
 app:layout_constraintRight_toRightOf="parent"

 http://blog.csdn.net/qq_33689414/article/details/75103731
 */
public class NiceLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_nice_layout);
        setContentView(R.layout.item_costraint_story);
    }
}
