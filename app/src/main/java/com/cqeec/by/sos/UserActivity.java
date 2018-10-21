package com.cqeec.by.sos;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.litepal.LitePal;

public class UserActivity extends AppCompatActivity {
    private Button bback ,bsave;
    private EditText user_name,user_sfzh,user_jtdz,user_xx,user_gmfy,user_zdy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // LitePal.initialize(this);
        setContentView(R.layout.activity_user);
        ActionBar();
        Edit();
        bsave();

    }
    public void Edit(){
        user_name =findViewById(R.id.username);
        user_sfzh=findViewById(R.id.sfzh);
        user_jtdz=findViewById(R.id.jtdz);
        user_xx=findViewById(R.id.xx);
        user_gmfy=findViewById(R.id.gmfy);
        user_zdy=findViewById(R.id.zdy);
    }
    public void  bsave(){
        bsave = findViewById(R.id.bsave);
        bsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserDB db = new UserDB();
                db.setU_name(user_name.getText().toString());
                db.setU_sfzh(user_sfzh.getText().toString());
                db.setU_jtdz(user_jtdz.getText().toString());
                db.setU_xx(user_xx.getText().toString());
                db.setU_gmfy(user_gmfy.getText().toString());
                db.setU_zdy(user_zdy.getText().toString());
                Log.i("用户数据",user_name.getText().toString());
                db.save();
            }
        });

    }
    public void ActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();

        }
    }
}
