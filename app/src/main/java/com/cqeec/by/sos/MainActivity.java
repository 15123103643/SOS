package com.cqeec.by.sos;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;

import com.baidu.mapapi.model.LatLng;


public class MainActivity extends AppCompatActivity {
    //地图控件
    private MapView mapView;
    //百度地图
    private BaiduMap baiduMap;
    //防止每次定位都重新设置中心点和marker
    private boolean isFirstLocation = true;
    //初始化LocationClient定位类
    private LocationClient mLocationClient = null;
    //BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口，原有BDLocationListener接口
    private BDLocationListener myListener = new MyLocationListener();
    //文本控件
    private TextView textView;
    //三个图片按钮
    private ImageButton Call_phone;
    private ImageButton SMS;
    private ImageButton emergency_contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permission_check();//权限检测
        initMap();//地图
        phone();//电话
//        sendMessage();//启动另外的Activity

    }
    //启动另外的Activity
    private void sendMessage() {
        Intent intent = new Intent(this,listviewActivity.class);
        startActivity(intent);

    }

    //权限检查
    public void permission_check(){
        //权限数组
        final String permission[] = {Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CHANGE_WIFI_STATE };
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.
                permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    permission , 1);

        }
    }

    //
    public void phone() {

        //实例化对象
        Call_phone = findViewById(R.id.call_phone);
        Call_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //all();
                sendMessage();
            }
        });

    }


    /**
     * 初始化地图
     */
    public void initMap() {
        //得到地图实例
        mapView = findViewById(R.id.bmapView);
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
        //开启交通图
        //baiduMap.setTrafficEnabled(true);
        //关闭缩放按钮
        mapView.showZoomControls(false);

        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        //声明LocationClient类
        mLocationClient = new LocationClient(this);
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        initLocation();
        //开始定位
        mLocationClient.start();


    }

    /**
     * 配置定位参数
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        int span = 5000;
        option.setScanSpan(span);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false,设置是否使用gps
        option.setOpenGps(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
    }


    @Override
    //当该activity与用户能进行交互时被执行
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }

    @Override
    //是当前的acitivty被暂停了_回到可交互状态，调用onResume()。
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

    @Override
    //退出
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        // 退出时销毁定位
        mLocationClient.unRegisterLocationListener(myListener);
        mLocationClient.stop();
        // 关闭定位图层
        baiduMap.setMyLocationEnabled(false);
        mapView.onDestroy();
        mapView = null;
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

        switch (id) {
            case R.id.emergency_contact:
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.My_first_aid_card:
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Rescue_information:
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.List_for_help:
                Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.system_stttings:
                Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    //定位数据信息处理
    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //获取文本实例
            textView = findViewById(R.id.position_textView);
//            //获取定位结果字符串
            StringBuffer sb = new StringBuffer(256);

            //插入
            sb.append("定位时间 : ");
            sb.append(location.getTime());    //获取定位时间
            sb.append("\n定位精度: ");
            sb.append(location.getRadius()).append("米");    //获取定位精准度


            //根据定位信息显示不同的信息
            if (location.getLocType() == BDLocation.TypeGpsLocation) {

                // GPS定位结果
//            sb.append("\nspeed : ");
//            sb.append(location.getSpeed());    // 单位：公里每小时
//
//            sb.append("\nsatellite : ");
//            sb.append(location.getSatelliteNumber());    //获取卫星数
//
//            sb.append("\nheight : ");
//            sb.append(location.getAltitude());    //获取海拔高度信息，单位米
//
//            sb.append("\ndirection : ");
//            sb.append(location.getDirection());    //获取方向信息，单位度

                sb.append("\n位置 : ");
                sb.append(location.getAddrStr()).append(location.getLocationDescribe());    //获取地址信息

                sb.append("\n定位方式 : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

                // 网络定位结果
                sb.append("\n位置 : ");
                sb.append(location.getAddrStr()).append(location.getLocationDescribe());  //获取地址信息    //位置语义化信息;

//            sb.append("\noperationers : ");
//            sb.append(location.getOperators());    //获取运营商信息

                sb.append("\n定位方式 : ");
                sb.append("网络定位成功");

            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                // 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");

            } else if (location.getLocType() == BDLocation.TypeServerError) {

                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到1634837963@，会有人追查原因");

            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");

            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");

            }
            //日志信息打印。便于分析数据
            Log.i("数据分析", sb.toString());
            //现在已经定位成功，可以将定位的数据保存下来，这里我新建的一个Const类，保存全局静态变量
            //去掉指定字符在
            int iFlag = -1;
            iFlag = sb.indexOf("在");
            if (iFlag != -1) {
                Const.Describe = sb.deleteCharAt(iFlag);
                textView.setText(Const.Describe);


            }

            //Const.Describe=sb.append(location.getCity()).append(location.getDistrict()).append(location.getLocationDescribe());
//            textView.setText(Const.Describe);
            //这个判断是为了防止每次定位都重新设置中心点和marker
            if (isFirstLocation) {
                isFirstLocation = false;
                //设置并显示中心点
                setPosition2Center(baiduMap, location, true);

            }
        }
    }

    /**
     * 设置中心点和添加marker
     *
     * @param map
     * @param bdLocation
     * @param isShowLoc
     */
    public void setPosition2Center(BaiduMap map, BDLocation bdLocation, Boolean isShowLoc) {
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                .direction(bdLocation.getRadius()).latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();
        map.setMyLocationData(locData);

        if (isShowLoc) {
            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            map.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }
    }

    //拨打电话的方法
    public void call() {
        Log.i("电话测试","电话测试");
        try {
            //Intent 传递
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:1"));
            startActivity(intent);

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    //拒绝权限的提示
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        //grantResults 封装了授权的结果
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.
                        PERMISSION_GRANTED) {

                   // phone();//电话
                } else {
                    Toast.makeText(this, "您拒绝了权限，请授权", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }
}

