package fast.wq.com.fastandroid.bean;

import java.util.ArrayList;

/**
 */

public class BaiduADBean {
    private String pkg ;
    private String url;
    private ArrayList<String> noticeUrls;//曝光
    private ArrayList<String> clickUrls;//点击
    private ArrayList<String> dUrls; //下载完成
    private ArrayList<String> iUrls;//安装完成
    private ArrayList<String> aUrls;//激活

    public BaiduADBean(String pkg, String url, ArrayList<String> dUrls , ArrayList<String> iUrls, ArrayList<String> aUrls){
        this.pkg = pkg;
        this.url = url;
        this.dUrls = dUrls;
        this.iUrls = iUrls;
        this.aUrls = aUrls;
    }
    public BaiduADBean(String pkg, ArrayList<String> noticeUrls, ArrayList<String> clickUrls, ArrayList<String> dUrls , ArrayList<String> iUrls, ArrayList<String> aUrls){
        this.pkg = pkg;
        this.noticeUrls = noticeUrls;
        this.clickUrls = clickUrls;
        this.dUrls = dUrls;
        this.iUrls = iUrls;
        this.aUrls = aUrls;
    }
    public String getPkgByUrl(String url){
        if (this.url.equals(url)){
            return pkg;
        }
        return null;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public ArrayList<String> getNoticeUrls() {
        return noticeUrls;
    }

    public void setNoticeUrls(ArrayList<String> noticeUrls) {
        this.noticeUrls = noticeUrls;
    }

    public ArrayList<String> getClickUrls() {
        return clickUrls;
    }

    public void setClickUrls(ArrayList<String> clickUrls) {
        this.clickUrls = clickUrls;
    }

    public ArrayList<String> getdUrls() {
        return dUrls;
    }

    public void setdUrls(ArrayList<String> dUrls) {
        this.dUrls = dUrls;
    }

    public ArrayList<String> getiUrls() {
        return iUrls;
    }

    public void setiUrls(ArrayList<String> iUrls) {
        this.iUrls = iUrls;
    }

    public ArrayList<String> getaUrls() {
        return aUrls;
    }

    public void setaUrls(ArrayList<String> aUrls) {
        this.aUrls = aUrls;
    }
}
