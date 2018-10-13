package com.cqeec.by.sos;

import android.os.Bundle;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.cqeec.by.sos.Const;

import static com.cqeec.by.sos.Const.ADDRESS;


public class MainActivity extends AppCompatActivity {
    //绑定空间
    private MapView mMapView = null;

    //绑定空间
    private TextView position_textView;
    //Handler 定时器的使用
    Handler handler = new Handler();
    Runnable update_thread =new Runnable() {
        @Override
        public void run() {

            position_textView = findViewById(R.id.position_textView);
            position_textView.setText(ADDRESS);
            //秒级执行
            handler.postDelayed(update_thread,1000);

        }
    };


    //--------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        Toolbar toolbar =findViewById(R.id.toolbar);
        BaiduDw baiduDw = new BaiduDw(MainActivity.this);
        handler.post(update_thread);
        baiduDw.doLocation();//开启定位
        baiduDw.mLocationClient.restart();//开始定位,异常情况重启定位



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
