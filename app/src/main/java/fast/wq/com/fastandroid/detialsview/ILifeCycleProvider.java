package fast.wq.com.fastandroid.detialsview;

/**
 * Page作为一个生命周期的提供者，具备生命周期分发功能
 * 在MainActivity中监听Page的生命周期变化，当Page显示的时候，触发MainActivity的onPause，Page隐藏触发MainActivity的onResume
 */

public interface ILifeCycleProvider {

    abstract void onPageShow();
    abstract void onPageResume();
}
