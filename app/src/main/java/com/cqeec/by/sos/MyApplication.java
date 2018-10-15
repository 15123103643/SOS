package com.cqeec.by.sos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;

public class MyApplication extends Application {
    //全局Context
    //如果需要全局引用Context 使用类名.getContext()
    private static Context context;
    @Override
    public void onCreate() {
        context =getApplicationContext();
        super.onCreate();
        //百度地图SDK的全局初始化
        SDKInitializer.initialize(getApplicationContext());

    }
    public static Context getContext(){

        return context;
    }

}
