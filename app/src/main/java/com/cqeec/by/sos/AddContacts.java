package com.cqeec.by.sos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;

public class AddContacts extends AppCompatActivity {
    private EditText name;
    private EditText phone;
    private EditText email;
    private Button btn,btn1;
    private MyOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contacts);
        helper=new MyOpenHelper(this);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.email);
        btn=(Button)findViewById(R.id.btn);
        btn1=(Button)findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void Save() {

        String Uname=name.getText().toString().trim();
        String Uphone=phone.getText().toString().trim();
        String Uemail=email.getText().toString().trim();
        if(Uemail.equals("") || Uphone.equals("") || Uname.equals("")){
            Toast.makeText(AddContacts.this, "联系人信息请填完整",Toast.LENGTH_LONG).show();
        }else{
        SQLiteDatabase db=helper.getReadableDatabase();
//
//        插入数据(表名，空值列名，对象)
        ContentValues values=new ContentValues();
        values.put("name",Uname);
        values.put("phone",Uphone);
        values.put("email",Uemail);

        long rowId=db.insert( "User",null,values);
        if(rowId!=-1){
            Toast.makeText(this,"数据添加成功",Toast.LENGTH_LONG).show();
        }
        db.close();
//        Intent intent =new Intent(AddContacts.this,ContactsActivity.class);
//        //启动
//        startActivity(intent);
            finish();
    }
    }
}