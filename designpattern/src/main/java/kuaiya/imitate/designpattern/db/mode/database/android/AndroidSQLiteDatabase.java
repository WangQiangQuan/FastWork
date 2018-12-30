package kuaiya.imitate.designpattern.db.mode.database.android;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import kuaiya.imitate.designpattern.db.mode.database.ISQLiteStatement;

/**
 * Created by wangqiang on 2018/12/4.
 */

public class AndroidSQLiteDatabase implements ISQLiteStatement {

    private SQLiteStatement mSQLiteStatement;

    public AndroidSQLiteDatabase(SQLiteStatement mSQLiteStatement) {
        this.mSQLiteStatement = mSQLiteStatement;
    }

    @Override
    public void execute() {

    }

    @Override
    public long executeInsert() {
        return 0;
    }

    @Override
    public long executeUpdateDelete() {
        return 0;
    }

    @Override
    public long simpleQueryForLong() {
        return 0;
    }

    @Override
    public String simpleQueryForString() {
        return null;
    }

    @Override
    public void bindNull(int index) {

    }

    @Override
    public void buidLong(int index, long vaule) {

    }

    @Override
    public void buidDouble(int index, double vaule) {

    }

    @Override
    public void buidString(int index, String vaule) {

    }

    @Override
    public void buidBlob(int index, byte[] value) {

    }

    @Override
    public void clearBindings() {

    }

    @Override
    public void bindAllArgsAsStrings(String... bindArgs) {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
