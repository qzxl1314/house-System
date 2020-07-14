package com.example.housemanagementsystem.ui.dashboard;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.housemanagementsystem.R;
import com.example.housemanagementsystem.tool.DBHelper;
import com.example.housemanagementsystem.tool.person;

public class DashboardFragment extends Fragment {
     Button in ;
     Button clear;
     EditText house;
     EditText name;
     EditText pid;
     EditText phone;
     EditText money;
     EditText start;
     EditText end;
     EditText time;
     EditText content;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        dashboardViewModel.dbhelper=new DBHelper(root.getContext(), "social", null, 1);//建立数据库
        dashboardViewModel.per=new person();
        //定义组件
        in = root.findViewById(R.id.button);
        clear=root.findViewById(R.id.button2);
        house=root.findViewById(R.id.editTextTextPersonName);
        name=root.findViewById(R.id.editTextTextPersonName2);
        pid=root.findViewById(R.id.editTextTextPersonName3);
        phone=root.findViewById(R.id.editTextTextPersonName4);
        money=root.findViewById(R.id.editTextTextPersonName5);
        start=root.findViewById(R.id.editTextTextPersonName6);
        end=root.findViewById(R.id.editTextTextPersonName7);
        time=root.findViewById(R.id.editTextTextPersonName10);
        content=root.findViewById(R.id.editTextTextMultiLine);
        in.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean e=isEmpty();
                if (e){
                    dashboardViewModel.per.setHouse(house.getText().toString());
                    dashboardViewModel.per.setName(name.getText().toString());
                    dashboardViewModel.per.setContent(content.getText().toString());
                    dashboardViewModel.per.setEnd(end.getText().toString());
                    dashboardViewModel.per.setMoney(money.getText().toString());
                    dashboardViewModel.per.setP_id(pid.getText().toString());
                    dashboardViewModel.per.setPhone(phone.getText().toString());
                    dashboardViewModel.per.setStart(start.getText().toString());
                    dashboardViewModel.per.setTime(time.getText().toString());
                    dashboardViewModel.sqldb = dashboardViewModel.dbhelper.getWritableDatabase();
                    //ContentValues类似HashMap，区别是ContentValues只能存简单数据类型，不能存对象
                    ContentValues values = new ContentValues();
                    values.put("info_house", dashboardViewModel.per.getHouse());
                    values.put("info_name", dashboardViewModel.per.getName());
                    values.put("info_person_id", dashboardViewModel.per.getP_id());
                    values.put("info_phone", dashboardViewModel.per.getPhone());
                    values.put("info_money", dashboardViewModel.per.getMoney());
                    values.put("info_start", dashboardViewModel.per.getStart());
                    values.put("info_end", dashboardViewModel.per.getEnd());
                    values.put("info_time", dashboardViewModel.per.getTime());
                    values.put("info_content", dashboardViewModel.per.getContent());
                    //执行插入操作
                    long a=dashboardViewModel.sqldb.insert("info", null, values);
                    System.out.println(a+"asd");
                    if (a!=0){
                        Toast.makeText(root.getContext(),"插入成功",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(root.getContext(),"插入失败，格式有误",Toast.LENGTH_LONG).show();
                    }
                    clear();
                }else{
                    Toast.makeText(root.getContext(),"内容不得为空",Toast.LENGTH_LONG).show();
                }

            }
        });
        clear.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
            clear();
            }
        });
        return root;
    }
    private void clear(){
        house.setText("");
        house.setText("");
        name.setText("");
        pid.setText("");
        phone.setText("");
        money.setText("");
        start.setText("");
        end.setText("");
        time.setText("");
        content.setText("");
    }//清空text控件内容
    private boolean isEmpty(){
        if (house.getText().toString().isEmpty()||
                name.getText().toString().isEmpty()||
                pid.getText().toString().isEmpty()||
                phone.getText().toString().isEmpty()||
                money.getText().toString().isEmpty()||
                start.getText().toString().isEmpty()||
                end.getText().toString().isEmpty()||
                time.getText().toString().isEmpty()||
                house.getText().toString().equals("")||
                name.getText().toString().equals("")||
                pid.getText().toString().equals("")||
                phone.getText().toString().equals("")||
                money.getText().toString().equals("")||
                start.getText().toString().equals("")||
                end.getText().toString().equals("")||
                time.getText().toString().equals(""))
            return false;
        else
            return true;
    }
}