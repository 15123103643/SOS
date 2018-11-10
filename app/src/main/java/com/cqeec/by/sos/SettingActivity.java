package com.cqeec.by.sos;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingActivity extends AppCompatActivity {
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setSwitch();//开关字体
        myPreference1();

    }

    //保存开关
    public void myPreference(String a, boolean b) {
        //获取SharedPreferences对象
        SharedPreferences myPreference = getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        //向SharedPreference中写入数据需要使用Editor
        SharedPreferences.Editor editor = myPreference.edit();
        editor.putBoolean(a, b);
        editor.apply();//apply是异步执行的，不需要等待 --editor.commit();

    }

    //读取开关状态--没思路实现
    public void myPreference1() {
        //获取SharedPreferences对象
        SharedPreferences myPreference = getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        boolean age = myPreference.getBoolean("1", false);//单个键值
        boolean age2 = myPreference.getBoolean("2", false);//单个键值
        boolean age3 = myPreference.getBoolean("3", false);//单个键值
        boolean age4 = myPreference.getBoolean("4", false);//单个键值
        boolean age5 = myPreference.getBoolean("5", false);//单个键值
        boolean age6 = myPreference.getBoolean("6", false);//单个键值
        boolean age7 = myPreference.getBoolean("7", false);//单个键值
    }

    private void setSwitch() {
        //愚蠢的方法
        //获取SharedPreferences对象
        SharedPreferences myPreference = getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        boolean age = myPreference.getBoolean("1", false);//单个键值
        boolean age2 = myPreference.getBoolean("2", false);//单个键值
        boolean age3 = myPreference.getBoolean("3", false);//单个键值
        boolean age4 = myPreference.getBoolean("4", false);//单个键值
        boolean age5 = myPreference.getBoolean("5", false);//单个键值
        boolean age6 = myPreference.getBoolean("6", false);//单个键值
        boolean age7 = myPreference.getBoolean("7", false);//单个键值
         /*-------

        -----*/
        final Switch aSwitch = (Switch) findViewById(R.id.switch1);
        final Switch bSwitch = (Switch) findViewById(R.id.switch2);
        final Switch cSwitch = (Switch) findViewById(R.id.switch3);
        final Switch dSwitch = (Switch) findViewById(R.id.switch4);
        final Switch eSwitch = (Switch) findViewById(R.id.switch5);
        final Switch fSwitch = (Switch) findViewById(R.id.switch6);
        final Switch gSwitch = (Switch) findViewById(R.id.switch7);

        aSwitch.setChecked(age);
        aSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_false);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //控制开关字体颜色
                if (b) {
                    aSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_true);
                    myPreference("1", b);
                } else {
                    aSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_false);
                    myPreference("1", b);
                }
            }
        });
        bSwitch.setChecked(age2);
        bSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_false);
        bSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //控制开关字体颜色
                if (b) {
                    myPreference("2", b);
                    aSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_true);
                } else {
                    aSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_false);
                    myPreference("2", b);
                }
            }
        });
        cSwitch.setChecked(age3);
        cSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_false);
        cSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //控制开关字体颜色
                if (b) {
                    aSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_true);
                    myPreference("3", b);
                } else {
                    aSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_false);
                    myPreference("3", b);
                }
            }
        });
        dSwitch.setChecked(age4);
        dSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_false);
        dSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //控制开关字体颜色
                if (b) {
                    aSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_true);
                    myPreference("4", b);
                } else {
                    aSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_false);
                    myPreference("4", b);
                }
            }
        });
        eSwitch.setChecked(age5);
        eSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_false);
        eSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //控制开关字体颜色
                if (b) {
                    aSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_true);
                    myPreference("5", b);
                } else {
                    aSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_false);
                    myPreference("5", b);
                }
            }
        });
        fSwitch.setChecked(age6);
        fSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_false);
        fSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //控制开关字体颜色
                if (b) {
                    aSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_true);
                    myPreference("6", b);
                } else {
                    aSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_false);
                    myPreference("6", b);
                }
            }
        });
        gSwitch.setChecked(age7);

        gSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_false);
        gSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //控制开关字体颜色
                if (b) {
                    aSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_true);
                    myPreference("7", b);
                    Intent startIntent = new Intent(SettingActivity.this, MyServiceDzYj.class);
                    startService(startIntent);//开启服务
                    sendChatMsg();
                } else {
                    aSwitch.setSwitchTextAppearance(SettingActivity.this, R.style.s_false);
                    myPreference("7", b);

                    Intent stopIntent = new Intent(SettingActivity.this, MyServiceDzYj.class);

                    stopService(stopIntent);//停止服务
                    sendSubscribeMsg();




                }
            }
        });

    }
    //测试

    public void sendChatMsg() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, "chat")
                .setContentTitle("收到一条预警消息")
                .setContentText("地震预警已开启，请放心休息！")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .build();
        manager.notify(1, notification);
    }

    public void sendSubscribeMsg() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this, "subscribe")
                .setContentTitle("收到一条预警消息")
                .setContentText("地震预警已关闭！")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .build();
        manager.notify(2, notification);
    }

}
