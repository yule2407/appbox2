package cn.com.hewoyi.appbox;


import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;
    private int date;




    //表存在则不创建（如果出现重复创建会报错）
    private static final String INSTALLED = "create table if not exists installed(" +
            "_id integer primary key autoincrement, " +
            "packagename text)";



    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
        //date = version;//作为表名...
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

       // db.execSQL(tableName(date));
        db.execSQL(INSTALLED);
        //Log.i("DBHelper", tableName(date));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //存在旧表则删除
        //db.execSQL("drop table if exists list_" + oldVersion);
       // db.execSQL(tableName(newVersion));

        Log.i("DBHelper","onUpgrade-->newVersion:"+newVersion+"-->oldVersion:"+oldVersion);
    }

}

