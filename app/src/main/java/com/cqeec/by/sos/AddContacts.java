package com.cqeec.by.sos;

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
    xm = findViewById(R.id.xm);
    hm = findViewById(R.id.hm);
    yx = findViewById(R.id.yx);
    btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        }
        }
