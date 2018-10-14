package com.cqeec.by.sos;

import android.os.Bundle;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;



public class MainActivity extends AppCompatActivity {

    //地图控件
    private MapView mapView = null;
    //百度地图
    public BaiduMap baiduMap;
    //文本控件
    private TextView position_textView;
    //p

    //--------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        Toolbar toolbar =findViewById(R.id.toolbar);

        initView();
        initMap();


    }


    /**
     * 初始化控件
     */
    public void initView(){

        mapView = (MapView)findViewById(R.id.bmapView);
    }

    /**
     * 初始化地图
     */
    public void initMap(){
        //得到地图实例
        baiduMap = mapView.getMap();
        /*
        设置地图类型
         */
        //普通地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //卫星地图
        //baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        //空白地图, 基础地图瓦片将不会被渲染。在地图类型中设置为NONE，将不会使用流量下载基础地图瓦片图层。使用场景：与瓦片图层一起使用，节省流量，提升自定义瓦片图下载速度。
        //baiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        //开启交通图
        baiduMap.setTrafficEnabled(true);
        //关闭缩放按钮
        mapView.showZoomControls(false);
    }






    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }


//    public void onDestroy() {
//        super.onDestroy();
//        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
//        // 退出时销毁定位
//        BaiduDw baiduDw = new BaiduDw(MainActivity.this);
//        baiduDw.mLocationClient.unRegisterLocationListener(baiduDw.myListener);
//        baiduDw.mLocationClient.stop();
//        // 关闭定位图层
//        baiduMap.setMyLocationEnabled(false);
//        mapView.onDestroy();
//        mapView = null;
//    }
    //


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
