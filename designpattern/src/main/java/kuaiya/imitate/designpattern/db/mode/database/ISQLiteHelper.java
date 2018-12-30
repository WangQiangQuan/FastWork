package kuaiya.imitate.designpattern.db.mode.database;

/**
 * Created by wangqiang on 2018/12/4.
 */

public interface ISQLiteHelper {

    void close();

    void registerDao(AbstractDao dao, Object daoInfo);

    void createDataBase();

    void registerDb(AbstractDao dao);
}
