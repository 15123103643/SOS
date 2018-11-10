package com.cqeec.by.sos;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
        onResume();
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
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               TextView xm = (TextView) view.findViewById(R.id.name);
                String  Uname= xm.getText().toString();
                TextView hm = (TextView) view.findViewById(R.id.phone);
                String  Uphone= hm.getText().toString();
                TextView yx = (TextView) view.findViewById(R.id.email);
                String  Uemail= yx.getText().toString();
                TextView dz = (TextView) view.findViewById(R.id.id);
                final    String Uid= dz.getText().toString();
                final    Intent intent = new Intent(ContactsActivity.this, UpdateContacts.class);
                /* 通过Bundle对象存储需要传递的数据 */
                final    Bundle bundle = new Bundle();
                /*字符、字符串、布尔、字节数组、浮点数等等，都可以传*/
                bundle.putString("Name", Uname);
                bundle.putString("Phone", Uphone);
                bundle.putString("Email", Uemail);
                bundle.putString("Id", Uid);
                    //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                    AlertDialog.Builder builder = new AlertDialog.Builder(ContactsActivity.this);
                    //    设置Title的图标
                    builder.setIcon(R.drawable.ic_launcher_background);
                    //    设置Title的内容
                    builder.setTitle("操作");
                    //    设置Content来显示一个信息
                    builder.setMessage("选择你的操作");
//
                    //    设置一个PositiveButton
                    builder.setPositiveButton("修改", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
//                            Toast.makeText(ContactsActivity.this, "positive: " + which, Toast.LENGTH_SHORT).show();
                            /*把bundle对象assign给Intent*/
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    //    设置一个NegativeButton
                    builder.setNegativeButton("删除", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
//                            Toast.makeText(ContactsActivity.this, "negative: " + which, Toast.LENGTH_SHORT).show();
                            /*获取Bundle中的数据，注意类型和key*/
                            String id = Uid;
                            SQLiteDatabase db=helper.getReadableDatabase();
                            int line=db.delete("User","_id=?",new String[]{id});
                            if(line>0){
                                Toast.makeText(ContactsActivity.this,"数据删除成功", Toast.LENGTH_SHORT).show();
                            }
                            db.close();
                            onResume();

                        }
                    });
                    //    设置一个NeutralButton
                    builder.setNeutralButton("忽略", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
//                            Toast.makeText(ContactsActivity.this, "neutral: " + which, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                    //    显示出该对话框
                    builder.show();


            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ContactsActivity.this, "长按事件 ", Toast.LENGTH_SHORT).show();
                TextView dhhm = (TextView) view.findViewById(R.id.phone);
                String  Uphone= dhhm.getText().toString();
                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+Uphone));
                startActivity(intent);
                return true;
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
                cur,new String[]{"name","_id","phone","email"},
                new int[]{R.id.name,R.id.id,R.id.phone,R.id.email}
        );
        list.setAdapter(adapter);
    }
//    public void Delete() {
//        Bundle bundle = this.getIntent().getExtras();
//        /*获取Bundle中的数据，注意类型和key*/
//        String id = bundle.getString("Id");
//        SQLiteDatabase db=helper.getReadableDatabase();
//        int line=db.delete("User","_id=?",new String[]{id});
//        if(line>0){
//            Toast.makeText(ContactsActivity.this,"数据删除成功", Toast.LENGTH_SHORT).show();
//        }
//        db.close();
//        Intent intent =new Intent(ContactsActivity.this,ContactsActivity.class);
//        //启动
//        startActivity(intent);
//    }
protected void onResume() {
    super.onResume();
    queryshow();//刷新数据
}

}