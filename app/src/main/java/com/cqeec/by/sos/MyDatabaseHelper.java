package com.cqeec.by.sos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String Create_User="create table user(" +
             "id integer primary key autoincrement," +
            "name text," +
            "sfzh text," +
            "jtzz text," +
            "xx text," +
            "gmfy text)";

    private Context mContext;
    public MyDatabaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context, name,factory,version);
        mContext = context;
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_User);
        Toast.makeText( mContext,"创建用户数据",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
