package com.cqeec.by.sos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContactsActivity  extends AppCompatActivity {
    private Button btn;
    private ListView list;
    private MyOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        helper=new MyOpenHelper(this);
        list=(ListView)findViewById(R.id.list);
        queryshow();
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 给bnt1添加点击响应事件
                Intent intent =new Intent(ContactsActivity.this,AddContacts.class);
                //启动
                startActivity(intent);
            }
        });
    }

    private void queryshow() {
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cur= db.query(
                "User",
                null,
                null,
                null,
                null,
                null,
                null);
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(
                this,
                R.layout.contacts,
                cur,new String[]{"name","phone","email"},
                new int[]{R.id.name,R.id.phone,R.id.email}
        );
        list.setAdapter(adapter);
    }

}