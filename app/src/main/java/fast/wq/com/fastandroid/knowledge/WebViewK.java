package fast.wq.com.fastandroid.knowledge;

/**
 * 1 API 16 之前webview。addjavascriptInterface方法。
 * 2 webview在其他容器销毁的时候 ，
 * Linerlayout中remove webview 然后在调用webview的removeallviews 和 destory
 * 3Jsbridge
 * 4 webviewClient.onPageFinished -> webChroeClient.onProgressChanged
 * webview跳转多次onPageFinished会调用多次
 * 5后台耗电
 * onDestory中直接System.exit 虚拟机直接关闭
 * 6硬件加速
 * 容易出现白块，闪烁，暂时关闭硬件加速
 */

public class WebViewK {

}
