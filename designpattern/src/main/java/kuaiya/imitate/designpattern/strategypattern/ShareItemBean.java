package kuaiya.imitate.designpattern.strategypattern;

/**
 * Created by admin on 2018/2/27.
 */

public class ShareItemBean {
    private static final int TYPE_LINK = 0;
    private static final int TYPE_IMAGE = 1;
    private static final int TYPE_TEXT = 2;
    private static final int TYPE_IMAGE_TEXT = 3;


    int type;
    String title;
    String content;
    String imagePath;
    String link;

}
