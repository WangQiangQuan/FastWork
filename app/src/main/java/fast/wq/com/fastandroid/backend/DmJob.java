package fast.wq.com.fastandroid.backend;

/**
 * Created by admin on 2018/2/5.
 */

public class DmJob {
    public int netMode;
    public int action;
    public String uri;
    public String body;
    public boolean newApi;
    public long _id;
    public int count;

    public static final int	ACTION_POST = 0;
    public static final int	ACTION_GET = 1;
    public static final int ACTION_PUT = 2;

    private boolean isMissingUserId = false;

    public static final int	NET_WIFI = 0;
    public static final int	NET_ANY = 1;
}
