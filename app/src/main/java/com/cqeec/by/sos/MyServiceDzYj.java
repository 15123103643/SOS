package com.cqeec.by.sos;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyServiceDzYj extends Service implements SensorEventListener {
    private static final int SENSOR_SHAKE = 10;
    private final String TAG = "zhangcheng";
    /**
     * 动作执行
     */
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SENSOR_SHAKE:
                    if (dzMusicActivity.mp.isPlaying()) dzMusicActivity.mp.stop();
                    Intent intent = new Intent(MyServiceDzYj.this, dzMusicActivity.class);
                    startActivity(intent);
                    Log.i("晃动", "检测到摇晃，执行操作！");
                    break;
            }
        }

    };
    private SensorManager mSensorManager;
    private Sensor sensor;
    private float mLastX, mLastY, mLastZ;
    private String sX, sY, sZ;
    private Vibrator vibrator;
    private Thread myThread = null;

    //
    public MyServiceDzYj() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        Log.d("MyServie", "onCreate  executed");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "预警消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
            Notification notification = new NotificationCompat.Builder(this, "default").
                    setContentTitle("SOS")
                    .setContentText("地震预警服务启动中")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher_round)

                    .setContentIntent(pi)
                    .build();
            startForeground(1, notification);

        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (mSensorManager == null) {
            Log.i("MyServie", "sensor not supported");
        }
        Log.i("MyServie", "sensor not supported2");
        mSensorManager.registerListener(MyServiceDzYj.this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d("MyServie", "onStartCommand  executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {


        mSensorManager.unregisterListener(this);
        Log.d("MyServie", " onDestroy  executed");
    }

    //创建创建通知渠道--安卓O以上
    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == null) {
            return;
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {  //在activity中完成该接口函数
            mLastX = event.values[0];
            mLastY = event.values[1];
            mLastZ = event.values[2];

            sX = String.valueOf(mLastX);
            sY = String.valueOf(mLastY);
            sZ = String.valueOf(mLastZ);
            Log.i("加速度1", sX);
            /*Log.i("加速度2",sY);
            Log.i("加速度3",sZ);*/
            //由于该接口函数不断刷新，所以可以把控件显示函数放在这里显示
            int medumValue = 12;// 如果不敏感请自行调低该数值,低于10的话就不行了,因为z轴上的加速度本身就已经达到10了

            float x = mLastX; // x轴方向的重力加速度，向右为正
            float y = mLastY; // y轴方向的重力加速度，向前为正
            float z = mLastZ; // z轴方向的重力加速度，向上为正
//            Log.i("加速度1",String.valueOf(x));
//            Log.i("加速度2",String.valueOf(y));
//            Log.i("加速度3",String.valueOf(z));
            if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) {
                vibrator.vibrate(200);
                Message msg = new Message();
                msg.what = SENSOR_SHAKE;
                handler.sendMessage(msg);

            }


        }

    }


}
