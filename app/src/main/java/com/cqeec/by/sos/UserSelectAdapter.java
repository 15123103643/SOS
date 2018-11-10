package com.cqeec.by.sos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserSelectAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mArr;

    public UserSelectAdapter(Context context, String[] arr) {
        mContext = context;
        mArr = arr;
    }

    @Override
    public int getCount() {
        return mArr.length;

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.listview_item, null);

        ImageView iconIv = (ImageView) view.findViewById(R.id.list_item_icon);
        TextView iconTv = (TextView) view.findViewById(R.id.list_item_info);

        if (0 == position) {
            iconIv.setImageResource(R.mipmap.camera);

        } else if (1 == position) {
            iconIv.setImageResource(R.mipmap.photo);
        } else if (2 == position) {
            iconIv.setImageResource(R.mipmap.cancel);
        }

        iconTv.setText(mArr[position]);


        return view;
    }
}
