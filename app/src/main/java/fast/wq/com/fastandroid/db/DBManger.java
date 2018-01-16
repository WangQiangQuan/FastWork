package fast.wq.com.fastandroid.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by wangqiang on 2016/12/15.
 * 数据库 操作工具类
 */
public class DBManger {
    private static SQLiteOpenHelperTest mInstance;

    public static SQLiteOpenHelperTest getInstance(Context mContext) {
        if (mInstance == null) {
            mInstance = new SQLiteOpenHelperTest(mContext);
        }
        return mInstance;
    }

    /**
     * 根据sql语句在数据库中执行语句
     * @param db
     * @param sql
     */
    public  static  void execSQL(SQLiteDatabase db , String sql){

        if (db != null) {
            if (sql == null || sql.length() == 0) {
                db.execSQL(sql);
            }
        }

    }

}
