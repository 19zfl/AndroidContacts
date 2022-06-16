package com.example.studentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etname,etage,etheight,etid;
    private Button addbtn,delbtn,updatebtn,querybtn;
    private TextView display;
    private DBAdapter dbAdapter;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etname=findViewById(R.id.et_name);
        etage=findViewById(R.id.et_age);
        etheight=findViewById(R.id.et_height);
        etid=findViewById(R.id.et_id);

        addbtn=findViewById(R.id.button1);
        delbtn=findViewById(R.id.button2);
        updatebtn=findViewById(R.id.button3);
        querybtn=findViewById(R.id.button4);

        display=findViewById(R.id.tv_display);

        dbAdapter=new DBAdapter(this);
        //打开数据库
        db=dbAdapter.open();


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=etname.getText().toString().trim();
                String age=etage.getText().toString().trim();
                String height=etheight.getText().toString().trim();

                Student addstudent=new Student(0,name,Integer.valueOf(age),Float.valueOf(height));
                dbAdapter.insert(addstudent);
                Log.i("demo","添加成功!");
                display.setText(dbAdapter.dbdisplay(dbAdapter.queryAllData()));

                etname.setText(""); etage.setText("");  etheight.setText("");

                Toast.makeText(MainActivity.this,"添加成功!",Toast.LENGTH_SHORT).show();
            }
        });

        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etid.getText().toString().equals("")){
                    dbAdapter.delAllData();
                }else
                dbAdapter.delOneData(Integer.valueOf(etid.getText().toString()));
                Log.i("demo","删除成功!"+etid.getText().toString());
                display.setText(dbAdapter.dbdisplay(dbAdapter.queryAllData()));
                Toast.makeText(MainActivity.this,"删除成功!",Toast.LENGTH_SHORT).show();
            }
        });

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=etname.getText().toString().trim();
                String age=etage.getText().toString().trim();
                String height=etheight.getText().toString().trim();

                Student upstudent=new Student(0,name,Integer.valueOf(age),Float.valueOf(height));

                dbAdapter.updateOneData(Integer.valueOf(etid.getText().toString()),upstudent);
                Log.i("demo","修改成功!"+etid.getText().toString());
                display.setText(dbAdapter.dbdisplay(dbAdapter.queryAllData()));
                Toast.makeText(MainActivity.this,"修改成功!",Toast.LENGTH_SHORT).show();
            }
        });

        querybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etid.getText().toString().equals("")){
                    display.setText(dbAdapter.dbdisplay(dbAdapter.queryAllData()));
                }
                else{
                    Student[] onestudent=dbAdapter.queryOneData(Integer.valueOf(etid.getText().toString()));
                    display.setText(dbAdapter.dbdisplay(onestudent));
                }
                Log.i("demo","查询成功!"+etid.getText().toString());
                Toast.makeText(MainActivity.this,"查询成功!",Toast.LENGTH_SHORT).show();
            }
        });

    }


        // activity生命周期的销毁回调函数
    public void onDestroy(){
        super.onDestroy();
        dbAdapter.close();
    }

}
