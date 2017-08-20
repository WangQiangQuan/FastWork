package fast.wq.com.fastandroid;

import java.io.Closeable;
import java.io.IOException;

/**
 * 接口隔离
 */

public class CloseUtils {
    public static void closeQuietiy(Closeable closeable){
        if (closeable != null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
