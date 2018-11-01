package com.cqeec.by.sos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Map;

public class YjActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yj);
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

    //读取开关状态
    public void myPreference1() {
        //获取SharedPreferences对象
        SharedPreferences myPreference = getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        // boolean age=myPreference.getBoolean("1", false);单个键值
        Map<String, ?> allMaps = myPreference.getAll();//所有的键值
        Log.i("预警", allMaps.toString());


    }

    private void setSwitch() {
        final Switch aSwitch = (Switch) findViewById(R.id.switch1);
        final Switch bSwitch = (Switch) findViewById(R.id.switch2);
        final Switch cSwitch = (Switch) findViewById(R.id.switch3);
        final Switch dSwitch = (Switch) findViewById(R.id.switch4);
        final Switch eSwitch = (Switch) findViewById(R.id.switch5);
        final Switch fSwitch = (Switch) findViewById(R.id.switch6);
        final Switch gSwitch = (Switch) findViewById(R.id.switch7);

        aSwitch.setChecked(false);
        aSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_false);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //控制开关字体颜色
                if (b) {
                    aSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_true);
                    myPreference("1", b);
                } else {
                    aSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_false);
                    myPreference("1", b);
                }
            }
        });
        bSwitch.setChecked(false);
        bSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_false);
        bSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //控制开关字体颜色
                if (b) {
                    myPreference("2", b);
                    aSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_true);
                } else {
                    aSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_false);
                    myPreference("2", b);
                }
            }
        });
        cSwitch.setChecked(false);
        cSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_false);
        cSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //控制开关字体颜色
                if (b) {
                    aSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_true);
                    myPreference("3", b);
                } else {
                    aSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_false);
                    myPreference("3", b);
                }
            }
        });
        dSwitch.setChecked(false);
        dSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_false);
        dSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //控制开关字体颜色
                if (b) {
                    aSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_true);
                    myPreference("4", b);
                } else {
                    aSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_false);
                    myPreference("4", b);
                }
            }
        });
        eSwitch.setChecked(false);
        eSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_false);
        eSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //控制开关字体颜色
                if (b) {
                    aSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_true);
                    myPreference("5", b);
                } else {
                    aSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_false);
                    myPreference("5", b);
                }
            }
        });
        fSwitch.setChecked(false);
        fSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_false);
        fSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //控制开关字体颜色
                if (b) {
                    aSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_true);
                    myPreference("6", b);
                } else {
                    aSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_false);
                    myPreference("6", b);
                }
            }
        });
        gSwitch.setChecked(false);
        gSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_false);
        gSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //控制开关字体颜色
                if (b) {
                    aSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_true);
                    myPreference("7", b);
                } else {
                    aSwitch.setSwitchTextAppearance(YjActivity.this, R.style.s_false);
                    myPreference("7", b);
                }
            }
        });

    }
}
