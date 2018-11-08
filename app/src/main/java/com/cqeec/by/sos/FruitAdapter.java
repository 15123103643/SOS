package com.cqeec.by.sos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;


//适配器---phone
public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;

    //重写构造函数，传递上下文，布局ID-数据
    public FruitAdapter(Context context, int textViewResourceId,
                        List<Fruit> objects) {
        super(context, textViewResourceId,objects);
        resourceId =textViewResourceId;
    }

    //用于控件实例缓存
    static class  ViewHolder{
        ImageView fruitImage;
        TextView fruitname;
    }
    //重写方法
    public View getView(int position, View convertView, ViewGroup parent){
        Fruit fruit = getItem(position);//获取当前项的Fruit实例
        //false 部位view 添加父布局--布局缓存——优化LIstview
        //如果存在布局，则直接调用，否者加载布局
        View view;
        //优化控件实例
        ViewHolder viewHolder;
        if (convertView == null){
            view =LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.fruitImage = view.findViewById(R.id.fruit_image);
            viewHolder.fruitname = view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);//将viewHulder 存储在view中
        }else {
            view = convertView;
            viewHolder =(ViewHolder) view.getTag();//重新获取viewHolder
        }
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitname.setText(fruit.getName());
        return view;
    }
}
