package fast.wq.com.fastandroid.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by wangqiang on 2016/12/15.
 * SQLiteOpenHelper --------->帮助类
 * onCreate         ---------> 创建方法
 * onUpgrade        --------->  数据库更新
 * open             --------->   打开
 *
 *
 SQLite Expert Professional是一款可视化的数据库管理工具
 */

public class SQLiteOpenHelperTest extends SQLiteOpenHelper {
    //帮助类
    public SQLiteOpenHelperTest(Context context){
        super(context,Constant.DATABASE_NAME,null,Constant.DATABASE_VERSION);
    }
    /**
     *
     * @param context
     * @param name
     * @param factory    游标工厂
     * @param version    >=1
     */
    public SQLiteOpenHelperTest(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    /**
     * 当 数据库创建时回调函数
     * @param db 数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
//        KLog.i();
        //创建表
        String sql = "create table "+Constant.TABLE_NAME
                +" ("+ Constant._ID+" Integer primary key,"+ Constant.NAME+" varchar(10) ,"+Constant.AGE+" Integer)";
        db.execSQL(sql);
    }
    /**
     * 当版本更新
     * @param db 数据库对象
     * @param oldVersion 旧本
     * @param newVersion 新
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 当数据库打卡回调
     * @param db
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
