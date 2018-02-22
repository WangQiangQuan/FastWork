package fast.wq.com.manager;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DmBlackWhiteListManager {

	private Set<String> blackSet = Collections.synchronizedSet(new HashSet<String>());
	private Set<String> whiteSet = Collections.synchronizedSet(new HashSet<String>());
	private Set<String> noGameSet = Collections.synchronizedSet(new HashSet<String>());
	
	private static DmBlackWhiteListManager inst;
	
	public static synchronized DmBlackWhiteListManager getInstance() {
		if (inst == null){
			inst = new DmBlackWhiteListManager();
		}
		return inst;
	}
	
	private DmBlackWhiteListManager() {
		init();
	}
	
	private void init(){
		blackSet.add("com.lenovo.anyshare");
		blackSet.add("com.miui.transfer");
		blackSet.add("cn.andouya");
		blackSet.add("cn.xender");
		
		
		whiteSet.add("com.omnivideo.video");
		whiteSet.add("com.ting.ximalaya.tingba.android");
		whiteSet.add("com.dewmobile.wificlient");
		whiteSet.add("com.dewmobile.zapya");
		
		noGameSet.add("com.mt.mtxx.mtxx");
		noGameSet.add("com.sec.pcw");
	}
	
	public boolean isWhite(String key) {
		return whiteSet.contains(key);
	}
	
	public boolean isBlack(String key) {
		return blackSet.contains(key);
	}
	
	public void addWhite(String key) {
		whiteSet.add(key);
	}
	
	public boolean isLargeApp(String key) {
		return noGameSet.contains(key);
	}
	
	public static boolean isFlashTransfer(String key) {
		return ("cn.andouya".equals(key) || "cn.xender".equals(key));
	}
	
	public static boolean isAnyShare(String key) {
		return ("com.lenovo.anyshare".equals(key) || "com.miui.transfer".equals(key));
	}
}
