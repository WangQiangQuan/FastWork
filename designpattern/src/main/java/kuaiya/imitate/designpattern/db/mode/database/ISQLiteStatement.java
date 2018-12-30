package kuaiya.imitate.designpattern.db.mode.database;

/**
 * Created by wangqiang on 2018/12/4.
 */

public interface ISQLiteStatement {
    void execute();

    long executeInsert();

    long executeUpdateDelete();

    long simpleQueryForLong();

    String simpleQueryForString();

    void bindNull(int index);

    void buidLong(int index, long vaule);

    void buidDouble(int index, double vaule);

    void buidString(int index, String vaule);

    void buidBlob(int index, byte[] value);

    void clearBindings();

    void bindAllArgsAsStrings(String... bindArgs);

    void close();

    boolean isClosed();
}
