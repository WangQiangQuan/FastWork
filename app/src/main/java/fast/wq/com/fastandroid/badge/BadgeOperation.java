package fast.wq.com.fastandroid.badge;

public interface BadgeOperation {
	void registerBadgeChangeListener(BadgeChangedListener listener);
	void unregisterBadgeChangeListener(BadgeChangedListener listener);
	void destroy();
}
