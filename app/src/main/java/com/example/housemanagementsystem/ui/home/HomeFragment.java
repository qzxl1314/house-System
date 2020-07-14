package com.example.housemanagementsystem.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ListView a;
    private List<Map<String,String>> data=new LinkedList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        a=root.findViewById(R.id.list);
        homeViewModel.dbhelper=new DBHelper(root.getContext(), "social", null, 1);//建立数据库
        homeViewModel.per=new person();
        data=homeViewModel.queryall();
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

                                }

                            }).create();  //创建对话框

                    dialog.show();  //显示对话框

                }
            });
            adapter.setOnItemDataClickListener(new MyAdapter.onItemdataListener() {
                @Override
                public void onDataClick(final int i) {


                }
            });
        }
    }
}