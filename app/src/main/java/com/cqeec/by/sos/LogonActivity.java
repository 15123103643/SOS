package com.cqeec.by.sos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LogonActivity extends AppCompatActivity {
    private TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);
        login();


    }

    private void login() {
        login = findViewById(R.id.tv_loginactivity_register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogonActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
