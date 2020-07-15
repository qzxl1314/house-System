package com.example.housemanagementsystem.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.housemanagementsystem.R;
import com.example.housemanagementsystem.tool.DBHelper;
import com.example.housemanagementsystem.tool.person;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class data extends AppCompatActivity {
    public DBHelper dbhelper;
    public person per=new person();
    public SQLiteDatabase sqldb;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Intent intent=getIntent();
        final String id=intent.getStringExtra("info_id");//调用getStringExtra()，传入对应键值，得到传递的数据
        dbhelper=new DBHelper(this,"social", null, 1);
        sqldb=dbhelper.getWritableDatabase();
        in =findViewById(R.id.button);
        clear=findViewById(R.id.button2);
        house=findViewById(R.id.editTextTextPersonName);
        name=findViewById(R.id.editTextTextPersonName2);
        pid=findViewById(R.id.editTextTextPersonName3);
        phone=findViewById(R.id.editTextTextPersonName4);
        money=findViewById(R.id.editTextTextPersonName5);
        start=findViewById(R.id.editTextTextPersonName6);
        end=findViewById(R.id.editTextTextPersonName7);
        time=findViewById(R.id.editTextTextPersonName10);
        content=findViewById(R.id.editTextTextMultiLine);
        show(id);

        in.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new AlertDialog.Builder(data.this)

                        .setTitle("修改信息？")  // 创建标题

                        .setMessage("您确定要修改这条信息吗？")    //表示对话框的内容

                        .setIcon(R.drawable.ic_home_black_24dp) //设置LOGO

                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }

                        }).setPositiveButton("修改", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                per.setHouse(house.getText().toString());
                                per.setName(name.getText().toString());
                                per.setContent(content.getText().toString());
                                per.setEnd(end.getText().toString());
                                per.setMoney(money.getText().toString());
                                per.setP_id(pid.getText().toString());
                                per.setPhone(phone.getText().toString());
                                per.setStart(start.getText().toString());
                                per.setTime(time.getText().toString());
                                ContentValues values = new ContentValues();
                                values.put("info_house", per.getHouse());
                                values.put("info_name", per.getName());
                                values.put("info_person_id", per.getP_id());
                                values.put("info_phone", per.getPhone());
                                values.put("info_money", per.getMoney());
                                values.put("info_start", per.getStart());
                                values.put("info_end", per.getEnd());
                                values.put("info_time", per.getTime());
                                values.put("info_content", per.getContent());
                                //第二个参数是修改的字段及修改的值(已经存放到ContentValues中)
                                //第三个参数是WHERE语句
                                //第四个参数是WHERE语句中占位符的填充值
                                //如果第三四个参数为null，那就将每条记录都改掉
                                sqldb.update("info", values, "info_id=?", new String[]{id});
                                show(id);
                                Toast.makeText(data.this,"修改成功",Toast.LENGTH_LONG).show();
                            }

                        }).create();  //创建对话框

                dialog.show();  //显示对话框

            }
        });
        clear.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.this.finish();
            }
        });
    }
    public void show(String id){
        query(id);
        house.setText(per.getHouse());
        name.setText(per.getName());
        pid.setText(per.getP_id());
        phone.setText(per.getPhone());
        money.setText(per.getMoney());
        start.setText(per.getStart());
        end.setText(per.getEnd());
        time.setText(per.getTime());
        content.setText(per.getContent());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出程序后，关闭数据库资源
        sqldb.close();
    }
    void query(String id){
        //创建游标
        Cursor mCursor = sqldb.query("info", null, "info_id=?", new String[]{id}, null, null,
                null);
        //游标置顶
        mCursor.moveToFirst();
        //遍历
        if (mCursor.getCount() != 0) {
            do {
                per.setHouse(mCursor.getString(mCursor.getColumnIndex("info_house")));
                per.setName(mCursor.getString(mCursor.getColumnIndex("info_name")));
                per.setP_id(mCursor.getString(mCursor.getColumnIndex("info_person_id")));
                per.setPhone(mCursor.getString(mCursor.getColumnIndex("info_phone")));
                per.setMoney(mCursor.getString(mCursor.getColumnIndex("info_money")));
                per.setStart(mCursor.getString(mCursor.getColumnIndex("info_start")));
                per.setEnd(mCursor.getString(mCursor.getColumnIndex("info_end")));
                per.setTime(mCursor.getString(mCursor.getColumnIndex("info_time")));
                per.setContent(mCursor.getString(mCursor.getColumnIndex("info_content")));
            } while (mCursor.moveToNext());
        }
    }
}