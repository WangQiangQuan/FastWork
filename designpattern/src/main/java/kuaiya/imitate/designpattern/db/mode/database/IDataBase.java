package kuaiya.imitate.designpattern.db.mode.database;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by wangqiang on 2018/12/4.
 */

public interface IDataBase {
    ISQLiteStatement compileStatement(String sql);

    void beginTransaction();

    void setTransactionSuccessful();

    void endTransaction();

    void close();

    void execSQL(String sql);

    Cursor query(String table, String[] columns, String selection
            , String[] selectionArgs, String groupBy, String having, String orderBy);

    Cursor query(String table, String[] columns, String selection
            , String[] selectionArgs, String groupBy, String having, String orderBy, String limit);

    int update(String table, ContentValues values, String whereClause, String whereArgs);

    int delete(String table, String whereClause, String[] whereArgs);
}
