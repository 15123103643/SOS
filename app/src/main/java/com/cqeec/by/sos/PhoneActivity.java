package com.cqeec.by.sos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class PhoneActivity extends AppCompatActivity {
    //声明list集合数组
    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        //初始化图标
        initFruits();
        FruitAdapter adapter = new FruitAdapter(PhoneActivity.this,R.layout.fruit_item,fruitList);
        /*这里有三个参000000000000000000000000000000000000000000000000000000000000数，分别表示：
        当前上下文（context）
        子项布局的id（textViewResourceId）:这里的布局是使用了系统自带的
        要适配的数据（objects）*/
        ListView listView =findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit =fruitList.get(position);
               call(fruit.getName());
            }
        });

    }
//集合组
    private void initFruits() {
            Fruit call_110 = new Fruit("110",R.mipmap.call_110);
            fruitList.add(call_110);
            Fruit call_120 = new Fruit("120",R.mipmap.call_120);
            fruitList.add(call_120);
            Fruit call_119 = new Fruit("119",R.mipmap.call_119);
            fruitList.add(call_119);
            Fruit call_122 = new Fruit("122",R.mipmap.call_122);
            fruitList.add(call_122);
            Fruit call_12395 = new Fruit("12395",R.mipmap.call_12395);
            fruitList.add(call_12395);
            Fruit call_114 = new Fruit("114",R.mipmap.call_114);
            fruitList.add(call_114);
    }
    //
    //拨打电话的方法
    public void call(String phone) {
//        Log.i("电话测试","电话测试");
        try {
            //Intent 传递
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+phone));
            startActivity(intent);

        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }



}
