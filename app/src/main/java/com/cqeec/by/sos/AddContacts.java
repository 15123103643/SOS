package com.cqeec.by.sos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddContacts extends AppCompatActivity {
private TextView xm;
private TextView hm;
private TextView yx;
private Button btn;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contacts);
        xm=(TextView)findViewById(R.id.xm);
        hm=(TextView)findViewById(R.id.hm);
        yx=(TextView)findViewById(R.id.yx);
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        }
        }
