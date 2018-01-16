package fast.wq.com.fastandroid.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import fast.wq.com.fastandroid.R;


public class DbActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        mHelper = DBManger.getInstance(this);
    }

    SQLiteOpenHelperTest mHelper = null;
    SQLiteDatabase db = null;
    //data中创建包名
    public void createDb(View view) {
        //这俩都是可读可写，当异常的时候有区别
        SQLiteDatabase db = null;
        mHelper.getReadableDatabase();
        mHelper.getWritableDatabase();
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_insert:
                db = mHelper.getWritableDatabase();
                String sql = "insert into person values(1,'zhangsan2',20)";
                DBManger.execSQL(db, sql);
                String sql2 = "insert into person values(2,'lisi2',25)";
                DBManger.execSQL(db, sql2);
                db.close();
                break;
            case R.id.btn_update:
                db = mHelper.getWritableDatabase();
                String updateSql = "update person set name = 'xiaoming' where id = 1";
                DBManger.execSQL(db, updateSql);
                db.close();
                break;
            case R.id.btn_delete:
                db = mHelper.getWritableDatabase();
                String deleteSql = "delete person set where id = 2";
                DBManger.execSQL(db, deleteSql);
                db.close();
                break;
        }
    }
    public void btn_insertApi(View view) {
        switch (view.getId()) {
            case R.id.btn_insertApi:
                db = mHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(Constant._ID, 3);
                values.put(Constant.NAME, "张三");
                values.put(Constant.AGE, 13);
                long colum = db.insert(Constant.TABLE_NAME, null, values);
                if (colum > 0) {
                }
                db.close();
                break;
            case R.id.btn_updateApi:
                db = mHelper.getWritableDatabase();
                ContentValues updatevalues = new ContentValues();
                // int count =  db.update(Constant.TABLE_NAME,updatevalues , Constant._ID + "=3",null);
                int count = db.update(Constant.TABLE_NAME, updatevalues, Constant._ID + "=?", new String[]{"3"});
                if (count > 0) {
                }
                db.close();
                break;
            case R.id.btn_deleteApi:
                db = mHelper.getWritableDatabase();
                int countdelete = db.delete(Constant.TABLE_NAME, Constant._ID + "=?",new String[]{"3"});
                if (countdelete > 0) {
                }
                db.close();
                break;
        }
    }
}
