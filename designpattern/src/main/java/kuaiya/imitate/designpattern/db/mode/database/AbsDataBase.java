package kuaiya.imitate.designpattern.db.mode.database;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangqiang on 2018/12/6.
 */

public class AbsDataBase {
    private static final String TAG = "AbsDataBase";

    private Map<Class<? extends AbstractDao>, AbstractDao> mDaoMap = new HashMap<>();
    private ISQLiteHelper mSqLiteHelper;

    public AbsDataBase(Context appContext, String dbName) {
//        mSqLiteHelper
    }

    public void registerDao(AbstractDao dao ,Object entry){
        mDaoMap.put(dao.getClass(),dao);
        mSqLiteHelper.registerDao(dao,entry);
    }

    public void createDataBase(){
        mSqLiteHelper.createDataBase();

        for (AbstractDao dao: mDaoMap.values()){
            mSqLiteHelper.registerDb(dao);
        }
    }

    public void destroy(){
        try {
            mSqLiteHelper.close();
        }catch (Exception e){

        }

    }

    public <T extends AbstractDao> T getDao(Class<T> clazz) {
        return (T) mDaoMap.get(clazz);
    }
}
