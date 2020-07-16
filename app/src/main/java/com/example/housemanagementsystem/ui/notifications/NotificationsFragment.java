package com.example.housemanagementsystem.ui.notifications;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Person;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.housemanagementsystem.R;
import com.example.housemanagementsystem.tool.DBHelper;
import com.example.housemanagementsystem.tool.MyAdapter;
import com.example.housemanagementsystem.tool.Uility;
import com.example.housemanagementsystem.tool.person;
import com.example.housemanagementsystem.tool.time;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private ListView a;
    private time Date=new time();
    View root;
    private List<Map<String,String>> data=new LinkedList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        root = inflater.inflate(R.layout.fragment_notifications, container, false);
        a=root.findViewById(R.id.asd);
        notificationsViewModel.dbhelper=new DBHelper(root.getContext(), "social", null, 1);
        notificationsViewModel.per=new person();
        data=notificationsViewModel.queryall(Date.getTime());
        show(root);
        return root;
    }
    public void show(final View root) {
        MyAdapter adapter;
        adapter = new MyAdapter(root.getContext(), data);
        if (adapter == null) {
            Toast.makeText(root.getContext(),"没有找到数据",Toast.LENGTH_LONG).show();
        } else {
            a.setAdapter(adapter);
            Uility.setListViewHeightBasedOnChildren(a);
            //ListView item的点击事件
            a.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                }
            });

            //ListView item 中的删除按钮的点击事件
            adapter.setOnItemDeleteClickListener(new MyAdapter.onItemDeleteListener() {
                @Override
                public void onDeleteClick(final int i) {
                    Dialog dialog = new AlertDialog.Builder(root.getContext())

                            .setTitle("删除信息？")  // 创建标题

                            .setMessage("您确定要删除这条信息吗？")    //表示对话框的内容

                            .setIcon(R.drawable.ic_home_black_24dp) //设置LOGO

                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }

                            }).setPositiveButton("删除", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int which) {
                                    String id=data.get(i).get("info_id");
                                    delete(id);
                                    data=notificationsViewModel.queryall(Date.getTime());
                                    show(root);
                                }

                            }).create();  //创建对话框

                    dialog.show();  //显示对话框

                }
            });
            adapter.setOnItemDataClickListener(new MyAdapter.onItemdataListener() {
                @Override
                public void onDataClick(final int i) {
                    String id=data.get(i).get("info_id");
                    Intent intent = new Intent(root.getContext(),com.example.housemanagementsystem.ui.data.class);//启动MainActivity
                    intent.putExtra("info_id",id);//将data信息用putExtra（）存进intent
                    startActivity(intent);
                }
            });
        }
    }
    public void delete(String id){
        notificationsViewModel.sqldb = notificationsViewModel.dbhelper.getWritableDatabase();
        //第二个参数是WHERE语句（即执行条件，删除哪条数据）
        //第三个参数是WHERE语句中占位符（即"?"号）的填充值
        notificationsViewModel.sqldb.delete("info", "info_id=?", new String[]{id});//删除name的值是jack的那条记录
        Toast.makeText(root.getContext(),"删除成功",Toast.LENGTH_LONG).show();
    }


}