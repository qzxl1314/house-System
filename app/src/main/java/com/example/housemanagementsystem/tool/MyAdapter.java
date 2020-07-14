package com.example.housemanagementsystem.tool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.housemanagementsystem.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private List<Map<String,String>> mList = new ArrayList<>();

    public MyAdapter(Context context, List<Map<String,String>> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
            viewHolder.house = (TextView) view.findViewById(R.id.house);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            viewHolder.data = (Button) view.findViewById(R.id.button5);
            viewHolder.delete = (Button) view.findViewById(R.id.item_btn);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.house.setText("房号"+mList.get(i).get("info_house"));
        viewHolder.name.setText("租客名"+mList.get(i).get("info_name"));
        viewHolder.data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemdataListener.onDataClick(i);
            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemDeleteListener.onDeleteClick(i);
            }
        });
        return view;
    }
    /**
     * 查看按钮的监听接口
     */
    public interface onItemdataListener {
        void onDataClick(int i);
    }

    private onItemdataListener onItemdataListener;

    public void setOnItemDataClickListener(onItemdataListener onItemdataListener) {
        this.onItemdataListener = onItemdataListener;
    }
    /**
     * 删除按钮的监听接口
     */
    public interface onItemDeleteListener {
        void onDeleteClick(int i);
    }

    private onItemDeleteListener mOnItemDeleteListener;

    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }

    class ViewHolder {
        TextView house;
        TextView name;
        Button data;
        Button delete;
    }
}
