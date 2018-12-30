package kuaiya.imitate.designpattern.db.mode.database;

/**
 * Created by wangqiang on 2018/12/6.
 */

public abstract class AbstractDao {
    protected IDataBase mWritableDb;
    public AbstractDao(){

    }
    public abstract void createTable( IDataBase db);

    public void setWritableDb(IDataBase writableDb){
        mWritableDb = writableDb;
    }

    public abstract Class<? extends TableEntry> getEntry();
}
