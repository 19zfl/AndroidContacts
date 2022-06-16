package com.example.studentdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    //实现数据库的创建
    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {  //添加建表的代码
        String sql="create table studentinfo(ID integer primary key autoincrement,name text not null,age integer,height float);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql="drop table if exists student;";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
