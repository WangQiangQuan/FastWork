package fast.wq.com.fastandroid.download;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String db_name = "download.db";
    private static final int version = 1;
    private static final String sql_create = "create table thread_info(_id integer primary key autoincrement" +
            "thread_id integer ,url text ,start integer ,end integer,finished integer) ";
    private static final String drop = "drop table if exists thread_info";
    public DbHelper(Context context) {
        super(context, db_name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(drop);
        sqLiteDatabase.execSQL(sql_create);
    }
}
