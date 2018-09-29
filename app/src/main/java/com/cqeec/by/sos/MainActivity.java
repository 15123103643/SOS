package com.cqeec.by.sos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    //引用菜单布局
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    //菜单选选项事件
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id){
            case R.id.emergency_contact:
                Toast.makeText(this,"1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.My_first_aid_card:
                Toast.makeText(this,"2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Rescue_information:
                Toast.makeText(this,"3",Toast.LENGTH_SHORT).show();
                break;
            case R.id.List_for_help:
                Toast.makeText(this,"4",Toast.LENGTH_SHORT).show();
                break;
            case R.id.system_stttings:
                Toast.makeText(this,"5",Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
