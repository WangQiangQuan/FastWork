package fast.wq.com.fastandroid.download;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqiang on 2018/2/18.
 */

public class ThreadDaoImpl implements ThreadDao {

    private DbHelper mHelper = null;

    public ThreadDaoImpl(Context mContext) {
        this.mHelper = new DbHelper(mContext);
    }

    @Override

    public void insertThread(ThreadInfo mThreadinfo) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("insert into thread_info(thread_id,url,start,end,finished) values(?,?,?,?,?)",
                new Object[]{mThreadinfo.getId(), mThreadinfo.getUrl(), mThreadinfo.getStart(), mThreadinfo.getEnd(), mThreadinfo.getFinished()});
        db.close();
    }

    @Override
    public void deleteThread(String url, int thread_id) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("delete from thread_info where url = ? and thread_id = ?",
                new Object[]{url, thread_id});
        db.close();
    }

    @Override
    public void updateThread(String url, int thread_id, int finished) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL("update  thread_info set finished = ? where url = ? and thread_id=? ",
                new Object[]{finished, url, thread_id});
        db.close();
    }

    @Override
    public List<ThreadInfo> getThreads(String url) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        List<ThreadInfo> mlist = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from thread_info where url = ?", new String[]{url});
        while (cursor.moveToNext()) {
            ThreadInfo bean = new ThreadInfo();
            bean.setId(cursor.getInt(cursor.getColumnIndex("thread_id")));
            bean.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            bean.setStart(cursor.getInt(cursor.getColumnIndex("start")));
            bean.setEnd(cursor.getInt(cursor.getColumnIndex("end")));
            bean.setFinished(cursor.getInt(cursor.getColumnIndex("finished")));
            mlist.add(bean);
        }
        cursor.close();
        db.close();
        return mlist;
    }

    @Override
    public boolean isExiste(String url, int thread_id) {
        boolean exists = false;
        SQLiteDatabase db = mHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from thread_info where url = ? and thread_id = ?", new String[]{url,thread_id+""});
        exists = cursor.moveToNext();
        cursor.close();
        db.close();
        return exists;
    }
}
