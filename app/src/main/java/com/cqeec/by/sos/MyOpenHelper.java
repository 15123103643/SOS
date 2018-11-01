package com.cqeec.by.sos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
    public static final int VERSION=1;
    public static final String  CREATE_TABLE_TEST="create table User(_id integer primary key autoincrement,name,phone,email)";
    public MyOpenHelper( Context context) {
        super(context, "User.db", null, VERSION);
    }

    //    当数据库文件不存在，创建数据库，并且第一次使用
    @Override
    public void onCreate(SQLiteDatabase db) {
//创建表
        db.execSQL(CREATE_TABLE_TEST);
    }
    // 版本更新
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
