package com.example.studentdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DBAdapter {
    private Context context;
    private final static String DBNAME="student";
    private final static String TABLENAME="studentinfo";
    private MySQLiteOpenHelper openHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context context) {
        this.context = context;
    }

    public SQLiteDatabase open()throws SQLiteException {
        openHelper=new MySQLiteOpenHelper(context,DBNAME,null,1);
        try {
            db = openHelper.getWritableDatabase();
        }catch (SQLiteException e){

        }
        return db;
    }

    public void close(){
        if(db!=null){
            db.close();
            db=null;
        }
    }

    public long insert(Student student){
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",student.getName());
        contentValues.put("age",student.getAge());
        contentValues.put("height",student.getHeight());
        return db.insert(TABLENAME,null,contentValues);
    }

    public long delOneData(long id){
      return db.delete(TABLENAME,"ID="+id,null);
    }

    public long delAllData(){
        return db.delete(TABLENAME,null,null);
    }

    public long updateOneData(long id,Student student){
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",student.getName());
        contentValues.put("age",student.getAge());
        contentValues.put("height",student.getHeight());
       return db.update(TABLENAME,contentValues,"ID ="+id,null);
    }

    public Student[] queryOneData(long id){
       Cursor cursor=db.query(TABLENAME,null,"ID ="+id,null,null,null,null);
       return ConvertToStudent(cursor);
    }

    public Student[] queryAllData(){
        Cursor cursor=db.query(TABLENAME,null,null,null,null,null,null);
        return ConvertToStudent(cursor);
    }

    public Student[] ConvertToStudent(Cursor cursor){
        int resultcount=cursor.getCount();
        if (resultcount==0||!cursor.moveToFirst()){return  null;}
        Student[] students=new Student[resultcount];
        for(int i=0;i<resultcount;i++){
            int _id=cursor.getInt(0);
            String name=cursor.getString(1);
            int age=cursor.getInt(2);
            float height=cursor.getFloat(3);
            Student student=new Student(_id,name,age,height);
            students[i]=student;
            cursor.moveToNext();
        }
        return students;
    }


    public String dbdisplay(Student[] students){
        String result="";
        if(students==null) return "数据库无数据";
        for(int i=0;i<students.length;i++){
           result+= students[i].toString()+"\n";
        }
        return result;
    }

}
