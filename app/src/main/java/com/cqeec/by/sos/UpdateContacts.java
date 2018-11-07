package com.cqeec.by.sos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateContacts extends AppCompatActivity {
    private EditText name;
    private EditText phone;
    private EditText email;
    private Button btn,btn1;
    private MyOpenHelper helper;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_contacts);
        helper=new MyOpenHelper(this);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        email=(EditText)findViewById(R.id.email);
//        cursor=helper.getReadableDatabase().query("User",null,null,null,null,null,null);
        show();
        btn=(Button)findViewById(R.id.btn);
        btn1=(Button)findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
    @Override
        public void onClick(View v) {
        finish();
    }
});
    }


    public void Update() {
        Bundle bundle = this.getIntent().getExtras();
        /*获取Bundle中的数据，注意类型和key*/
        String id = bundle.getString("Id");
//        修改的列级新值
        SQLiteDatabase db=helper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name.getText().toString().trim());
        values.put("phone",phone.getText().toString().trim());
        values.put("email",email.getText().toString().trim());
      int line=db.update("User",values,"_id=?",new String[]{id});
        if(line>0){
            Toast.makeText(UpdateContacts.this,"联系人修改成功", Toast.LENGTH_SHORT).show();
        }
        Intent intent =new Intent(UpdateContacts.this,ContactsActivity.class);
        //启动
        startActivity(intent);
    }
    public  void show(){
        /*获取Intent中的Bundle对象*/
        Bundle bundle = this.getIntent().getExtras();
        /*获取Bundle中的数据，注意类型和key*/
        String Uname = bundle.getString("Name");
        String Uphone = bundle.getString("Phone");
        String Uemail = bundle.getString("Email");
        name.setText(Uname);
        phone.setText(Uphone);
        email.setText(Uemail);

    }

//    public void Delete() {
//        Bundle bundle = this.getIntent().getExtras();
//        /*获取Bundle中的数据，注意类型和key*/
//        String id = bundle.getString("Id");
//        SQLiteDatabase db=helper.getReadableDatabase();
//        int line=db.delete("User","_id=?",new String[]{id});
//        if(line>0){
//            Toast.makeText(UpdateContacts.this,"数据删除成功", Toast.LENGTH_SHORT).show();
//        }
//        db.close();
//        Intent intent =new Intent(UpdateContacts.this,ContactsActivity.class);
//        //启动
//        startActivity(intent);
//    }

}