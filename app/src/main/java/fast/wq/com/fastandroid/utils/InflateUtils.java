package fast.wq.com.fastandroid.utils;

/**
 *
 *
 */

public class InflateUtils {
    /**
     * public View inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot)
     * 1.1 root不为null，attachToRoot为true
     *  正常
     * 1.2 root不为null，attachToRoot为false
     * 正常
     * 表示不将第一个参数所指定的View添加到root中，
     * 1.3 root为null
     * 当root为null时，不论attachToRoot为true还是为false，显示效果都是一样的
     * 它的根节点的宽高属性会失效
     */
}
